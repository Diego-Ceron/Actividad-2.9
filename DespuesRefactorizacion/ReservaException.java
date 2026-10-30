public class ReservaException extends RuntimeException {

    //TECNICA UTILIZADA: Replace error code with exception
    //REFACTOR: Se crea una clase para mandar excepciones en vez de usar numeros
    //para representar errores, dentro de cada llamada a la excepcion se incluye
    //un mensaje descriptivo del error.
    public ReservaException(String mensaje) {
        super(mensaje);
    }
}
