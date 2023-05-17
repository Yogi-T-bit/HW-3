package game.racers;

import game.arenas.Arena;
import utilities.EnumContainer.Color;
import utilities.Fate;
import utilities.Mishap;
import utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 * <p>
 * This class is an abstract class (the father of all the racers)
 * Contain info about the racer
 */
public abstract class Racer extends Observable implements Runnable {
    private static int count = 1;
    private int serialNumber = count;
    private String name;
    private Point currentLocation;
    private Point finish;
    private Arena arena;
    private double maxSpeed;
    private double acceleration;
    private double currentSpeed;
    private double failureProbability;
    private Color color;
    private Mishap mishap;
    private JLabel img;
    private Thread thread;

    public JLabel getImg() {
        return img;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        this.addObserver(arena);
        while (this.getCurrentLocation().getX() < this.getFinish().getX()) {
            this.move(this.getCurrentSpeed());

            Point mypoint = move(1);
            java.awt.Point point = new java.awt.Point((int) mypoint.getX() - 15 - img.getWidth(), (int) mypoint.getY());
            img.setLocation(point);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers(this);

        }
        arena.crossFinishLine(this);

    }

    public void setImg(JLabel img) {
        this.img = img;
    }


    /**
     * @return return the current location var
     */
    public Point getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation the current location of the racer (x,y)
     */
    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }


    /**
     * @return return the color var
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color color or the racer driving thing
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return return the serial number var
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the num of the racer
     */
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return return the name var
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of the racer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return return the finish var
     */
    public Point getFinish() {
        return finish;
    }

    /**
     * @param finish the end line of the race
     */
    public void setFinish(Point finish) {
        this.finish = finish;
    }

    /**
     * @return get the arena var
     */
    public Arena getArena() {
        return arena;
    }

    /**
     * @param arena the arena var
     */
    public void setArena(Arena arena) {
        this.arena = arena;
    }

    /**
     * @return get the max speed var
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed the max speed the racer can limit
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * @return get the acceleration var
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration the acceleration var
     */
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * @return get the current speed var
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * @param currentSpeed the current speed of the user
     */
    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    /**
     * @return get the failure probability var
     */
    public double getFailureProbability() {
        return failureProbability;
    }

    /**
     * @param failureProbability failure probability var
     */
    public void setFailureProbability(double failureProbability) {
        this.failureProbability = failureProbability;
    }


    /**
     * @return return mishap var
     */
    public Mishap getMishap() {
        return mishap;
    }

    public static void resetCount() {
        count = 1;
    }

    /**
     * @param mishap mishap var
     */
    public void setMishap(Mishap mishap) {
        this.mishap = mishap;
    }


    /**
     * @param name         set the name var
     * @param maxSpeed     set the max speed var
     * @param acceleration set the acceleration var
     * @param color        set the color var
     */
    public Racer(String name, double maxSpeed, double acceleration, Color color, Arena arena) {
        count++;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.color = color;
        currentSpeed = 0;
        this.arena = arena;
        failureProbability = 0;
        currentLocation = new Point(0, 33 * (getSerialNumber() - 1));
        finish = new Point(0, 0);
        mishap = null;

        String str = color.toString().toLowerCase();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        ImageIcon icon = new ImageIcon("icons/" + className() + str + ".png");
        img = new JLabel("", icon, JLabel.LEFT);
        img.setBounds(0, 33 * (getSerialNumber() - 1), 33, 33);
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(33, 33, java.awt.Image.SCALE_SMOOTH);
        img.setIcon(new ImageIcon(newimg));
        //   System.out.println(image.toString());
        if (this.getName() == null)
            this.setName(className() + " #" + this.getSerialNumber());
    }

    /**
     * @param arena  set the arena var
     * @param start  set the current location var
     * @param finish set the finish var
     */
    public void initRace(Arena arena, Point start, Point finish) {
        this.arena = arena;
        currentLocation = start;
        this.finish = finish;
    }

    public boolean hasFinished() {
        if (!(finish.getX() > currentLocation.getX()))
            return true;
        return false;
    }


    /**
     * The method responsible for moving the racer and checking if there is a mishap (stop until there is no mishap)
     *
     * @param friction chosen double var by the user
     * @return return new point
     */
    public Point move(double friction) {
        double reductionFactor = 1;
        if (this.mishap == null || (mishap.getFixable() && mishap.getTurnToFix() == 0)) {
            mishap = null;
            if (Fate.breakDown()) {
                this.mishap = Fate.generateMishap();
                System.out.println(this.getName() + " Has a new mishap!: " + mishap);
                mishap.nextTurn();
                reductionFactor = mishap.getReductionFactor();
            }
        } else if (mishap.getFixable()) {
            mishap.nextTurn();
            reductionFactor = mishap.getReductionFactor();
        } else {
            reductionFactor = mishap.getReductionFactor();
        }

        if (this.currentSpeed < this.maxSpeed) {
            this.setCurrentSpeed(this.currentSpeed + this.acceleration * friction * reductionFactor);
        }

        if (this.currentSpeed > this.maxSpeed) {
            this.setCurrentSpeed(this.maxSpeed);
        }

        Point newPoint = new Point((this.currentLocation.getX() + (1 * this.currentSpeed)), this.currentLocation.getY());
        if (newPoint.getX() > finish.getX())
            newPoint.setX(finish.getX());
        this.setCurrentLocation(newPoint);

        return newPoint;
    }

    /**
     * @return a later use abstract class
     */
    public abstract String describeSpecific();

    /**
     * @return return info about the racer (name,serial num)
     */
    public String describeRacer() {
        return " name: " + name + " Serialnumber: " + serialNumber;
    }

    public void introduce() {
        System.out.println("[" + className() + "]" + describeRacer() + describeSpecific());
    }

    /**
     * @return a later use abstract class
     */
    public abstract String className();


    /**
     * @return return true if mishap not null and false if mishap is null
     */
    public boolean hasMishap() {
        if (mishap == null)
            return false;
        else return true;
    }
}
