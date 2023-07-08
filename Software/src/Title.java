package Software.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Responsible for the title of the app
public class Title extends JPanel{

    //Variables
    Color lightBlue=new Color(135, 206, 235);

    public Title() {
        JLabel title=new JLabel("WeatherLight");
        JTextField addressBar=new JTextField();
        JButton addressButton=new JButton();
        
        //Values for the title
        title.setPreferredSize(new Dimension(500, 85));
        title.setFont(new Font("Sans=serif", Font.BOLD, 40));
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.WHITE);
        this.setBackground(lightBlue);

        //Values for the bar to enter addresses
        addressBar.setPreferredSize(new Dimension(200, 30));
        addressBar.setHorizontalAlignment(JLabel.RIGHT);

        //Values for the button
        addressButton.addActionListener(e-> {
            String address=addressBar.getText();
            updateAddress(address);
        });
        addressButton.setPreferredSize(new Dimension(50, 30));
        
        this.add(title);
        this.add(addressBar);
        this.add(addressButton);
    }

    private void updateAddress(String address) {
        API.setAddress(address);
    }
}
