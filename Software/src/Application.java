package Software.src;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Application extends JFrame{

    //Variables
    Title title;
    Simulation simulation;

    Application() {
        //Declares variables
        title=new Title();
        simulation=new Simulation();

        //Size and rules for JFrame
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //Implements variables
        this.add(title, BorderLayout.NORTH);
        this.add(simulation, BorderLayout.CENTER);
    }
}
