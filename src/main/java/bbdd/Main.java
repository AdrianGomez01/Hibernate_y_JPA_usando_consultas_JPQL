package bbdd;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

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

            // Menú de opciones
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n----- Menú de Consultas -----\n");
                System.out.println("1. Mostrar todos los juegos");
                System.out.println("2. Mostrar todos los desarrolladores");
                System.out.println("3. Mostrar juegos desarrollados por un desarrollador específico");
                System.out.println("4. Mostrar nombres de desarrolladores de un juego específico");
                System.out.println("5. Actualizar el título de un juego");
                System.out.println("6. Eliminar un juego");
                System.out.println("7. Mostrar juegos lanzados antes de una fecha determinada");
                System.out.println("8. Mostrar juegos lanzados después de una fecha determinada");
                System.out.println("9. Mostrar juegos lanzados en un rango de fechas determinado");
                System.out.println("10. Mostrar juegos lanzados en una plataforma determinada");
                System.out.println("0. Salir");

                System.out.print("Ingrese la opción deseada: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después del nextInt

                switch (opcion) {
                    case 1:
                        mostrarTodosLosJuegos();
                        break;
                    case 2:
                        mostrarTodosLosDesarrolladores();
                        break;
                    case 3:
                        mostrarJuegosPorDesarrollador();
                        break;
                    case 4:
                        mostrarDesarrolladoresPorJuego();
                        break;
                    case 5:
                        actualizarTituloDeJuego();
                        break;
                    case 6:
                        eliminarJuego();
                        break;
                    case 7:
                        mostrarJuegosAntesDeFecha();
                        break;
                    case 8:
                        mostrarJuegosDespuesDeFecha();
                        break;
                    case 9:
                        mostrarJuegosEnRangoDeFechas();
                        break;
                    case 10:
                        mostrarJuegosPorPlataforma();
                        break;
                }

            } while (opcion != 0);


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

    // Métodos de consulta
    private static void mostrarTodosLosJuegos() {
        List<Juego> juegos = em.createNamedQuery("Juego.findAll", Juego.class).getResultList();
        System.out.println("----- Todos los Juegos -----");
        for (Juego juego : juegos) {
            System.out.println(juego);
        }
    }

    private static void mostrarTodosLosDesarrolladores() {
        List<Desarrollador> desarrolladores = em.createNamedQuery("Desarrollador.findAll", Desarrollador.class).getResultList();
        System.out.println("----- Todos los Desarrolladores -----");
        for (Desarrollador desarrollador : desarrolladores) {
            System.out.println(desarrollador);
        }
    }

    private static void mostrarJuegosPorDesarrollador() {
        // Implementar la consulta para mostrar juegos por desarrollador
        // ...
    }

    private static void mostrarDesarrolladoresPorJuego() {
        // Implementar la consulta para mostrar desarrolladores por juego
        // ...
    }

    private static void actualizarTituloDeJuego() {
        // Implementar la consulta para actualizar el título de un juego
        // ...
    }

    private static void eliminarJuego() {
        // Implementar la consulta para eliminar un juego
        // ...
    }

    private static void mostrarJuegosAntesDeFecha() {
        // Implementar la consulta para mostrar juegos antes de una fecha determinada
        // ...
    }

    private static void mostrarJuegosDespuesDeFecha() {
        // Implementar la consulta para mostrar juegos después de una fecha determinada
        // ...
    }

    private static void mostrarJuegosEnRangoDeFechas() {
        // Implementar la consulta para mostrar juegos en un rango de fechas determinado
        // ...
    }

    private static void mostrarJuegosPorPlataforma() {
        // Implementar la consulta para mostrar juegos por plataforma
        // ...
    }
}
