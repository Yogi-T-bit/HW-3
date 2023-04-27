package factory;

import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer.Color;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 */

/**
 * We can only create one racebuilder class
 * In these builders we are loading a general class (can accept all the classes types)
 */
public class RaceBuilder {
    private static RaceBuilder instance;
    private ClassLoader classLoader;

    /**
     * @return return the instance
     */
    public static RaceBuilder getInstance() {
        if (instance == null)
            instance = new RaceBuilder();
        return instance;
    }


    /**
     * @param arenaType set the type of the arena
     * @param length set the length of the arena
     * @param maxRacers set the max racers in the arena
     * @return return the new arena
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws SecurityException SecurityException
     * @throws InstantiationException InstantiationException
     * @throws IllegalAccessException IllegalAccessException
     * @throws IllegalArgumentException IllegalArgumentException
     * @throws InvocationTargetException InvocationTargetException
     * The arena builder class
     */
    public Arena buildArena(String arenaType, double length, int maxRacers) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> Class;
        Constructor<?> constructor;
        Class = classLoader.loadClass(arenaType);
        constructor = Class.getConstructor(double.class, int.class);
        return (Arena) constructor.newInstance(length, maxRacers);

    }

    /**
     * @param racerType set the type of the racer
     * @param name set the name of the racer
     * @param maxSpeed set the max speed of the racer
     * @param acceleration set the acceleration of the racer
     * @param color set the color of the racer
     * @return return new racer
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws SecurityException SecurityException
     * @throws InstantiationException InstantiationException
     * @throws IllegalAccessException IllegalAccessException
     * @throws IllegalArgumentException IllegalArgumentException
     * @throws InvocationTargetException InvocationTargetException
     * The racer builder class
     */
    public Racer buildRacer(String racerType, String name, double maxSpeed, double acceleration, Color color) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> Class;
        Constructor<?> constructor;
        Class = classLoader.loadClass(racerType);
        constructor = Class.getConstructor(String.class, double.class, double.class, Color.class);
        return (Racer) constructor.newInstance(name, maxSpeed, acceleration, color);
    }

    /**
     * @param racerType set the type of the racer
     * @param name set the name of the racer
     * @param maxSpeed set the max speed of the racer
     * @param acceleration set the acceleration of the racer
     * @param color set the color of the racer
     * @param wheels  set the wheels of the racer
     * @return return new racer with wheels
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws SecurityException SecurityException
     * @throws InstantiationException InstantiationException
     * @throws IllegalAccessException IllegalAccessException
     * @throws IllegalArgumentException IllegalArgumentException
     * @throws InvocationTargetException InvocationTargetException
     * The land racer builder class
     */
    public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration, Color color, int wheels) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> Class;
        Constructor<?> constructor;
        Class = classLoader.loadClass(racerType);
        constructor = Class.getConstructor(String.class, double.class, double.class, Color.class, int.class);
        return (Racer) constructor.newInstance(name, maxSpeed, acceleration, color, wheels);
    }
}
