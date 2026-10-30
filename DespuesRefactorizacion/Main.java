import java.util.List;

public class Main {
    public static void main(String[] args) {
        SistemaReservas s = new SistemaReservas();
        Funcion f = new Funcion("Interestelar", 3, "20:30", 12.0);
        
        // Se crean los clientes para pasar el objeto completo al método reserva
        Cliente cliente1 = new Cliente("Ana", "ana@mail.com", true);
        // Se crean las listas del objeto asiento, para luego pasar la lista como parametro
        List<Asiento> asientos1 = List.of(new Asiento('F', 5), new Asiento('F', 6));
        int r1 = s.hacerReserva(
            cliente1,
            f,
            asientos1,
            "App");
        System.out.println("Reserva Ana: " + r1);

        // F7 se marca como ocupado antes de fallar en F6 (bug)
        Cliente cliente2 = new Cliente("luis", "luis@mail.com", false);
        List<Asiento> asientos2 = List.of(new Asiento('F', 7), new Asiento('F', 6));
        int r2 = s.hacerReserva(
            cliente2,
            f,
            asientos2,
            "Taquilla");
        System.out.println("Reserva Luis: " + r2);
        System.out.println("Ocupados: " + f.obtenerOcupados());

        Reserva ana = (Reserva) s.buscar(1);
        System.out.println("Precio Ana (socio): " + ana.precio);

        System.out.println("Cancelar 1: " + s.cancelar(1));
        System.out.println("Cancelar 99: " + s.cancelar(99));
    }
}