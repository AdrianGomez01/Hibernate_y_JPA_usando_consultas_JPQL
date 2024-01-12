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

            // Crear 10 registros en JuegoDesarrollador (asumo que los ids de juegos y desarrolladores ya existen)
            JuegoDesarrollador registro1 = crearJuegoDesarrollador(1, 1);
            JuegoDesarrollador registro2 = crearJuegoDesarrollador(1, 2);
            JuegoDesarrollador registro3 = crearJuegoDesarrollador(2, 3);
            JuegoDesarrollador registro4 = crearJuegoDesarrollador(3, 4);
            JuegoDesarrollador registro5 = crearJuegoDesarrollador(4, 5);
            JuegoDesarrollador registro6 = crearJuegoDesarrollador(2, 1);
            JuegoDesarrollador registro7 = crearJuegoDesarrollador(3, 2);
            JuegoDesarrollador registro8 = crearJuegoDesarrollador(4, 3);
            JuegoDesarrollador registro9 = crearJuegoDesarrollador(5, 4);
            JuegoDesarrollador registro10 = crearJuegoDesarrollador(5, 5);


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

            // Persistimos los registros en la tabla JuegoDesarrollador
            em.persist(registro1);
            em.persist(registro2);
            em.persist(registro3);
            em.persist(registro4);
            em.persist(registro5);
            em.persist(registro6);
            em.persist(registro7);
            em.persist(registro8);
            em.persist(registro9);
            em.persist(registro10);


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


    public static JuegoDesarrollador crearJuegoDesarrollador(int juegoId, int desarrolladorId) {
        JuegoDesarrollador nuevoJuegoDesarrollador = new JuegoDesarrollador();
        nuevoJuegoDesarrollador.setJuegoId(juegoId);
        nuevoJuegoDesarrollador.setDesarrolladorId(desarrolladorId);
        return nuevoJuegoDesarrollador;
    }

}
