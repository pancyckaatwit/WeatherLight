package Software.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

//Responsible for the title of the app
public class Title extends JPanel{

    //Variables
    Color lightBlue=new Color(135, 206, 235);

    Title() {
        JLabel title=new JLabel("WeatherLight");
        title.setPreferredSize(new Dimension(800, 85));
        title.setFont(new Font("Sans=serif", Font.BOLD, 40));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.WHITE);
        this.setBackground(lightBlue);

        this.add(title);
    }
}
