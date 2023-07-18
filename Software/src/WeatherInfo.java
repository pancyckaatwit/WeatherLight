package Software.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JPanel;

//Responsible for the Weather information section of the app
public class WeatherInfo extends JPanel{

    //Variables
    double temperature;
    String windSpeed;
    String currentForecast;

    JLabel temperatureText;
    JLabel windSpeedText;
    JLabel forecastText;

    ScheduledExecutorService executorService;

    public WeatherInfo() {
        //Declaration of variables
        //API.APICall();
        temperature=0;
        windSpeed="";
        currentForecast="";

        //Section for the weather information
        JPanel weatherInfo=new JPanel(new GridLayout(0, 1));
        weatherInfo.setPreferredSize(new Dimension(600, 200));
        weatherInfo.setFont(new Font("Sans=serif", Font.BOLD, 20));
        weatherInfo.setBackground(Color.LIGHT_GRAY);

        //Temperature text
        temperatureText=new JLabel("Temperature: " + temperature);
        temperatureText.setFont(new Font("Sans=serif", Font.BOLD, 20));
        temperatureText.setHorizontalAlignment(JLabel.CENTER);
        temperatureText.setVerticalAlignment(JLabel.CENTER);
        temperatureText.setForeground(Color.BLACK);
        this.setBackground(Color.LIGHT_GRAY);

        //Crurrent forecast text
        forecastText=new JLabel("Current Forecast: " + currentForecast);
        forecastText.setFont(new Font("Sans=serif", Font.BOLD, 20));
        forecastText.setHorizontalAlignment(JLabel.CENTER);
        forecastText.setVerticalAlignment(JLabel.CENTER);
        forecastText.setForeground(Color.BLACK);

        //Wind speed text
        windSpeedText=new JLabel("Wind speed: " + windSpeed);
        windSpeedText.setFont(new Font("Sans=serif", Font.BOLD, 20));
        windSpeedText.setHorizontalAlignment(JLabel.CENTER);
        windSpeedText.setVerticalAlignment(JLabel.CENTER);
        windSpeedText.setForeground(Color.BLACK);

        weatherInfo.add(temperatureText);
        weatherInfo.add(forecastText);
        weatherInfo.add(windSpeedText);
        this.add(weatherInfo);

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                updateWeatherInfo();
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    //Updates weather info
    public void updateWeatherInfo() {
        temperature = API.getTemperature();
        windSpeed = API.getWindSpeed();
        currentForecast = API.getForecast();

        temperatureText.setText("Temperature: " + temperature);
        forecastText.setText("Current Forecast: " + currentForecast);
        windSpeedText.setText("Wind speed: " + windSpeed);
    }
}
