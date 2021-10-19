let ESTUDIANTES = [
  
];
let CARRERAS = [
  
];
let MATRICULAS = [
 
];

const baseUrl = "http://localhost:8080/Entregable3Rest/api";

document.addEventListener("DOMContentLoaded", async () => {
  const getEstudiante = async () => {
    try {
      let response = await fetch(`${baseUrl}/estudiantes/dni`);
      let json = await response.json();
      console.log(json);
      ESTUDIANTES = json;
    } catch (error) {
      console.log(error);
    }
  };

  const getCarreras = () => {
    fetch(`${baseUrl}/carreras`)
      .then((res) => {
        res.json();
        CARRERAS = res;
      })
      .catch((err) => console.error(err));
  };
getEstudiante();
//getCarreras();
  //Ejercicio 2a -> Dar de alta un estudiante
  let formAlta = document.getElementById("darDeAltaEstudiante");
  formAlta.addEventListener("submit", (e) => {
    e.preventDefault();
    let estudiante = {
      nro_libreta: Math.floor(Math.random() * 100000000).toString(),
    };
    for (const elem of e.target.elements) {
      if (elem.id || elem.id !== "") estudiante[elem.id] = elem.value;
    }
    // ESTUDIANTES.push(estudiante) //Post por fetch
    fetch(`${baseUrl}/estudiantes`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(estudiante),
    })
      .then((res) => {
        e.target.reset();
        cargarEstudiantes();
      })
      .catch((err) => console.error(err));
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
  cargarOpcionesCarrera("selectCarrera");
  cargarOpcionesCarrera("selectCarrera-g");
  let matricularEstudiante = document.getElementById("matricularEstudiante");
  matricularEstudiante.addEventListener("click", (e) => {
    e.preventDefault();
    let nro_libretaEstudiante = document.getElementById(
      "inputMatricularEstudiante"
    );
    let idCarrera = document.getElementById("selectCarrera");
    let estudiante = ESTUDIANTES.find(
      (estudiante) => estudiante.nro_libreta === nro_libretaEstudiante.value
    );
    if (estudiante) {
      // let matricula = {
      //     id: Math.floor(Math.random() * 100000000).toString(),
      //     egresado: false,
      //     fecha_inicio: new Date().toISOString(),
      //     estudiante: estudiante.nro_libreta,
      //     carrera: idCarrera.value
      // }
      // MATRICULAS.push(matricula) //Post por fetch
      fetch(
        `${baseUrl}/estudiantes/matricular/${estudiante.nro_libreta}/${idCarrera.value}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
        }
      ).then((res) => {
        res.json();
        nro_libretaEstudiante.value = "";
        idCarrera.value = -1;
      });
    }
  });

  //Ejercicio 2c -> Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
  let parsearEstudiante = (estudiante) => {
    return {
      nro_libreta: estudiante.nro_libreta,
      nombre: `${estudiante.nombre} ${estudiante.apellido}`,
      edad: estudiante.edad,
      genero: estudiante.genero,
      dni: estudiante.dni,
      ciudad: estudiante.ciudad_residencia,
    };
  };

  let cargarEstudiantes = () => {
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
  cargarEstudiantes();

  //Ejercicio 2d -> Recuperar un estudiante, en base a su número de libreta universitaria.
  let buscarEstudiante = document.getElementById("buscarEstudiantePorLibreta");
  buscarEstudiante.addEventListener("click", (e) => {
    e.preventDefault();
    let nroLibreta = document.getElementById("inputEstudianteBusqueda").value;

    fetch(`${baseUrl}/estudiantes/${nroLibreta}`)
      .then((res) => {
        res.json();
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
      })
      .catch((err) => console.error(err));
    // let estudiante = ESTUDIANTES.find(estudiante => estudiante.nro_libreta === nroLibreta)//Fetch por GET(nro_libreta)

    e.target.reset();
  });

  //Ejercicio 2e -> Recuperar todos los estudiantes, en base a su género.
  let buscarEstudiantesPorGenero = document.getElementById(
    "buscarEstudiantesPorGenero"
  );
  buscarEstudiantesPorGenero.addEventListener("click", (e) => {
    e.preventDefault();
    let genero = document.getElementById("inputGeneroBusqueda").value;
    let estudiantes = ESTUDIANTES.filter(
      (estudiante) => estudiante.genero === genero
    ); //Fetch por GET(genero)

    fetch(`${baseUrl}/estudiantes/genero/${genero}`)
      .then((res) => {
        res.json();
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
      })
      .catch((err) => console.error(err));
    e.target.reset();
  });

  // 2)f)recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos./carreras/cantidad-inscriptos

  const getCarrerasWithEstudiantesByCantidadInscriptos = () => {
    fetch(`${baseUrl}/carreras/cantidad-inscriptos`)
      .then((res) => {
        res.json();
        let tabla = document.getElementById("listaCarreras");
        tabla.innerHTML = "";
        for (let carrera of res) {
          let tr = document.createElement("tr");
          for (const key in carrera) {
            let td = document.createElement("td");
            td.innerText = carrera[key];
            tr.appendChild(td);
          }
          tabla.appendChild(tr);
        }
      })
      .catch((err) => console.error(err));
  };
  let OrdenarPorInscriptos = document
    .getElementById("OrdenarPorInscriptos")
    .addEventListener(
      "click",
      getCarrerasWithEstudiantesByCantidadInscriptos()
    );

  //2)g)recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
  //"/carrera-ciudad/id-carrera/str-ciudad")

  const getByCarreraAndCiudad = () => {
    let idCarrera = document.getElementById("selectCarrera-g").value;
    let ciudad = document.getElementById("inputCiudad-g").value;
    fetch(`${baseUrl}/estudiantes/carrera-ciudad/${idCarrera}/${ciudad}`)
      .then((res) => {
        res.json();
        let tabla = document.getElementById("listaEstudianteCarreraCiudad");
        tabla.innerHTML = "";
        for (let estudiante of res) {
          let tr = document.createElement("tr");
          for (const key in estudiante) {
            let td = document.createElement("td");
            td.innerText = estudiante[key];
            tr.appendChild(td);
          }
          tabla.appendChild(tr);
        }
      })
      .catch((err) => console.error(err));
  };
  let buscarEstudianteCC = document
    .getElementById("buscarEstudianteCC")
    .addEventListener("click", getByCarreraAndCiudad());

  //2)h)generar un reporte de las carreras, que para cada carrera incluya información de los
  // inscriptos y egresados por año.
  
    const getMatriculas = () => {
      fetch(`${baseUrl}/matriculas`)
        .then((res) => {
          res.json();
          let listaReporte=document.getElementById("listaReportes");
          listaReporte.innerHTML="";
        
          for (let reporte of res) {
            let li=document.createElement("li");
            li.innerText=reporte;
            listaReporte.appendChild(li)
          }
          
        })
        .catch((err) => console.error(err));
    };

  let generarReporte=document.getElementById("generarReporte").addEventListener('click',getMatriculas());
});
