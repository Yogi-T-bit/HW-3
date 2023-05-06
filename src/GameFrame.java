

import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.land.LandArena;
import game.arenas.naval.NavalArena;
import game.racers.Racer;
import game.racers.air.Airplane;
import game.racers.air.Helicopter;
import game.racers.land.Bicycle;
import game.racers.land.Car;
import game.racers.land.Horse;
import game.racers.naval.RowBoat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import utilities.Point;

import game.racers.naval.SpeedBoat;
import utilities.EnumContainer;
import utilities.EnumContainer.Color;
import utilities.RacerInfoPanel;

public class GameFrame extends JFrame {
    private JPanel GamePanel;
    private JButton startRaceButton;
    private JButton showInfoButton;
    private JComboBox chooseArena;
    private JFrame infoTable = null;
    private JTextField arenaLengthText;
    private JTextField maxRacerText;
    private JComboBox chooseRacer;
    private JComboBox chooseColor;
    private JButton Add_racer_button;
    private JTextField racerNameText;
    private JTextField maxSpeedText;
    private JTextField accelerationText;
    private JButton buildArenaButton;
    private JPanel toolBar;
    private Arena arena;
    private int countRacers = 0;


    public GameFrame() {
        super("My GUI");
        setContentPane(GamePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(1000+ toolBar.getWidth(), 700);
        GamePanel.setSize(1000, 700);
        setLocationRelativeTo(null);


        chooseArena.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Choose Arena:"));
        chooseArena.setPreferredSize(new Dimension(120, 40));

        arenaLengthText.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Arena length:"));
        arenaLengthText.setPreferredSize(new Dimension(120, 40));

        maxRacerText.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Max racers number:"));
        maxRacerText.setPreferredSize(new Dimension(120, 40));

        chooseRacer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Choose racer:"));
        chooseRacer.setPreferredSize(new Dimension(120, 40));

        chooseColor.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Choose color:"));
        chooseColor.setPreferredSize(new Dimension(120, 40));

        racerNameText.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Racer name:"));
        racerNameText.setPreferredSize(new Dimension(120, 40));

        maxSpeedText.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Max speed:"));
        maxSpeedText.setPreferredSize(new Dimension(120, 40));

        accelerationText.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Acceleration:"));
        accelerationText.setPreferredSize(new Dimension(120, 40));

        if (arenaLengthText.getText().isEmpty()) {
            arenaLengthText.setText("1000");
        }
        if (maxRacerText.getText().isEmpty()) {
            maxRacerText.setText("8");
        }

        buildArenaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setSize(Integer.parseInt(arenaLengthText.getText()) + toolBar.getWidth(), 700);
                GamePanel.setSize(Integer.parseInt(arenaLengthText.getText()), 700);
                toolBar.setLocation(getWidth()-toolBar.getWidth(),0);

                if (changeBackground(chooseArena.getSelectedItem().toString()) && limitLength() && limitRacers()) {

                    switch (chooseArena.getSelectedItem().toString()) {
                        case "AerialArena" ->
                                arena = new AerialArena(Double.parseDouble(arenaLengthText.getText()), Integer.parseInt(maxRacerText.getText()));
                        case "LandArena" ->
                                arena = new LandArena(Double.parseDouble(arenaLengthText.getText()), Integer.parseInt(maxRacerText.getText()));
                        case "NavalArena" ->
                                arena = new NavalArena(Double.parseDouble(arenaLengthText.getText()), Integer.parseInt(maxRacerText.getText()));
                        default ->
                                throw new IllegalStateException("Unexpected value: " + chooseArena.getSelectedItem().toString());
                    }
                    countRacers = 0;
                    arena.setLength(GamePanel.getWidth());
                    Racer.resetCount();
                    arena.getActiveRacers().clear();
                }
            }
        });
        Add_racer_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTypeRacer() && checkIfArenaExists() && checkValuesOfRacer()) {

                    Color color = Color.valueOf(chooseColor.getSelectedItem().toString().toUpperCase());

                    if (countRacers >= Integer.parseInt(maxRacerText.getText())) {
                        JOptionPane.showMessageDialog(null, "You have reached the maximum number of racers!");
                        // Add_racer_button.setEnabled(false);
                    } else {
                        switch (chooseRacer.getSelectedItem().toString()) {
                            case "RowBoat" ->
                                    arena.addActiveRacer(new RowBoat(racerNameText.getText(), Double.parseDouble(maxSpeedText.getText()), Double.parseDouble(accelerationText.getText()), color));
                            case "SpeedBoat" ->
                                    arena.addActiveRacer(new SpeedBoat(racerNameText.getText(), Double.parseDouble(maxSpeedText.getText()), Double.parseDouble(accelerationText.getText()), color));
                            case "Airplane" ->
                                    arena.addActiveRacer(new Airplane(racerNameText.getText(), Double.parseDouble(maxSpeedText.getText()), Double.parseDouble(accelerationText.getText()), color, 3));// TODO to check
                            case "Helicopter" ->
                                    arena.addActiveRacer(new Helicopter(racerNameText.getText(), Double.parseDouble(maxSpeedText.getText()), Double.parseDouble(accelerationText.getText()), color));
                            case "Bicycle" ->
                                    arena.addActiveRacer(new Bicycle(racerNameText.getText(), Double.parseDouble(maxSpeedText.getText()), Double.parseDouble(accelerationText.getText()), color, 2));
                            case "Car" ->
                                    arena.addActiveRacer(new Car(racerNameText.getText(), Double.parseDouble(maxSpeedText.getText()), Double.parseDouble(accelerationText.getText()), color, 4));
                            case "Horse" ->
                                    arena.addActiveRacer(new Horse(racerNameText.getText(), Double.parseDouble(maxSpeedText.getText()), Double.parseDouble(accelerationText.getText()), color));
                            default ->
                                    throw new IllegalStateException("Unexpected value: " + chooseRacer.getSelectedItem().toString());
                        }
                        setVisible(true);
                        GamePanel.add(arena.getActiveRacers().get(countRacers).getImg());
                        GamePanel.setComponentZOrder(arena.getActiveRacers().get(countRacers).getImg(), 0);
                        GamePanel.revalidate();
                        GamePanel.repaint();
                        arena.getActiveRacers().get(countRacers).setArena(arena);
                        Point point = new Point((int) arena.getLength(), 0);
                        arena.getActiveRacers().get(countRacers).setFinish(point);

                        countRacers++;
                    }


                }

            }

        });
        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Racer racer :arena.getActiveRacers()) {
                    Thread thread = new Thread(racer);
                    thread.start();
                }


                    }

        });
        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if(arena!=null) {
//                    RacerInfoPanel racerInfoPanel = new RacerInfoPanel(arena);
//                    JScrollPane scrollPane = new JScrollPane(racerInfoPanel);
//                    GamePanel.add(scrollPane); // add the panel to your UI
//                    scrollPane.setVisible(true);
//                    scrollPane.repaint();
//                    Thread updateThread = new Thread(racerInfoPanel); // create a new thread
//                    updateThread.start(); // start the thread
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "You must build an arena first!");
//                }

                if (arena == null || arena.getActiveRacers().size() == 0) {
                    JOptionPane.showMessageDialog(null, "Please build arena first and add racers!");
                    return;
                }
                RacerInfoPanel racerInfoPanel = new RacerInfoPanel(arena);



                JTable table = new JTable( racerInfoPanel.getData(),  racerInfoPanel.getDataNames());
                table.setPreferredScrollableViewportSize(table.getPreferredSize());
                JScrollPane scrollPane = new JScrollPane(table);

                JPanel tabPan = new JPanel();
                // tabPan.setLayout(new GridLayout(1,0));
                tabPan.add(scrollPane);
                Thread updateThread = new Thread(racerInfoPanel); // create a new thread
                updateThread.start();

                if (infoTable != null)
                    infoTable.dispose();
                infoTable = new JFrame("Racers information");
                infoTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                infoTable.setContentPane(tabPan);
                infoTable.pack();
                infoTable.setVisible(true);

            }
        });
    }


    public boolean changeBackground(String arena) {
        try {
            ImageIcon imageicon= new ImageIcon("icons/"+arena+".jpg");
            JLabel background = new JLabel();

            for(Component c : GamePanel.getComponents()) {
                if((c instanceof JLabel)) {
                    GamePanel.remove(c);
                }
            }
            GamePanel.setLayout(null);
            background.setBounds(0,0,GamePanel.getWidth(),GamePanel.getHeight());
            Image image = imageicon.getImage();
            Image scaledImage = image.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
            background.setIcon(new ImageIcon(scaledImage));
            GamePanel.add(background);
            GamePanel.setPreferredSize(new Dimension(imageicon.getIconWidth(), imageicon.getIconHeight()));
            GamePanel.revalidate();
            GamePanel.repaint();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean limitRacers() {
        try {
            if (!(Integer.parseInt(maxRacerText.getText()) >= 1 && Integer.parseInt(maxRacerText.getText()) <= 20)) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
            return false;
        }
        return true;
    }

    public boolean limitLength() {
        try {
            if (!(Integer.parseInt(arenaLengthText.getText()) >= 100 && Integer.parseInt(arenaLengthText.getText()) <= 3000)) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
            return false;
        }
        return true;
    }

    public boolean checkTypeRacer() {
        try { // check if the user entered air arena racer type
            if (!((chooseRacer.getSelectedItem() == "Airplane" || chooseRacer.getSelectedItem().toString() == "Helicopter"))
                    && chooseArena.getSelectedItem() == "AerialArena") {
                throw new Exception();
            } else if (!((chooseRacer.getSelectedItem() == "Car" || chooseRacer.getSelectedItem().toString() == "Bicycle" || chooseRacer.getSelectedItem().toString() == "Horse"))
                    && chooseArena.getSelectedItem() == "LandArena") {
                throw new Exception();
            } else if (!((chooseRacer.getSelectedItem() == "RowBoat" || chooseRacer.getSelectedItem().toString() == "SpeedBoat"))
                    && chooseArena.getSelectedItem() == "NavalArena") {
                throw new Exception();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Racer type and arena type are not compatible! Please try again.");
            return false;
        }
        return true;
    }

    public boolean checkIfArenaExists() {
        try {
            if (arena == null) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please build arena first!");
            return false;
        }
        return true;
    }

    public boolean checkValuesOfRacer() {
        try {
            if (racerNameText.getText().isEmpty()) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please add a racer name!");
            return false;
        }
        try {
            if (!(Integer.parseInt(maxSpeedText.getText()) >= 0 && Integer.parseInt(maxSpeedText.getText()) <= 100000)) { //TODO: need to ask the teacher about the most max speed possible
                throw new Exception();
            } else if (maxSpeedText.getText().isEmpty()) {
                throw new Exception();

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input max speed value! Please try again.");
            return false;
        }
        try {
            if (!(Integer.parseInt(accelerationText.getText()) >= 0 && Integer.parseInt(accelerationText.getText()) <= 10000)) { //TODO: need to ask the teacher about the most acceleration possible
                throw new Exception();
            } else if (maxSpeedText.getText().isEmpty()) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input acceleration value! Please try again.");
            return false;
        }
        return true;
    }

    // todo: to check if there is one user when we start the game!


    public static void main(String[] args) throws IOException {
        new GameFrame();
    }
}
