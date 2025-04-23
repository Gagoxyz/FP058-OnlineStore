import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaConexionJPA {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventJPA");
            EntityManager em = emf.createEntityManager();
            System.out.println("¡Conexión exitosa!");
            em.close();
            emf.close();
        } catch (Exception e) {
            System.err.println("Error al conectar con JPA/MySQL:");
            e.printStackTrace();
        }
    }
}