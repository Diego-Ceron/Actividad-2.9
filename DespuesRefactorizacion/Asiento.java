public class Asiento {
    public char fila;
    public int numero;

    public Asiento(char fila, int numero) {
        this.fila = fila;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "" + fila + numero;
    }
}
