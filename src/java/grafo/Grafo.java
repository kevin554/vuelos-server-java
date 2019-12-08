package grafo;

import diccionarios.Diccionario;
import diccionarios.DiccionarioSecuencia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grafo<K, V, C extends Number> {

    private Diccionario<K, Nodo<K, V, C>> vertices;
    
    public Grafo() {
        init();
    }

    private void init() {
        vertices = new DiccionarioSecuencia<>();
    }

    public void insertarVertice(K llave, V valor) {
        try {
            vertices.insertar(llave, new Nodo(llave, valor));
        } catch (Exception ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public V obtener(K llave) {
        try {
            return vertices.obtener(llave).getValor();
        } catch (Exception ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public V eliminarVertice(K llave) {
        try {
            List<Nodo<K, V, C>> nodos = vertices.obtenerValores();
            for (Nodo<K, V, C> nodo : nodos) {
                if (nodo.contieneArista(llave))
                    nodo.eliminarArista(llave);
            }
            
            Nodo<K, V, C> nodoEliminado = vertices.eliminar(llave);
            
            return nodoEliminado.getValor();
        } catch (Exception ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void insertarArista(K origen, K destino, C costo) {
        try {
            Nodo<K, V, C> nodoOrigen = vertices.obtener(origen);
            Nodo<K, V, C> nodoDestino = vertices.obtener(destino);
            
            nodoOrigen.insertarArista(nodoDestino, costo);
        } catch (Exception ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarArista(K origen, K destino) {
        try {
            Nodo<K, V, C> nodoOrigen = vertices.obtener(origen);
            nodoOrigen.eliminarArista(destino);
        } catch (Exception ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<K> getIdsVertices() {
        return vertices.obtenerLlaves();
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters y setters">

    public Diccionario<K, Nodo<K, V, C>> getVertices() {
        return vertices;
    }

    public void setVertices(Diccionario<K, Nodo<K, V, C>> vertices) {
        this.vertices = vertices;
    }
    
    // </editor-fold>

    @Override
    public String toString() {
        String s = "";
        
        for (Nodo<K, V, C> nodo : vertices.obtenerValores())
            s += nodo.toString() + "";
        
        s += "\n";

        return s;
    }
    
}