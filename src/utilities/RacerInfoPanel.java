package utilities;

import game.arenas.Arena;
import game.racers.Racer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RacerInfoPanel extends JFrame implements Runnable {
    Arena arena;
    String[] DataNames = { "Racer name", "Current speed", "Max speed", "Current X location", "Finished" };
    String[][] Data;

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public String[] getDataNames() {
        return DataNames;
    }

    public void setDataNames(String[] dataNames) {
        DataNames = dataNames;
    }

    public String[][] getData() {
        return Data;
    }

    public void setData(String[][] data) {
        Data = data;
    }

    public RacerInfoPanel(Arena arena) {
        this.arena = arena;
        Data= new String[arena.getActiveRacers().size()][5];
//        setLayout(new GridLayout(arena.getActiveRacers().size(), 5));
//        initLabels();
    }

//    private void initLabels() {
//        ArrayList<Racer> racers = arena.getActiveRacers();
//        nameLabelArray = new JLabel[racers.size()];
//        speedLabelArray = new JLabel[racers.size()];
//        maxSpeedLabelArray = new JLabel[racers.size()];
//        locationLabelArray = new JLabel[racers.size()];
//        finishedLabelArray = new JLabel[racers.size()];
//
//        for (int i = 0; i < racers.size(); i++) {
//            nameLabelArray[i] = new JLabel(racers.get(i).getName());
//            speedLabelArray[i] = new JLabel(String.valueOf(racers.get(i).getCurrentSpeed()));
//            maxSpeedLabelArray[i] = new JLabel(String.valueOf(racers.get(i).getMaxSpeed()));
//            locationLabelArray[i] = new JLabel(String.valueOf(racers.get(i).getCurrentLocation().getX()));
//            finishedLabelArray[i] = new JLabel(String.valueOf(racers.get(i).hasFinished()));
//            add(nameLabelArray[i]);
//            add(speedLabelArray[i]);
//            add(maxSpeedLabelArray[i]);
//            add(locationLabelArray[i]);
//            add(finishedLabelArray[i]);
//        }
//    }

    private void updateLabels() {
//        ArrayList<Racer> racers = arena.getActiveRacers();
//        for (int i = 0; i < racers.size(); i++) {
//            Racer racer = racers.get(i);
//            speedLabelArray[i].setText(String.valueOf(racer.getCurrentSpeed()));
//            locationLabelArray[i].setText(String.valueOf(racer.getCurrentLocation().getX()));
//            finishedLabelArray[i].setText(String.valueOf(racer.hasFinished()));
  //      }
        int i = 0;
        for (Racer racer : arena.getCompletedRacers()) {
            Data[i][0] = racer.getName();
            Data[i][1] = "" + racer.getCurrentSpeed();
            Data[i][2] = "" + racer.getMaxSpeed();
            Data[i][3] = "" + racer.getCurrentLocation().getX();
            Data[i][4] = "Yes";
            i++;
        }

        for (Racer racer : arena.getActiveRacers()) {
            Data[i][0] = racer.getName();
            Data[i][1] = "" + racer.getCurrentSpeed();
            Data[i][2] = "" + racer.getMaxSpeed();
            Data[i][3] = "" + racer.getCurrentLocation().getX();
            Data[i][4] = "No";
            i++;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> updateLabels());
        }
    }
}
