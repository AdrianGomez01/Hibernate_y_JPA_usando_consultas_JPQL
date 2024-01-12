package bbdd;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Juego {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id", nullable = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "fecha_lanzamiento", nullable = false)
    private Date fechaLanzamiento;

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Basic
    @Column(name = "plataforma", nullable = false, length = 100)
    private String plataforma;

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
