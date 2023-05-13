package game.arenas;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 * <p>
 * This class is an abstract class (the father of all the arenas)
 * That contains the racer array (all the racers in the arena) and more methods that relevant for the race
 */
public abstract class Arena implements Observer {
    ArrayList<Racer> activeRacers;
    ArrayList<Racer> completedRacers;
    final double FRICTION;
    final int MAX_RACERS;
    final static int MIN_Y_GAP = 10;
    double length;

    /**
     * @param length    the length of the arena
     * @param maxRacers the max racers can be in the arena
     * @param friction  the friction in the arena
     */
    public Arena(double length, int maxRacers, double friction) {
        this.length = length;
        MAX_RACERS = maxRacers;
        FRICTION = friction;
        activeRacers = new ArrayList<Racer>(maxRacers);
        completedRacers = new ArrayList<Racer>();
    }
    /**
     * @param newRacer racer var chosen by the user
     * @throws RacerLimitException racer limit exception
     * @throws RacerTypeException  racer type exception
     */
    public abstract void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException;


    /**
     * This method prepare the race with setting the constructors of points, the every racer point in the race
     */
    public void initRace() {
            Point start = new Point(0, 0);
            Point end = new Point(0, length);
            for (Racer racer : activeRacers) {
                racer.initRace(this, start, end);
                racer.setCurrentLocation(start);
                start.setY(start.getY() + MIN_Y_GAP);
        }
    }

    /**
     * @return Checking if there is active racers (true if there is)
     */
    public boolean raceIsFinished(){
        if(activeRacers.isEmpty()&&!completedRacers.isEmpty()){
            return true;
        }
        return false;
    }
    public boolean hasActiveRacers() {
        return !activeRacers.isEmpty();
    }

    /**
     * Play one turn in the race
     */
    public void playTurn() {
        for (int i = 0; i < activeRacers.size(); i++) {
            activeRacers.get(i).move(FRICTION);
            if (activeRacers.get(i).getCurrentLocation().getX() >= length) {
                crossFinishLine(activeRacers.get(i));
            }
        }
    }

    /**
     * Checking if the racer finish the race
     */
    public void crossFinishLine(Racer racer) {
        if ((racer.getCurrentLocation().getX() >= racer.getFinish().getX())) {
            completedRacers.add(racer);
            activeRacers.remove(racer);
            System.out.println("active:"+activeRacers);
            System.out.println("completed:"+completedRacers);
            System.out.println(racer.getName()+" has finish the race");
        }
    }

    /**
     * Showing the results of the race
     */
    public void showResults() {
        for (int i = 0; i < completedRacers.size(); i++) {
            System.out.print("#" + i + "-> ");
            completedRacers.get(i).introduce();
        }
    }

    /**
     * @return return the active racers in the arena
     */
    public ArrayList<Racer> getActiveRacers() {
        return activeRacers;
    }

    /**
     * @param activeRacers set the active racers in the arena
     */
    public void setActiveRacers(ArrayList<Racer> activeRacers) {
        this.activeRacers = activeRacers;
    }

    public void addActiveRacer(Racer racer) {
        activeRacers.add(racer);
    }

    /**
     * @return return the racers who complete the race
     */
    public ArrayList<Racer> getCompletedRacers() {
        return completedRacers;
    }

    /**
     * @param completedRacers set the racers who complete the race
     */
    public void setCompletedRacers(ArrayList<Racer> completedRacers) {
        this.completedRacers = completedRacers;
    }

    /**
     * @return return friction
     */
    public double getFRICTION() {
        return FRICTION;
    }

    /**
     * @return return max racers
     */
    public int getMAX_RACERS() {
        return MAX_RACERS;
    }

    /**
     * @return return length
     */
    public double getLength() {
        return length;
    }

    /**
     * @param length set the length of the arena
     */
    public void setLength(double length) {
        this.length = length;
    }

}
