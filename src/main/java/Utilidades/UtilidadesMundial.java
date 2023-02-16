package Utilidades;

import Modelo.*;

import java.time.LocalDate;
import java.util.*;

public class UtilidadesMundial {

    //Ejercicio 1
    public static Map<Seleccion, List<Jugador>> crearMapa() {
        Pais españa = new Pais("España");
        Seleccion seleccionespañola = new Seleccion("España", Continente.EUROPA, españa);
        Pais argentina = new Pais("Argentina");
        Seleccion seleccionargentina = new Seleccion("Argentina", Continente.AMERICA, argentina);
        Pais francia = new Pais("Francia");

        Liga santander = new Liga("Santander", españa);
        Liga league1 = new Liga("League 1", francia);

        Jugador pedri = new Jugador("Pedri", LocalDate.of(2002, 9, 10), Posicion.MC, santander);
        Jugador unaiSimon = new Jugador("Unai Simon", LocalDate.of(2003, 5, 12), Posicion.PT, santander);
        Jugador messi = new Jugador("Messi", LocalDate.of(1985, 3, 24), Posicion.DL, league1);
        Jugador diMaria = new Jugador("Di Maria", LocalDate.of(1980, 12, 30), Posicion.DL, league1);


        List<Jugador> jugadoresEspaña = List.of(pedri, unaiSimon);
        List<Jugador> jugadoresArgentina = List.of(messi, diMaria);

        Map<Seleccion, List<Jugador>> jugadoresseleccion = new HashMap<>();

        jugadoresseleccion.put(seleccionespañola, jugadoresEspaña);
        jugadoresseleccion.put(seleccionargentina, jugadoresArgentina);
        return jugadoresseleccion;

    }
    //Ejercicio 2

    public static List<Jugador> getPorPaisyPosicion(Map<Seleccion, List<Jugador>> mapa, Pais pais, Posicion posicion) {
        List<Jugador> jugadoresfiltrados = new ArrayList<>();
        for (Seleccion seleccion : mapa.keySet()) {
            if (seleccion.getPais().equals(pais)) {
                for (Jugador jug : mapa.get(seleccion)) {
                    if (jug.getPosicion().equals(posicion)) {
                        jugadoresfiltrados.add(jug);
                    }
                }
            }
        }
        return jugadoresfiltrados;

    }

    //Ejercicio 3
    public static Map<Pais, List<Seleccion>> getSeleccionesPais(Map<Seleccion, List<Jugador>>mapa){
        Map<Pais, List<Seleccion>> paisselecciones = new HashMap<>();
        for (Seleccion selecc:mapa.keySet()){
            paisselecciones.put(selecc.getPais(), new ArrayList<>());
            paisselecciones.get(selecc.getPais()).add(selecc);
        }
    System.out.println(paisselecciones);
    return paisselecciones;
    }




    //Ejercicio 4
    public static Map<Continente, List<Liga>>getLigasPorPais(Map<Seleccion,List<Jugador>> mapa){
        Map<Continente, List<Liga>> ligasporcontinentes = new HashMap<>();
        for (Seleccion sel: mapa.keySet()){
            if (ligasporcontinentes.containsKey(sel.getContinente())){
                continue;
            }
            else {
                ligasporcontinentes.put(sel.getContinente(), new ArrayList<>());
            }
            for(List<Jugador> lista:mapa.values()){
                for (Jugador jug: lista){
                    if(sel.getPais().equals(jug.getLiga().getPais())){
                        if (! (ligasporcontinentes.get(sel.getContinente()).contains(jug.getLiga()))){
                            ligasporcontinentes.get(sel.getContinente()).add(jug.getLiga());
                        }
                    }
                }
            }
        }
        System.out.println(ligasporcontinentes);
        return ligasporcontinentes;
    }
    public static void main(String[] args) {
        getLigasPorPais(crearMapa());
    }
}
