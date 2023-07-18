package Software.src;

import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

//Responsible for the simulation section of the app
public class Simulation extends JPanel{
    
    //Variables
    LightEffect tempEffectColor=new LightEffect();
    Color tempColor=tempEffectColor.setTemperatureColor();
    LightEffect weatherEffectColor=new LightEffect();
    Color weatherColor=weatherEffectColor.setWeatherEffect();

    boolean simulationState;
    private ScheduledExecutorService executorService;
    
    public Simulation() {
        //Section for the simulation
        JPanel simulation=new JPanel();
        simulation.setPreferredSize(new Dimension(750, 500));
        simulation.setBackground(tempColor);

        //Decides whether to show temperature or weather effect every ten seconds
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                simulationState = !simulationState;
                System.out.println("Simulation State: " + simulationState);
            }
        }, 0, 10, TimeUnit.SECONDS);
        //Updates background color 1/2 a second
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(simulationState==true) {
                    updateSimulationTempColor();
                }   else {
                    updateSimulationWeatherColor();
                }
            }
        }, 0, 500, TimeUnit.MILLISECONDS);

        this.add(simulation);
    }

    //Responsible for updating the already existing simulation temperature color
    public void updateSimulationTempColor() {
        Color tempColor = tempEffectColor.setTemperatureColor();
        this.getComponent(0).setBackground(tempColor);
        this.repaint();
    }

    //Responsible for updating the already existing simulation color
    public void updateSimulationWeatherColor() {
        Color wColor = weatherEffectColor.setWeatherEffect();
        this.getComponent(0).setBackground(wColor);
        this.repaint();
    }

}
