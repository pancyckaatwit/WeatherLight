package Software.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Title extends JPanel{
    
    Title() {
        //Title elements (WeatherLight)
        Color lightBlue=new Color(135, 206, 235);
        JLabel title=new JLabel("WeatherLight");
        title.setPreferredSize(new Dimension(500, 80));
        title.setFont(new Font("Sans=serif", Font.BOLD, 40));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.WHITE);
        this.setBackground(lightBlue);

        this.add(title);
    }
}
