package bbdd;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciamos la transacción
            em.getTransaction().begin();

            // Creamos las instancias de los Juegos (Tengo un método para que sea más claro el código)
            Juego nuevoJuego1 = crearJuego("Hollow Kignt", Date.valueOf("2017-02-24"), "PC,PS4,Switch");
            Juego nuevoJuego2 = crearJuego("The Legend of Zelda: Breath of the Wild", Date.valueOf("2017-03-03"), "Switch");
            Juego nuevoJuego3 = crearJuego("Red Dead Redemption 2", Date.valueOf("2018-10-26"), "PS4,Xbox One");
            Juego nuevoJuego4 = crearJuego("Cyberpunk 2077", Date.valueOf("2020-12-10"), "PC,PS4,Xbox One");
            Juego nuevoJuego5 = crearJuego("Super Mario Odyssey", Date.valueOf("2017-10-27"), "Switch");
            Juego nuevoJuego6 = crearJuego("The Witcher 3: Wild Hunt", Date.valueOf("2015-05-19"), "PC,PS4,Xbox One");
            Juego nuevoJuego7 = crearJuego("God of War", Date.valueOf("2018-04-20"), "PS4");
            Juego nuevoJuego8 = crearJuego("Minecraft", Date.valueOf("2011-11-18"), "PC,PS4,Xbox One,Switch");
            Juego nuevoJuego9 = crearJuego("Assassin's Creed Valhalla", Date.valueOf("2020-11-10"), "PC,PS4,Xbox One");
            Juego nuevoJuego10 = crearJuego("Animal Crossing: New Horizons", Date.valueOf("2020-03-20"), "Switch");

            // Creamos los desarrolladores
            Desarrollador desarrollador1 = crearDesarrollador("Nintendo", "Japón");
            Desarrollador desarrollador2 = crearDesarrollador("Rockstar Games", "Estados Unidos");
            Desarrollador desarrollador3 = crearDesarrollador("CD Projekt", "Polonia");
            Desarrollador desarrollador4 = crearDesarrollador("Ubisoft", "Francia");
            Desarrollador desarrollador5 = crearDesarrollador("Square Enix", "Japón");

            nuevoJuego1.getDesarrolladora().add(desarrollador1);
            nuevoJuego2.getDesarrolladora().add(desarrollador2);
            nuevoJuego3.getDesarrolladora().add(desarrollador4);
            nuevoJuego4.getDesarrolladora().add(desarrollador5);
            nuevoJuego5.getDesarrolladora().add(desarrollador1);
            nuevoJuego6.getDesarrolladora().add(desarrollador3);
            nuevoJuego7.getDesarrolladora().add(desarrollador5);
            nuevoJuego8.getDesarrolladora().add(desarrollador4);
            nuevoJuego9.getDesarrolladora().add(desarrollador5);
            nuevoJuego10.getDesarrolladora().add(desarrollador1);


            // Persistimos las instancias en la base de datos
            em.persist(nuevoJuego1);
            em.persist(nuevoJuego2);
            em.persist(nuevoJuego3);
            em.persist(nuevoJuego4);
            em.persist(nuevoJuego5);
            em.persist(nuevoJuego6);
            em.persist(nuevoJuego7);
            em.persist(nuevoJuego8);
            em.persist(nuevoJuego9);
            em.persist(nuevoJuego10);

            // Persistimos los desarrolladores en la base de datos
            em.persist(desarrollador1);
            em.persist(desarrollador2);
            em.persist(desarrollador3);
            em.persist(desarrollador4);
            em.persist(desarrollador5);

            // Finalizamos la transacción
            em.getTransaction().commit();


        } catch (Exception e) {
            //Si hay algún problema con la transacción, hacemos un rollback al estado anterior
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

    public static Juego crearJuego(String titulo, Date fechaLanz, String Plataforma) {
        Juego nuevoJuego = new Juego();
        nuevoJuego.setTitulo(titulo);
        nuevoJuego.setFechaLanzamiento(fechaLanz);
        nuevoJuego.setPlataforma(Plataforma);
        return nuevoJuego;
    }

    public static Desarrollador crearDesarrollador(String nombre, String pais) {
        Desarrollador nuevoDesarrollador = new Desarrollador();
        nuevoDesarrollador.setNombre(nombre);
        nuevoDesarrollador.setPais(pais);
        return nuevoDesarrollador;
    }

}
