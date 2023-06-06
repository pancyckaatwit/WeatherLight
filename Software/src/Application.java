package Software.src;

import java.awt.BorderLayout;

import javax.swing.JFrame;

//This class acts as the JFrame layout and rules for the application
public class Application extends JFrame{

    //Variables
    private Title t=new Title();
    private Simulation s=new Simulation();
    private WeatherInfo w=new WeatherInfo();

    Application() {
        //Declaration for variables
        //Size and rules for JFrame
        JFrame application=new JFrame("WeatherLight");
        application.setSize(800, 800);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);

        application.add(t, BorderLayout.NORTH);
        application.add(s, BorderLayout.CENTER);
        application.add(w, BorderLayout.SOUTH);
    }
}
