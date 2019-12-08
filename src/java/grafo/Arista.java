package grafo;

public class Arista<K, V, C extends Number> {

    private C costo;
    private Nodo<K, V, C> destino;

    public Arista() {
    }

    public Arista(C costo, Nodo<K, V, C> destino) {
        this.costo = costo;
        this.destino = destino;
    }

    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    
    public C getCosto() {
        return costo;
    }

    public void setCosto(C costo) {
        this.costo = costo;
    }

    public Nodo<K, V, C> getDestino() {
        return destino;
    }

    public void setDestino(Nodo<K, V, C> destino) {
        this.destino = destino;
    }

    // </editor-fold>

    @Override
    public String toString() {
        return "{" + destino.getID() + " (" + costo.toString() + ")}" ;
    }
    
}