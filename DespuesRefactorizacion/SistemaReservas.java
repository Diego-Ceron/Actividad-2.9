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
    // TÉCNICA UTILIZADA: Remove parameter
    //REFACTOR: Al aplicar la técnica se elimina el parámetro sin usar 'boolean imprimir'
    // TÉCNICA UTILIZADA: Preserve whole object
    // REFACTOR: Se pasa el objeto cliente en vez de cada uno de sus parámetros individualmente
    public int hacerReserva(Cliente cliente, Funcion f,
                     char[] filas, int[] numeros, String canal) {
        if (cliente == null || f == null) return -1;
        if (filas.length == 0 || filas.length != numeros.length) return -2;
        try {
            List<String> lista = new ArrayList<>();
            for (int i = 0; i < filas.length; i++) {
                f.ocupar(filas[i], numeros[i]);   // si falla a mitad, los anteriores ya quedaron ocupados
                lista.add("" + filas[i] + numeros[i]);
            }
            Reserva r = new Reserva(contador, cliente, f, lista);
            r.precio = calcularPrecio(r, cliente.socio ? "socio" : "general");
            db.put(contador, r);
            contador++;
            return r.id;
        } catch (IllegalStateException e) {
            return -3;
        }
    }

    // Tipo de tarifa como String y if/else
    public double calcularPrecio(Reserva r, String tipo) {
        double base = r.funcion.precio * r.asientos.size();
        if (tipo.equals("general")) {
            return base;
        } else if (tipo.equals("socio")) {
            return descuentoDel15(base);
        }
        return base;
    }

    public double descuentoDel15(double p) {
        return p - p * 0.15;
    }

    public double descuentoDel25(double p) {   // duplicado, sin uso
        return p - p * 0.25;
    }

    // Downcast repetido en cada llamada
    public int cancelar(int id) {
        Reserva r = (Reserva) db.get(id);
        if (r == null) return -1;
        if (r.cancelada) return -2;
        for (String a : r.asientos) {
            r.funcion.ocupados.remove(a);
        }
        r.setCancelada(true);
        return 0;
    }

    public Object buscar(int id) {
        return db.get(id);
    }
}
