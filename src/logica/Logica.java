package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import sun.java2d.loops.CustomComponent;

public class Logica {
    
    public static float calcularPromedio(ArrayList<Jugador> Equipo){
        float promedio_mediocampistas = 0;
        for (int i = 0; i < Equipo.size(); i++)
            promedio_mediocampistas += Equipo.get(i).getCalificacion();
        return (promedio_mediocampistas / Equipo.size());
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Codigo para abrir el archivo de entrada
        String nombre_archivo = "prueba.txt";
        File file = new File(nombre_archivo);
        BufferedReader entrada = new BufferedReader(new FileReader(file));
        String linea;
        
        //Leemos la cantidad de jugadores
        int n = Integer.parseInt(entrada.readLine());
        
        //Inicializamos cada equipo
        Equipo Atacantes = new Equipo("A", 1, 3);
        Equipo Defensas = new Equipo("D", 1, 3);
        Equipo Mediocampistas = new Equipo("M", 1, 3);
        
        //Leemos jugador por jugador y lo añadimos al arreglo correspondiente
        for (int i = 0; i < n; i++) {
            //Formato Linea: nombre posicion calificacion
            //Ej: giovanni A 10
            linea = entrada.readLine();
            String[] split = linea.split(" ");
            String nombre = split[0];
            String posicion = split[1];
            int calificacion = Integer.parseInt(split[2]);
            Jugador aux = new Jugador(calificacion, nombre,posicion);
            
            //Añadimos cada jugador al arreglo que le corresponde
            if ("D".equals(posicion)) {
                Defensas.anadirJugador(aux);
            }
            if ("M".equals(posicion)) {
                Mediocampistas.anadirJugador(aux);
            }
            if ("A".equals(posicion)) {
                Atacantes.anadirJugador(aux);
            }
        }
        
        /*
         * La cantidad maxima de jugadores para determinada posicion esta
         * dada por la formula 2.3.1
         */
        int cantidad_maxima_defensas = Defensas.cantidadMaxima();
        int cantidad_maxima_atacantes = Atacantes.cantidadMaxima();
        int cantidad_maxima_mediocampistas = Mediocampistas.cantidadMaxima();
        
        //Comprobamos si se pued formar un equipo
        if (!(cantidad_maxima_defensas
                + cantidad_maxima_atacantes
                + cantidad_maxima_mediocampistas >= 6)) {
            System.out.println("No hay una cantidad suficiente de jugadores para crear un equipo");
            System.exit(0);
        }
        //Ordenamos los atacantes de mayor a menor
        Collections.sort(Atacantes.Jugadores,new Comparator<Jugador>(){
            @Override
            public int compare(Jugador o1, Jugador o2) {
                return o2.calificacion - o1.calificacion;
            }
        });
        //Ordenamos los defensas de mayor a menor
        Collections.sort(Defensas.Jugadores,new Comparator<Jugador>(){
            @Override
            public int compare(Jugador o1, Jugador o2) {
                return o2.calificacion - o1.calificacion;
            }
        });
        //Ordenamos los mediocampistas de mayor a menor
        Collections.sort(Mediocampistas.Jugadores,new Comparator<Jugador>(){
            @Override
            public int compare(Jugador o1, Jugador o2) {
                return o2.calificacion - o1.calificacion;
            }
        });
        //Calculamos los promediosde cada posicion
        float promedio_atacantes        = calcularPromedio(Atacantes.Jugadores);
        float promedio_defensas         = calcularPromedio(Defensas.Jugadores);
        float promedio_mediocampistas   = calcularPromedio(Mediocampistas.Jugadores);

        Equipo Primero;
        Equipo Segundo;
        Equipo Tercero;
        boolean atacantes_mayor_defensas = promedio_atacantes > promedio_defensas;
        boolean atacantes_mayor_mediocampistas = promedio_atacantes > promedio_mediocampistas;
        boolean defensas_mayor_mediocampistas = promedio_defensas > promedio_mediocampistas;
        if(atacantes_mayor_defensas && atacantes_mayor_mediocampistas){
            Primero = Atacantes;
            if(defensas_mayor_mediocampistas){
                Segundo = Defensas;
                Tercero = Mediocampistas;
            }else{
                Segundo = Mediocampistas;
                Tercero = Defensas;
            }
        }else if(!atacantes_mayor_defensas && defensas_mayor_mediocampistas){
            Primero = Defensas;
            if(atacantes_mayor_mediocampistas){
                Segundo = Atacantes;
                Tercero = Mediocampistas;
            }else{
                Segundo = Mediocampistas;
                Tercero = Atacantes;
            }
        }else{
            Primero = Mediocampistas;
            if(atacantes_mayor_defensas){
                Segundo = Atacantes;
                Tercero = Defensas;
            }else{
                Segundo = Defensas;
                Tercero = Atacantes;
            }
        }
        
        //Calculamos la cantidad de jugadores por cada posicion
        int jugadores_disponibles = 6;
        int cantidad_primero = Math.min(Primero.cantidadMaxima(),jugadores_disponibles);
        jugadores_disponibles -= cantidad_primero;
        
        int cantidad_segundo = Math.min(Segundo.cantidadMaxima(),jugadores_disponibles);
        jugadores_disponibles -= cantidad_segundo;
        
        int cantidad_tercero = Math.min(Tercero.cantidadMaxima(), jugadores_disponibles);
        jugadores_disponibles -= cantidad_tercero;
        
        if(cantidad_tercero == 0){
            cantidad_segundo -= 1;
            cantidad_tercero = 1;
        }
        
        for(int i=0;i<cantidad_primero;i++){
            System.out.print(Primero.Jugadores.get(i).getPosicion());
        }
        
        for(int i=0;i<cantidad_segundo;i++){
            System.out.print(Segundo.Jugadores.get(i).getPosicion());
        }
        for(int i=0;i<cantidad_tercero;i++){
            System.out.print(Tercero.Jugadores.get(i).getPosicion());
        }
        
        System.out.println("");
        
        for(int i=0;i<cantidad_primero;i++){
            System.out.print(Primero.Jugadores.get(i).getNombre());
        }
        for(int i=0;i<cantidad_segundo;i++){
            System.out.print(Segundo.Jugadores.get(i).getNombre());
        }
        for(int i=0;i<cantidad_tercero;i++){
            System.out.print(Tercero.Jugadores.get(i).getNombre());
        }
        System.out.println("");
    }
}
