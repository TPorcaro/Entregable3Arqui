package EMF;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EMF implements ServletContextListener {

	public static EntityManagerFactory EMF;
	
	public static EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		EMF = Persistence.createEntityManagerFactory("entregable3");
		System.out.println("EntityManagerFactory Created");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(EMF != null) {
			EMF.close();
			EMF = null;
			System.out.println("EntityManagerFactory Destroyed");
		}else {
			System.out.println("EntityManagerFactory already destroyed");
		}
	}
}
