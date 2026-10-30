import java.util.List;

public class Reserva {
    public int id;
    public Cliente cliente;
    public Funcion funcion;
    public List<String> asientos;
    public double precio;
    public boolean cancelada;

    public Reserva(int id, Cliente cliente, Funcion funcion, List<String> asientos) {
        this.id = id;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = asientos;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public double calcularPrecio() {
        double base = funcion.precio * asientos.size();

        if (cliente.socio) {
            return aplicarDescuento(base, 0.15);
        }

        return base;
    }

    //TECNICA UTILIZADA: Parametize method
    // REFACTOR: El metodo se movió a la clase Reserva, es mas adecuado que esta
    // haga el calculo y devuelva el resultado ya que esta clase conoce los parámetros
    // que se usan.
    private double aplicarDescuento(double precio, double porcentaje) {
        return precio - precio * porcentaje;
    }
}