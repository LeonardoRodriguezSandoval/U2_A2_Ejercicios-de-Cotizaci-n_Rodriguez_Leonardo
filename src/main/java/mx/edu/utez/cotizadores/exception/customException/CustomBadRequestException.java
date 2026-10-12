package mx.edu.utez.cotizadores.exception.customException;

public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException(String message) {
        super(message);
    }
}
