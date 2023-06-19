package Software.src;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

//Responsible for the simulation section of the app
public class Simulation extends JPanel{
    
    //Variables
    LightEffect tempEffect=new LightEffect();
    Color tempColor=tempEffect.setTemperatureColor();
    
    public Simulation() {
        //Section for the simulation
        JPanel simulation=new JPanel();
        simulation.setPreferredSize(new Dimension(750, 500));
        simulation.setBackground(tempColor);

        this.add(simulation);
    }

}
