package bbdd;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            //iniciamos la transacción
            em.getTransaction().begin();






        } catch (Exception e) {
            //Si hay algún problema con al transacción, hacemos un rollback al estado anterior
            if (em.getTransaction() != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            //Por último cerramos los entity managers
            em.close();
            emf.close();
        }
    }
}
