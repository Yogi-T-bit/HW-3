package utilities;

import java.text.DecimalFormat;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * This class is part of the racer class
 */
public class Mishap {
    private boolean fixable;
    double reductionFactor;
    int turnToFix;

    /**
     * @param fixable boolean var in mishap if false cant move to the next turn
     * @param turnToFix int var in mishap if below 1 cant move to the next turn
     * @param reductionFactor double var in mishap
     */
    Mishap(boolean fixable, int turnToFix, double reductionFactor) {
        this.fixable = fixable;
        this.turnToFix = turnToFix;
        this.reductionFactor = reductionFactor;
    }

    public void nextTurn() {
        if (fixable && turnToFix > 0) {
            turnToFix--;
        }
    }

    /**
     * @return return the fixable var
     */
    public boolean getFixable() {
        return fixable;
    }

    /**
     * @param fixable set new fixable chosen by the user
     */
    public void setFixable(boolean fixable) {
        this.fixable = fixable;
    }

    /**
     * @return return the red factor var
     */
    public double getReductionFactor() {
        return reductionFactor;
    }

    /**
     * @param reductionFactor set new red factor chosen by the user
     */
    public void setReductionFactor(double reductionFactor) {
        this.reductionFactor = reductionFactor;
    }

    /**
     * @return  return the "turn to fix" var
     */
    public int getTurnToFix() {
        return turnToFix;
    }

    /**
     * @param turnToFix set new "turn to fix" chosen by the user
     */
    public void setTurnToFix(int turnToFix) {
        this.turnToFix = turnToFix;
    }

    /**
     * @return return a format string
     */
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "(" + fixable + "," + turnToFix + "," + df.format(reductionFactor) + ")";
    }
}
