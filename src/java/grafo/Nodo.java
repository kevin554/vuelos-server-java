package grafo;

import diccionarios.Diccionario;
import diccionarios.DiccionarioSecuencia;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nodo<K, V, C extends Number> {

    private K ID;
    private V valor;
    private Diccionario<K, Arista<K, V, C>> aristas;

    public Nodo() {
        init();
    }

    public Nodo(K ID, V valor) {
        this.ID = ID;
        this.valor = valor;
        init();
    }
    
    private void init() {
        aristas = new DiccionarioSecuencia<>();
    }
    
    public void insertarArista(Nodo<K, V, C> nodo, C costo) {
        Arista<K, V, C> arista = new Arista<>(costo, nodo);
        
        try {
            aristas.insertar(nodo.getID(), arista);
        } catch (Exception ex) {
            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarArista(K ID) {
        try {
            aristas.eliminar(ID);
        } catch (Exception ex) {
            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean contieneArista(K ID) {
        try {
            return aristas.contieneLlave(ID);
        } catch (Exception ex) {
            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Arista> obtenerAristas() {
        return new ArrayList<>(aristas.obtenerValores());
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters y setters">

    public K getID() {
        return ID;
    }

    public void setID(K ID) {
        this.ID = ID;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public Diccionario<K, Arista<K, V, C>> getAristas() {
        return aristas;
    }

    public void setAristas(Diccionario<K, Arista<K, V, C>> aristas) {
        this.aristas = aristas;
    }

    // </editor-fold>

    @Override
    public String toString() {
        String s = ID.toString() + " [";
        
        for (Arista<K, V, C> arista : aristas.obtenerValores())
            s+= arista.toString() + "";
        
        s += "]\n";
        
        return s;
    }
    
}