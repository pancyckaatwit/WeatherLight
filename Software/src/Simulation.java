package Software.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Simulation extends JPanel{

    Simulation() {
        //Simulation
        JLabel simulation=new JLabel("Test");
        simulation.setPreferredSize(new Dimension(100, 100));
        simulation.setFont(new Font("Sans=serif", Font.BOLD, 40));
        simulation.setBackground(Color.RED);
        simulation.setHorizontalAlignment(JLabel.CENTER);

        this.add(simulation);
    }
    
}
