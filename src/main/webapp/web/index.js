let ESTUDIANTES = [
  
];
let CARRERAS = [
  
];
let MATRICULAS = [
 
];
console.log("asdasdasdasdasd");
const baseUrl = "http://localhost:8080/Entregable3/api";

document.addEventListener("DOMContentLoaded", async () => {
  const getEstudiante = async () => {
    try {
      let response = await fetch(`${baseUrl}/estudiantes/dni`);
      let json = await response.json();
      console.log(json);
      ESTUDIANTES = json;
      cargarEstudiantes()
    } catch (error) {
      console.log(error);
    }
  };
const getCarreras = async () => {
  try {
    let response = await fetch(`${baseUrl}/carreras`);
    let responseJson = await response.json();
    CARRERAS = responseJson;
    cargarOpcionesCarrera("selectCarrera");
    cargarOpcionesCarrera("selectCarrera-g");
  } catch (error) {
    console.log(error);
  }
}
getEstudiante();
getCarreras();
  //Ejercicio 2a -> Dar de alta un estudiante
  let formAlta = document.getElementById("darDeAltaEstudiante");
  formAlta.addEventListener("submit", async (e) => {
    console.log(e);
    e.preventDefault();
    let estudiante = {
      nmro_libreta: Math.floor(Math.random() * 100000000),
    };
    for (const elem of e.target.elements) {
      console.log(elem.id);
      if (elem.id || elem.id !== "") estudiante[elem.id] = elem.value;
    }
    estudiante.edad = Number(estudiante.edad);
    estudiante.dni = Number(estudiante.dni);
    console.log(estudiante);
    // ESTUDIANTES.push(estudiante) //Post por fetch
   try {
    let response = await fetch(`${baseUrl}/estudiantes`, {
      method: "POST",
      headers: {
        'Accept': 'application/json',
        'Content-type': 'application/json'
      },
      body: JSON.stringify(estudiante)
    });
    console.log(response);
    let responseJson = await response.json();
    console.log(responseJson);
   } catch (error) {
     console.log(error);
   }
  });
//Ejercicio 2b -> Matricular un estudiante en una carrera
let cargarOpcionesCarrera = (idSelect) => {
  let selectCarreras = document.getElementById(idSelect);
  CARRERAS.forEach((carrera) => {
    let option = document.createElement("option");
    option.value = carrera.idCarrera;
    option.innerText = carrera.nombre;
    selectCarreras.appendChild(option);
  });
};

let matricularEstudiante = document.getElementById("matricularEstudiante");
matricularEstudiante.addEventListener("click", (e) => {
  e.preventDefault();
  let nro_libretaEstudiante = document.getElementById(
    "inputMatricularEstudiante"
  );
  console.log(nro_libretaEstudiante.value);
  let idCarrera = document.getElementById("selectCarrera");
  let estudiante = ESTUDIANTES.find((estudiante) => estudiante.nmro_libreta === Number(nro_libretaEstudiante.value));
  console.log(estudiante);
  if (estudiante) {
    fetch(
      `${baseUrl}/estudiantes/matricular/${estudiante.nmro_libreta}/${idCarrera.value}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      }
    ).then((res) => {
      nro_libretaEstudiante.value = "";
      idCarrera.value = -1;
    });
  }
});
//Ejercicio 2c -> Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
let parsearEstudiante = (estudiante) => {
  return {
    nro_libreta: estudiante.nmro_libreta,
    nombre: `${estudiante.nombre} ${estudiante.apellido}`,
    edad: estudiante.edad,
    genero: estudiante.genero,
    dni: estudiante.dni,
    ciudad: estudiante.ciudad_residencia,
  };
};
let cargarEstudiantes = async () => {
  //Fetch por GET
  let tabla = document.getElementById("listaEstudiantes");
  tabla.innerHTML = "";
  for (let estudiante of ESTUDIANTES) {
    estudiante = parsearEstudiante(estudiante);
    let tr = document.createElement("tr");
    for (const key in estudiante) {
      let td = document.createElement("td");
      td.innerText = estudiante[key];
      tr.appendChild(td);
    }
    tabla.appendChild(tr);
  }
};
//Ejercicio 2d -> Recuperar un estudiante, en base a su número de libreta universitaria.
let buscarEstudiante = document.getElementById("buscarEstudiantePorLibreta");
buscarEstudiante.addEventListener("click", (e) => {
  e.preventDefault();
  let nroLibreta = document.getElementById("inputEstudianteBusqueda").value;

  fetch(`${baseUrl}/estudiantes/${nroLibreta}`)
    .then((res) => {
      res.json().then(
        res=>{
          if (res) {
            let estudiante = parsearEstudiante(res);
            let tr = document.createElement("tr");
            for (const key in estudiante) {
              let td = document.createElement("td");
              td.innerText = estudiante[key];
              tr.appendChild(td);
            }
            let tabla = document.getElementById("listaEstudiantes");
            tabla.innerHTML = "";
            tabla.appendChild(tr);
          }
        }
      );
    })
    .catch((err) => console.error(err));
  // let estudiante = ESTUDIANTES.find(estudiante => estudiante.nro_libreta === nroLibreta)//Fetch por GET(nro_libreta)


});

//Ejercicio 2e -> Recuperar todos los estudiantes, en base a su género.
let buscarEstudiantesPorGenero = document.getElementById(
  "buscarEstudiantesPorGenero"
);
buscarEstudiantesPorGenero.addEventListener("click", (e) => {
  e.preventDefault();
  let genero = document.getElementById("inputGeneroBusqueda").value;
  if(genero === "")return;
  fetch(`${baseUrl}/estudiantes/genero/${genero}`)
    .then((res) => {
      res.json().then(
        res=>{
          console.log(res);
          if (res.length > 0) {
            let estudiantes = res.map((estudiante) =>
              parsearEstudiante(estudiante)
            );
            let tabla = document.getElementById("listaEstudiantes");
            tabla.innerHTML = "";
            for (let estudiante of estudiantes) {
              let tr = document.createElement("tr");
              for (const key in estudiante) {
                let td = document.createElement("td");
                td.innerText = estudiante[key];
                tr.appendChild(td);
              }
              tabla.appendChild(tr);
            }
          }
        }
      );
    })
    .catch((err) => console.error(err));

});

// 2)f)recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos./carreras/cantidad-inscriptos

const getCarrerasWithEstudiantesByCantidadInscriptos = (e) => {
  e.preventDefault();
  fetch(`${baseUrl}/carreras/cantidad-inscriptos`)
    .then((res) => {
      res.json().then(res=>{
        console.log(res)
        let tabla = document.getElementById("listaCarreras");
        tabla.innerHTML = "";
        for (let carrera of res) {
          console.log(carrera)
          let tr = document.createElement("tr");
          for (const key in carrera) {
            let td = document.createElement("td");
            if(key =="carrera"){
              td.innerText=carrera[key].nombre
            }else{
              td.innerText = carrera[key];
            }
            tr.appendChild(td);
          }
          tabla.appendChild(tr);
        }
      });
      
    })
    .catch((err) => console.error(err));
};
let OrdenarPorInscriptos = document.getElementById("OrdenarPorInscriptos")
                          .addEventListener("click",getCarrerasWithEstudiantesByCantidadInscriptos);

// 2)g)recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
// "/carrera-ciudad/id-carrera/str-ciudad")

const getByCarreraAndCiudad = (e) => {
  e.preventDefault();
  let idCarrera = document.getElementById("selectCarrera-g").value;
  let ciudad = document.getElementById("inputCiudad-g").value;
  fetch(`${baseUrl}/estudiantes/carrera-ciudad/${idCarrera}/${ciudad}`)
    .then((res) => {
      res.json().then((data) => {
        let tabla = document.getElementById("listaEstudianteCarreraCiudad");
        tabla.innerHTML = "";
        for (let estudiante of data) {
          let tr = document.createElement("tr");
          for (const key in estudiante) {
            let td = document.createElement("td");
            td.innerText = estudiante[key];
            tr.appendChild(td);
          }
          tabla.appendChild(tr);
        }
      });
      
    })
    .catch((err) => console.error(err));
};
let buscarEstudianteCC = document
  .getElementById("buscarEstudianteCC")
  .addEventListener("click", getByCarreraAndCiudad);

//2)h)generar un reporte de las carreras, que para cada carrera incluya información de los
// inscriptos y egresados por año.

  const getMatriculas = () => {
    fetch(`${baseUrl}/matriculas/reporte`)
      .then((res) => {
        res.json().then(
          res=>{
            let listaReporte=document.getElementById("listaReportes");
            console.log(res)
            listaReporte.innerHTML="";
            for (let reporte of res) {
              let li=document.createElement("li");
              li.innerText=`${reporte.nombreCarrera} - Anio: ${reporte.anio} - Egresados: ${reporte.egresados} - Inscriptos: ${reporte.inscriptos}`;
              console.log(reporte)
              li.classList.add("list-group-item")
              listaReporte.appendChild(li)
            }
          }
        );

      })
      .catch((err) => console.error(err));
  };

  let generarReporte=document.getElementById("generarReporte")
  generarReporte.addEventListener('click',getMatriculas);
});
