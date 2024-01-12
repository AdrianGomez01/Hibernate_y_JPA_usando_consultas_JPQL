package bbdd;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
@NamedQuery(
        name = "Juego.findAll",
        query = "SELECT j FROM Juego j"
)

@Entity
public class Juego {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @Basic
    @Column(name = "fecha_lanzamiento", nullable = false)
    private Date fechaLanzamiento;
    @Basic
    @Column(name = "plataforma", nullable = false, length = 100)
    private String plataforma;

    //Creamos la relación entre Friki y Juegos (N:M)
    @ManyToMany
    //Introducimos en la tabla auxiliar de la relación los ids de ambas tablas relacionadas
    @JoinTable (
            name = "juego_desarrollador",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "desarrollador_id")
    )
    private Set<Desarrollador> desarrolladora = new HashSet<>();

    public Set<Desarrollador> getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(Set<Desarrollador> desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Juego juego = (Juego) o;

        if (id != juego.id) return false;
        if (titulo != null ? !titulo.equals(juego.titulo) : juego.titulo != null) return false;
        if (fechaLanzamiento != null ? !fechaLanzamiento.equals(juego.fechaLanzamiento) : juego.fechaLanzamiento != null)
            return false;
        if (plataforma != null ? !plataforma.equals(juego.plataforma) : juego.plataforma != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (fechaLanzamiento != null ? fechaLanzamiento.hashCode() : 0);
        result = 31 * result + (plataforma != null ? plataforma.hashCode() : 0);
        return result;
    }
}
