package bbdd;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@jakarta.persistence.Table(name = "juego_desarrollador", schema = "videojuegosjpa", catalog = "")
@jakarta.persistence.IdClass(bbdd.JuegoDesarrolladorPK.class)
public class JuegoDesarrollador {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "juego_id", nullable = false)
    private int juegoId;

    public int getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(int juegoId) {
        this.juegoId = juegoId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "desarrollador_id", nullable = false)
    private int desarrolladorId;

    public int getDesarrolladorId() {
        return desarrolladorId;
    }

    public void setDesarrolladorId(int desarrolladorId) {
        this.desarrolladorId = desarrolladorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JuegoDesarrollador that = (JuegoDesarrollador) o;

        if (juegoId != that.juegoId) return false;
        if (desarrolladorId != that.desarrolladorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = juegoId;
        result = 31 * result + desarrolladorId;
        return result;
    }
}
