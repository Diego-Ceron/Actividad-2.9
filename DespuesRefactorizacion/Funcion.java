import java.util.ArrayList;
import java.util.List;

public class Funcion {
    public String titulo;
    public int sala;
    public String hora;
    public double precio;
    private List<String> ocupados = new ArrayList<>();

    public Funcion(String titulo, int sala, String hora, double precio) {
        this.titulo = titulo;
        this.sala = sala;
        this.hora = hora;
        this.precio = precio;
    }

//TECNICA UTILIZADA: Separate query from modifier
//REFACTOR: El metodo 'ocuparasiento' ahora solo hace la reserva del asiento mientras
// que 'estaDisponible' es el nuevo metodo de consulta para saber si esta libre el asiento.
    public void ocuparAsiento(char fila, int numero) {
        String codigo = "" + fila + numero;
        ocupados.add(codigo);
    }

    public boolean estaDisponible(char fila, int numero) {
        String codigo = "" + fila + numero;
        return !ocupados.contains(codigo);
    }

    //TECNICA UTILIZADA: Hide method
    //REFACTOR: Se hizo privada la lista de ocupados, se proporcionan metodos para leer y modificar
    //la lista, de esta manera ninguna clase puede modificarla directamente.
    public List<String> obtenerOcupados() {
        return new ArrayList<>(ocupados);
    }

    public void liberarAsiento(String codigo) {
        ocupados.remove(codigo);
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
