package bbdd;

import java.io.Serializable;
import java.util.Objects;

public class JuegoDesarrolladorPK implements Serializable {

    private int juegoId;
    private int desarrolladorId;

    // Constructor, getters y setters


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JuegoDesarrolladorPK that = (JuegoDesarrolladorPK) o;
        return juegoId == that.juegoId && desarrolladorId == that.desarrolladorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(juegoId, desarrolladorId);
    }
}
