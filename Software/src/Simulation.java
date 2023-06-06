package Software.src;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

//Responsible for the simulation section of the app
public class Simulation extends JPanel{
    
    Simulation() {
        //Section for the simulation
        JPanel simulation=new JPanel();
        simulation.setPreferredSize(new Dimension(750, 500));
        this.setBackground(Color.WHITE);

        this.add(simulation);
    }
}
