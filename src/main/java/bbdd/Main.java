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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        List<Juego> juegos = em.createNamedQuery("Juego.findAll", Juego.class).getResultList();

        if (!juegos.isEmpty()) {
            System.out.println("----- Todos los Juegos -----");
            for (Juego juego : juegos) {
                System.out.println(juego);
            }
        } else {
            System.out.println("No hay juegos registrados en la base de datos.");
        }
    }

    private static void mostrarTodosLosDesarrolladores() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        List<Desarrollador> desarrolladores = em.createNamedQuery("Desarrollador.findAll", Desarrollador.class).getResultList();

        if (!desarrolladores.isEmpty()) {
            System.out.println("----- Todos los Desarrolladores -----");
            for (Desarrollador desarrollador : desarrolladores) {
                System.out.println(desarrollador);
            }
        } else {
            System.out.println("No hay desarrolladores registrados en la base de datos.");
        }
    }

    private static void mostrarJuegosPorDesarrollador() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del desarrollador: ");
        int desarrolladorId = scanner.nextInt();
        String jpql = "SELECT j FROM Juego j JOIN j.desarrolladora d WHERE d.id = :desarrolladorId";
        List<Juego> juegos = em.createQuery(jpql, Juego.class)
                .setParameter("desarrolladorId", desarrolladorId)
                .getResultList();

        System.out.println("----- Juegos desarrollados por el Desarrollador con ID " + desarrolladorId + " -----");
        for (Juego juego : juegos) {
            System.out.println(juego);
        }
    }

    private static void mostrarDesarrolladoresPorJuego() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del juego: ");
        int juegoId = scanner.nextInt();

        Juego juego = em.find(Juego.class, juegoId);
        if (juego != null) {
            System.out.println("----- Desarrolladores del Juego con ID " + juegoId + " -----");
            for (Desarrollador desarrollador : juego.getDesarrolladora()) {
                System.out.println(desarrollador);
            }
        } else {
            System.out.println("No se encontró un juego con ID " + juegoId);
        }
    }

    private static void actualizarTituloDeJuego() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del juego a actualizar: ");
        int juegoId = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del nextInt

        System.out.print("Ingrese el nuevo título del juego: ");
        String nuevoTitulo = scanner.nextLine();

        em.getTransaction().begin();
        String jpql = "UPDATE Juego j SET j.titulo = :nuevoTitulo WHERE j.id = :juegoId";
        em.createQuery(jpql)
                .setParameter("nuevoTitulo", nuevoTitulo)
                .setParameter("juegoId", juegoId)
                .executeUpdate();
        em.getTransaction().commit();

        System.out.println("Título del juego actualizado con éxito.");
    }

    private static void eliminarJuego() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del juego a eliminar: ");
        int juegoId = scanner.nextInt();

        em.getTransaction().begin();
        Juego juego = em.find(Juego.class, juegoId);
        em.remove(juego);
        em.getTransaction().commit();

        System.out.println("Juego eliminado con éxito.");
    }

    private static void mostrarJuegosAntesDeFecha() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha límite (Formato: yyyy-MM-dd): ");
        Date fechaLimite = Date.valueOf(scanner.nextLine());

        String jpql = "SELECT j FROM Juego j WHERE j.fechaLanzamiento < :fechaLimite";
        List<Juego> juegos = em.createQuery(jpql, Juego.class)
                .setParameter("fechaLimite", fechaLimite)
                .getResultList();

        System.out.println("----- Juegos lanzados antes de " + fechaLimite + " -----");
        for (Juego juego : juegos) {
            System.out.println(juego);
        }
    }

    private static void mostrarJuegosDespuesDeFecha() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha de referencia (Formato: yyyy-MM-dd): ");
        Date fechaReferencia = Date.valueOf(scanner.nextLine());

        String jpql = "SELECT j FROM Juego j WHERE j.fechaLanzamiento > :fechaReferencia";
        List<Juego> juegos = em.createQuery(jpql, Juego.class)
                .setParameter("fechaReferencia", fechaReferencia)
                .getResultList();

        System.out.println("----- Juegos lanzados después de " + fechaReferencia + " -----");
        for (Juego juego : juegos) {
            System.out.println(juego);
        }
    }

    private static void mostrarJuegosEnRangoDeFechas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha de inicio (Formato: yyyy-MM-dd): ");
        Date fechaInicio = Date.valueOf(scanner.nextLine());
        System.out.print("Ingrese la fecha de fin (Formato: yyyy-MM-dd): ");
        Date fechaFin = Date.valueOf(scanner.nextLine());

        String jpql = "SELECT j FROM Juego j WHERE j.fechaLanzamiento BETWEEN :fechaInicio AND :fechaFin";
        List<Juego> juegos = em.createQuery(jpql, Juego.class)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();

        System.out.println("----- Juegos lanzados en el rango de fechas " + fechaInicio + " a " + fechaFin + " -----");
        for (Juego juego : juegos) {
            System.out.println(juego);
        }
    }

    private static void mostrarJuegosPorPlataforma() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la plataforma: ");
        String plataforma = scanner.nextLine();

        String jpql = "SELECT j FROM Juego j WHERE j.plataforma = :plataforma";
        List<Juego> juegos = em.createQuery(jpql, Juego.class)
                .setParameter("plataforma", plataforma)
                .getResultList();

        System.out.println("----- Juegos lanzados en la plataforma " + plataforma + " -----");
        for (Juego juego : juegos) {
            System.out.println(juego);
        }
    }
}
