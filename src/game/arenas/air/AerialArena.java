package game.arenas.air;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.Airplane;
import game.racers.air.Helicopter;
import utilities.EnumContainer.Height;
import utilities.EnumContainer.Vision;
import utilities.EnumContainer.Weather;
import utilities.EnumContainer.Wind;

import java.util.Observable;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * That class extends from arena class, building all the variable that feet's the Aerial arena class
 * Every arena class will override the method add racer (this method will add new racer only if the racer's type feet's the arena
 * And if the arena is not full
 */
public class AerialArena extends Arena {
    static final double DEFAULT_FRICTION = 0.4;
    static final int DEFAULT_MAX_RACERS = 6;
    final static int DEFAULT_LENGTH = 1500;
    private Vision vision = Vision.SUNNY;
    private Weather weather = Weather.DRY;
    private Height height = Height.HIGH;
    private Wind wind = Wind.HIGH;
    @Override
    public void update(Observable o, Object arg) {

    }

    public AerialArena() {
        super(DEFAULT_LENGTH, DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
    }

    /**
     * @param length set the length of the arena
     * @param maxRacers set the max racers of the arena
     */
    public AerialArena(double length, int maxRacers) {
        super(length, maxRacers, DEFAULT_FRICTION);
    }

    /**
     * @return return the vision
     */
    public Vision getVision() {
        return vision;
    }

    /**
     * @param vision set the vision
     */
    public void setVision(Vision vision) {
        this.vision = vision;
    }

    /**
     * @return return the weather
     */
    public Weather getWeather() {
        return weather;
    }

    /**
     * @param weather set the weather
     */
    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    /**
     * @return return the height
     */
    public Height getHeight() {
        return height;
    }

    /**
     * @param height set the height
     */
    public void setHeight(Height height) {
        this.height = height;
    }

    /**
     * @return return the wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * @param wind set the wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * @param newRacer racer var chosen by the user
     * @throws RacerLimitException racer limit exception
     * @throws RacerTypeException racer type exception
     */
    @Override
    public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {

        if (!(newRacer instanceof Airplane) && !(newRacer instanceof Helicopter)) {
            throw new RacerTypeException("Invalid Racer of type \"" + newRacer.getClass().getSimpleName() + "\" for Aerial arena.");
        }
        if (super.getActiveRacers().size() >= super.getMAX_RACERS()) {
            throw new RacerLimitException(super.getMAX_RACERS(), newRacer.getSerialNumber());
        }
        super.getActiveRacers().add(newRacer);
    }
}
