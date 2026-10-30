import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaReservas {
    public Map<Integer, Object> db = new HashMap<>();
    public int contador = 1;

    // TÉCNICA UTILIZADA: Rename method
    // REFACTOR: Al aplicar la técnica se cambio el nombre poco claro del método por
    // uno mas claro
    // TÉCNICA UTILIZADA: Remove arameter
    //REFACTOR: Al aplicar la técnica se elimina el parámetro sin usar 'boolean imprimir'
    // TÉCNICA UTILIZADA: Preserve whole object
    // REFACTOR: Se pasa el objeto cliente en vez de cada uno de sus parámetros individualmente
    // TECNICA UTILIZADA: Introduce Parameter Object
    // Refactor: Se creó el objeto Asiento, en main se crea una lista de objetos asiento,
    // esa lista se pasa como parámetro al método 'hacerReserva()'
    public int hacerReserva(Cliente cliente, Funcion f, List<Asiento> asientos, String canal) {
        if (cliente == null || f == null) {
            throw new ReservaException("El cliente y la funcion son obligatorios");
        }
        if (asientos == null || asientos.isEmpty()) {
            throw new ReservaException("La reserva debe incluir al menos un asiento");
        }

        List<String> lista = new ArrayList<>();

        // se recorre la lista de asientos sin depender de indices
        for (Asiento asiento : asientos) {
            if (!f.estaDisponible(asiento.fila, asiento.numero)) {
                throw new ReservaException(
                    "El asiento " + asiento + " no esta disponible"
                );
            }
        }

        for (Asiento asiento : asientos) {
            f.ocuparAsiento(asiento.fila, asiento.numero);
            lista.add(asiento.toString());
        }

        Reserva r = new Reserva(contador, cliente, f, lista);
//TECNICA UTILIZADA: Replace parameter with method
// REFACTOR: La funcion calcularPrecio se pasó a la clase Reserva, ya que tiene
// sentido que esta haga el calculo ya que conoce los datos. Al resolver este problema
// se resuelve el problema 7, en el cual la tarifa se elegia con texto. El metodo 'calcularPrecio'
// ahora compara directamente cliente.socio para decidir si aplicar descuento o no.
        r.precio = r.calcularPrecio();
        db.put(contador, r);
        contador++;
        return r.id;
    }

    //TECNICA UTILIZADA: Encapsulate downcast
    //REFACTOR: Se encapsuló el casteo de object a reserva en obtenerReserva(),
    //por lo que los demas metodos funcionan directamente con objetos reserva.
    public void cancelar(int id) {
        Reserva r = obtenerReserva(id);

        if (r == null) {
            throw new ReservaException("No existe la reserva: " + id);
        }
        if (r.cancelada) {
            throw new ReservaException("La reserva " + id + " ya esta cancelada");
        }

        for (String a : r.asientos) {
            r.funcion.liberarAsiento(a);
        }

        r.cancelarReserva();
    }

    private Reserva obtenerReserva(int id) {
        return (Reserva) db.get(id);
    }

    public Reserva buscar(int id) {
        return obtenerReserva(id);
    }
}
