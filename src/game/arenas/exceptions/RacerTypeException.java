package game.arenas.exceptions;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * This exception extends from exception class (the class take care about exception)
 * In racer type exception we are checking if the racer type feet's the requirement of the arena (land,naval,air)
 */
public class RacerTypeException extends Exception {
    /**
     * @param message the throw message
     */
    public RacerTypeException(String message) {
        super(message);
    }
}
