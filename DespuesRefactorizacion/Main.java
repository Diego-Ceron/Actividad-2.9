public class Main {
    public static void main(String[] args) {
        SistemaReservas s = new SistemaReservas();
        Funcion f = new Funcion("Interestelar", 3, "20:30", 12.0);
        
        // Se crean los clientes para pasar el objeto completo al método reserva
        Cliente cliente1 = new Cliente("Ana", "ana@mail.com", true);
        int r1 = s.hacerReserva(
            cliente1,
            f,
            new char[]{'F', 'F'},
            new int[]{5, 6},
            "App");
        System.out.println("Reserva Ana: " + r1);

        // F7 se marca como ocupado antes de fallar en F6 (bug)
        Cliente cliente2 = new Cliente("luis", "luis@mail.com", false);
        int r2 = s.hacerReserva(
            cliente2,
            f,
            new char[]{'F', 'F'},
            new int[]{7, 6},
            "Taquilla");
        System.out.println("Reserva Luis: " + r2);
        System.out.println("Ocupados: " + f.ocupados);

        Reserva ana = (Reserva) s.buscar(1);
        System.out.println("Precio Ana (socio): " + ana.precio);

        System.out.println("Cancelar 1: " + s.cancelar(1));
        System.out.println("Cancelar 99: " + s.cancelar(99));
    }
}