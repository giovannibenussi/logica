package logica;

import java.util.Comparator;

/**
 *
 * @author gbenussi
 */
public class Jugador implements Comparator<Jugador> {
    private int calificacion;
    private String nombre;
    private String Posicion;

    public String getPosicion() {
        return Posicion;
    }

    public Jugador(int calificacion, String nombre, String Posicion) {
        this.calificacion = calificacion;
        this.nombre = nombre;
        this.Posicion = Posicion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int compare(Jugador o1, Jugador o2) {
        return o1.calificacion - o2.calificacion;
    }

}
