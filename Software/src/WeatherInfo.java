package Software.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

//Responsible for the Weather information section of the app
public class WeatherInfo extends JPanel{

    //Variables
    double temperature=0;
    double windSpeed=0;
    String currentForecast;

    WeatherInfo() {
        //Section for the weather information
        JPanel weatherInfo=new JPanel(new GridLayout(0, 1));
        weatherInfo.setPreferredSize(new Dimension(600, 200));
        weatherInfo.setFont(new Font("Sans=serif", Font.BOLD, 20));
        weatherInfo.setBackground(Color.LIGHT_GRAY);

        //Temperature text
        JLabel temperatureText=new JLabel("Temperature: " + temperature);
        temperatureText.setFont(new Font("Sans=serif", Font.BOLD, 20));
        temperatureText.setHorizontalAlignment(JLabel.CENTER);
        temperatureText.setVerticalAlignment(JLabel.CENTER);
        temperatureText.setForeground(Color.BLACK);
        this.setBackground(Color.LIGHT_GRAY);

        //Crurrent forecast text
        JLabel forecastText=new JLabel("Current Forecast: " + currentForecast);
        forecastText.setFont(new Font("Sans=serif", Font.BOLD, 20));
        forecastText.setHorizontalAlignment(JLabel.CENTER);
        forecastText.setVerticalAlignment(JLabel.CENTER);
        forecastText.setForeground(Color.BLACK);

        //Wind speed text
        JLabel windSpeedText=new JLabel("Wind speed: " + windSpeed);
        windSpeedText.setFont(new Font("Sans=serif", Font.BOLD, 20));
        windSpeedText.setHorizontalAlignment(JLabel.CENTER);
        windSpeedText.setVerticalAlignment(JLabel.CENTER);
        windSpeedText.setForeground(Color.BLACK);

        weatherInfo.add(temperatureText);
        weatherInfo.add(forecastText);
        weatherInfo.add(windSpeedText);
        this.add(weatherInfo);
    }
}
