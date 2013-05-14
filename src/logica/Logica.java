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

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String nombre_archivo = "prueba.txt";
        File file = new File(nombre_archivo);
        BufferedReader entrada = new BufferedReader(new FileReader(file));
        String linea;
        int n = Integer.parseInt(entrada.readLine());
        int minimo_atacantes = 1, maximo_atacantes = 3;
        int minimo_defensas = 1, maximo_defensas = 3;
        int minimo_mediocampistas = 1, maximo_mediocampistas = 3;
        ArrayList<Jugador> Atacantes = new ArrayList();
        ArrayList<Jugador> Defensas = new ArrayList();
        ArrayList<Jugador> Mediocampistas = new ArrayList();
        for (int i = 0; i < n; i++) {
            linea = entrada.readLine();
            String[] split = linea.split(" ");
            String nombre = split[0];
            String posicion = split[1];
            String calificacion = split[2];
            Jugador aux = new Jugador(Integer.parseInt(calificacion), nombre,posicion);
            //System.out.println("Nombre: "+nombre+", posicion: "+posicion+", calificacion: "+calificacion);
            if ("D".equals(posicion)) {
                Defensas.add(aux);
            }
            if ("M".equals(posicion)) {
                Mediocampistas.add(aux);
            }
            if ("A".equals(posicion)) {
                Atacantes.add(aux);
            }
        }
        int cantidad_maxima_defensas = Math.min(Defensas.size(), maximo_defensas);
        int cantidad_maxima_atacantes = Math.min(Atacantes.size(), maximo_atacantes);
        int cantidad_maxima_mediocampistas = Math.min(Mediocampistas.size(), maximo_mediocampistas);
        if (!(cantidad_maxima_defensas
                + cantidad_maxima_atacantes
                + cantidad_maxima_mediocampistas >= 6)) {
            System.out.println("No hay una cantidad suficiente de jugadores para crear un equipo");
        }
        int jugadores_disponibles = 6;
        int cantidad_atacantes = Math.min(cantidad_maxima_atacantes, jugadores_disponibles);
        jugadores_disponibles -= cantidad_atacantes;
        int cantidad_defensas = Math.min(cantidad_maxima_defensas, jugadores_disponibles);
        jugadores_disponibles -= cantidad_defensas;
        int cantidad_mediocampistas = Math.min(cantidad_maxima_mediocampistas, jugadores_disponibles);
        jugadores_disponibles -= cantidad_mediocampistas;
        System.out.println("Atacantes: "+cantidad_atacantes+"\nDefensas: "+cantidad_defensas+"\nMediocampistas: "+cantidad_mediocampistas);
        //Ordenamos los atacantes de mayor a menor
        Collections.sort(Atacantes,new Comparator<Jugador>(){
            public int compare(Jugador o1, Jugador o2) {
                return o2.calificacion - o1.calificacion;
            }
        });
        //Ordenamos los defensas de mayor a menor
        Collections.sort(Atacantes,new Comparator<Jugador>(){
            public int compare(Jugador o1, Jugador o2) {
                return o2.calificacion - o1.calificacion;
            }
        });
        //Ordenamos los mediocampistas de mayor a menor
        Collections.sort(Atacantes,new Comparator<Jugador>(){
            public int compare(Jugador o1, Jugador o2) {
                return o2.calificacion - o1.calificacion;
            }
        });
        //Calculamos el promedio de los atacantes
        float promedio_atacantes = 0;
        for (int i = 0; i < cantidad_atacantes; i++)
            promedio_atacantes += Atacantes.get(i).getCalificacion();
        promedio_atacantes = promedio_atacantes / Atacantes.size();
        //Calculamos el promedio de los defensas
        float promedio_defensas = 0;
        for (int i = 0; i < cantidad_defensas; i++)
            promedio_defensas += Atacantes.get(i).getCalificacion();
        promedio_defensas = promedio_defensas / Defensas.size();
        //Calculamos el promedio de los mediocampistas
        float promedio_mediocampistas = 0;
        for (int i = 0; i < cantidad_mediocampistas; i++)
            promedio_mediocampistas += Atacantes.get(i).getCalificacion();
        promedio_mediocampistas = promedio_mediocampistas / Mediocampistas.size();
        ArrayList<Jugador> Primero = new ArrayList<Jugador>();
        ArrayList<Jugador> Segundo = new ArrayList<Jugador>();
        ArrayList<Jugador> Tercero = new ArrayList<Jugador>();
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
        for(int i=0;i<Primero.size();i++){
            System.out.print(Primero.get(i).getPosicion());
        }
        System.out.println("");
        for(int i=0;i<Primero.size();i++){
            System.out.print(Primero.get(i).getNombre());
        }
        for(int i=0;i<Segundo.size();i++){
            System.out.print(Segundo.get(i).getPosicion());
        }
        System.out.println("");
        for(int i=0;i<Segundo.size();i++){
            System.out.print(Segundo.get(i).getNombre());
        }
        for(int i=0;i<Tercero.size();i++){
            System.out.print(Tercero.get(i).getPosicion());
        }
        System.out.println("");
        for(int i=0;i<Tercero.size();i++){
            System.out.print(Tercero.get(i).getNombre());
        }
        System.out.println("");
    }
}
