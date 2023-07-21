package Software.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppInfo extends JFrame{
    //Variables
    Color lightBlue=new Color(135, 206, 235);
    Color darkOrange=new Color(255, 140, 0);

    //Constructor
    public AppInfo() {
        //Size and rules for JFrame
        JFrame appInfo=new JFrame("WeatherLight App Information");
        appInfo.setSize(700, 500);
        appInfo.setVisible(true);

        //JPLabel for title for app info
        JLabel appInfoTitle=new JLabel("App Information");
        appInfoTitle.setPreferredSize(new Dimension(700, 80));
        appInfoTitle.setFont(new Font("Sans=serif", Font.BOLD, 40));
        appInfoTitle.setHorizontalAlignment(JLabel.CENTER);
        appInfoTitle.setVerticalAlignment(JLabel.CENTER);
        appInfoTitle.setForeground(Color.WHITE);
        appInfoTitle.setBackground(lightBlue);
        appInfoTitle.setOpaque(true);

        //JPanel to hold text
        JPanel info=new JPanel(new GridLayout(0, 1));
        info.setSize(700, 420);
        info.setFont(new Font("Sans=serif", Font.PLAIN, 12));
        info.setBackground(Color.LIGHT_GRAY);
        info.setOpaque(true);

        //Title for address bar info
        JLabel addressBarInfoTitle=new JLabel("Address Bar Info:");
        addressBarInfoTitle.setFont(new Font("Sans=serif", Font.BOLD, 14));

        //Info about the address bar
        JLabel addressBarInfo=new JLabel("<html>The address bar is located in the top right of the app. Simply type an address or city in the US then hit the button to set your current location. If the address does not exist or it was mispelled it will default to Wentworth Institute of Technology.</html>");
        addressBarInfo.setForeground(Color.BLACK);

        //Info about the simulation
        JLabel simulationInfoTitle=new JLabel("Simulation Info:");
        simulationInfoTitle.setFont(new Font("Sans=serif", Font.BOLD, 14));

        //First sentence about the simulation
        JLabel simulationInfo=new JLabel("<html>The simulation alters between showing temperature and weather effect every 10 seconds.</html>");
        simulationInfo.setForeground(Color.BLACK);

        //Second sentence about the simulation
        JLabel temperatureInfo=new JLabel("<html>The temperature is a gradient that starts with some shade of blue for a low temperature and gradually turns to red the higher the temperature.</html>");
        temperatureInfo.setForeground(Color.BLACK);

        //Specific weather effect information
        JLabel sunnyInfo=new JLabel("For the sunny effect the simulation will shine some yellow and orange gradient.");
        sunnyInfo.setForeground(darkOrange);
        JLabel thunderInfo=new JLabel("For the sunny effect the simulation will be grey and flash yellow randomly.");
        thunderInfo.setForeground(Color.YELLOW);
        JLabel snowInfo=new JLabel("For the snow effect the simulation will be white.");
        snowInfo.setForeground(Color.WHITE);
        JLabel rainInfo=new JLabel("For the rain effect the simulation will show different shades of blue randomly.");
        rainInfo.setForeground(Color.BLUE);
        JLabel cloudInfo=new JLabel("For the cloud effect the simulation will switch between grey and light grey.");
        cloudInfo.setForeground(Color.GRAY);

        info.add(addressBarInfoTitle);
        info.add(addressBarInfo);
        info.add(simulationInfoTitle);
        info.add(simulationInfo);
        info.add(temperatureInfo);
        info.add(sunnyInfo);
        info.add(thunderInfo);
        info.add(snowInfo);
        info.add(rainInfo);
        info.add(cloudInfo);
        
        appInfo.add(appInfoTitle, BorderLayout.NORTH);
        appInfo.add(info, BorderLayout.CENTER);
    }
}
