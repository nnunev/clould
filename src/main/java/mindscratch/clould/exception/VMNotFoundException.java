package mindscratch.clould.exception;

public class VMNotFoundException extends RuntimeException {

    public VMNotFoundException(Long id) {
        super("Virtual machine not found: " + id);
    }
}