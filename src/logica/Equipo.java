package logica;
import java.util.ArrayList;

public class Equipo {
    private ArrayList<Jugador> Jugadores = new ArrayList<>();
    private String tipo;
    private int minimo_jugadores, maximo_jugadores;

    public String getTipo() {
        return tipo;
    }

    public int cantidadMaxima(){
        return Math.min(this.size(),this.maximo());
    }
    
    public ArrayList<Jugador> getJugadores(){
        return this.Jugadores;
    }
    
    public void remove(int i){
        Jugadores.remove(i);
    }
    public Jugador get(int i){
        return this.Jugadores.get(i);
    }
    
    public int maximo(){
        return maximo_jugadores;
    }
    
    public int size(){
        return Jugadores.size();
    }
    
    public void anadirJugador(Jugador aux){
        Jugadores.add(aux);
    }
    
    public Equipo(String tipo, int minimo_jugadores, int maximo_jugadores) {
        this.tipo = tipo;
        this.minimo_jugadores = minimo_jugadores;
        this.maximo_jugadores = maximo_jugadores;
    }
    
}
