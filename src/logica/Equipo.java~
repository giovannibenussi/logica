package logica;

import java.util.ArrayList;

public class Equipo {
    ArrayList<Jugador> Jugadores = new ArrayList<>();
    String tipo;
    int minimo_jugadores, maximo_jugadores;

    public int cantidadMaxima(){
        return Math.min(this.size(),this.maximo());
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
