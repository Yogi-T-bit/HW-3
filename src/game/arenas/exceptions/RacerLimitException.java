package game.arenas.exceptions;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * This exception extends from exception class (the class take care about exception)
 * In racer limit exception class we are adding new exception that check if the arena is full (no limit racers reach)
 */
public class RacerLimitException extends Exception {
    /**
     * @param ActiveRacers the racers who are active in the race
     * @param currentRacer the racers in the current race
     */
    public RacerLimitException(int ActiveRacers, int currentRacer) {
        super("Arena is full! (" + ActiveRacers + " active racers exist). racer #" + (currentRacer) + " was not added");
    }
}
