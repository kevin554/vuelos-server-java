package logica;

import grafo.Grafo;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Administrador extends Observable {

    private static Administrador instancia;
    private Grafo<String, String, Integer> vuelosTiempo;
    private Grafo<String, String, Integer> vuelosPrecio;

    public static Administrador getInstancia() {
        if (instancia == null)
            instancia = new Administrador();
        
        return instancia;
    }
    
    private Administrador() {
        vuelosTiempo = new Grafo<>();
        vuelosPrecio = new Grafo<>();
        
        vuelosTiempo.insertarVertice("LP", "La Paz");
        vuelosTiempo.insertarVertice("CB", "Cochabamba");
        vuelosTiempo.insertarVertice("SC", "Santa Cruz");
        
        vuelosPrecio.insertarVertice("LP", "La Paz");
        vuelosPrecio.insertarVertice("CB", "Cochabamba");
        vuelosPrecio.insertarVertice("SC", "Santa Cruz");
        
        vuelosPrecio.insertarArista("LP", "CB", 120);
        vuelosPrecio.insertarArista("SC", "LP", 400);
        vuelosPrecio.insertarArista("LP", "SC", 440);
        vuelosPrecio.insertarArista("CB", "SC", 140);
        vuelosPrecio.insertarArista("SC", "CB", 150);
        
        vuelosTiempo.insertarArista("LP", "CB", 40);
        vuelosTiempo.insertarArista("SC", "LP", 30);
        vuelosTiempo.insertarArista("LP", "SC", 35);
        vuelosTiempo.insertarArista("CB", "SC", 45);
        vuelosTiempo.insertarArista("SC", "CB", 40);
    }

    public List<String> obtenerPaises() {
        List<String> paises = new ArrayList<>();
        
        List<String> idsVertices = vuelosTiempo.getIdsVertices();
        
        for (int i = 0; i < idsVertices.size(); i++) {
            String id = idsVertices.get(i);
            
            String pais = vuelosTiempo.obtener(id);
            paises.add(pais);
        }
        
        return paises;
    }

    public List<String> obtenerIDsPaises() {
        return vuelosPrecio.getIdsVertices();
    }
    
    public void insertarLugar(String ID, String lugar) {
        vuelosPrecio.insertarVertice(ID, lugar);
        vuelosTiempo.insertarVertice(ID, lugar);
    }

    public void insertarTramo(String origen, String destino, String tiempo, String costo) {
        try {
            vuelosPrecio.insertarArista(origen, destino, Integer.parseInt(costo));
            vuelosTiempo.insertarArista(origen, destino, Integer.parseInt(tiempo));
        } catch (NumberFormatException e) {
        }
    }

    public void eliminarLugar(String ID) {
        vuelosTiempo.eliminarVertice(ID);
        vuelosPrecio.eliminarVertice(ID);
    }

    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    
    public Grafo<String, String, Integer> getVuelosTiempo() {
        return vuelosTiempo;
    }

    public void setVuelosTiempo(Grafo<String, String, Integer> vuelosTiempo) {
        this.vuelosTiempo = vuelosTiempo;
    }

    public Grafo<String, String, Integer> getVuelosPrecio() {
        return vuelosPrecio;
    }

    public void setVuelosPrecio(Grafo<String, String, Integer> vuelosPrecio) {
        this.vuelosPrecio = vuelosPrecio;
    }
    
    // </editor-fold>

}