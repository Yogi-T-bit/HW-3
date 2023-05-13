package utilities;

import game.arenas.Arena;
import game.racers.Racer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class RacerInfoPanel extends JFrame implements Runnable {
    private final JTable table= new JTable();

    private final DefaultTableModel tableModel;
    Arena arena;
    String[] DataNames = { "Racer name", "Current speed", "Max speed", "Current X location", "Finished" };
    String[][] Data;

    public ArrayList<Racer> getRacers() {
        return racers;
    }

    private ArrayList<Racer> racers = new ArrayList<>();
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
        racers.addAll(arena.getCompletedRacers());
        racers.addAll(arena.getActiveRacers());

        this.arena = arena;
        Data = new String[racers.size()][5];
        tableModel = new DefaultTableModel(Data, DataNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        pack();

    }


    private void updateLabels() {
        int i = 0;

        for (Racer racer :racers) {
            Data[i][0] = racer.getName();
            Data[i][1] = "" + racer.getCurrentSpeed();
            Data[i][2] = "" + racer.getMaxSpeed();
            Data[i][3] = "" + racer.getCurrentLocation().getX();
            if(racer.hasFinished())
                Data[i][4] = "Yes";
            else
                Data[i][4] = "No";
            i++;
        }


        tableModel.setDataVector(Data, DataNames);

    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> {
                updateLabels();
                table.repaint();
            });
        }
    }


}
