package Software.src;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

//Responsible for the simulation section of the app
public class Simulation extends JPanel{
    
    //Variables
    LightEffect tempEffect=new LightEffect();
    Color tempColor=tempEffect.setTemperatureColor();
    Timer timer;

    private ScheduledExecutorService executorService;

    
    public Simulation() {
        //Section for the simulation
        JPanel simulation=new JPanel();
        simulation.setPreferredSize(new Dimension(750, 500));
        simulation.setBackground(tempColor);

        this.add(simulation);

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                updateSimulationColor();
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    public void updateSimulationColor() {
        Color newColor = tempEffect.setTemperatureColor();
        this.getComponent(0).setBackground(newColor);
        this.repaint();
    }

}
