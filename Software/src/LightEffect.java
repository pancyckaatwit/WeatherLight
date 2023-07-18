package Software.src;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

//Responsible for the color of the simulation
public class LightEffect {
    
    //Variables
    double temperature;
    Color tempColor;
    String forecast;
    Color weatherEffect;

    //Will get a base color depending on the temp (As of now it changes every 3 degrees ranging from 10 to 98)
    public Color setTemperatureColor() {
        //API.APICall();
        temperature=API.getTemperature();
        System.out.println("Temp: "+temperature);

        if(temperature<4) {
            tempColor=Color.BLUE;
        }else if(temperature>4 && temperature<=7) {
            tempColor=new Color(13, 13, 242);
        }else if(temperature<7 && temperature<=10) {
            tempColor=new Color(26, 26, 229);
        }else if(temperature>10 && temperature<=13){
            tempColor=new Color(39, 39, 216);
        }else if(temperature>13 && temperature<=16) {
            tempColor=new Color(52, 52, 203);
        }else if(temperature>16 && temperature<=19) {
            tempColor=new Color(65, 65, 190);
        }else if(temperature>19 && temperature<=22) {
            tempColor=new Color(78, 78, 177);
        }else if(temperature>22 && temperature<=25) {
            tempColor=new Color(91, 91, 164);
        }else if(temperature>25 && temperature<=28) {
            tempColor=new Color(104, 104, 151);
        }else if(temperature>28 && temperature<=31) {
            tempColor=new Color(117, 117, 138);
        }else if(temperature>31 && temperature<=34) {
            tempColor=new Color(130, 130, 125);
        }else if(temperature>34 && temperature<=37) {
            tempColor=new Color(143, 143, 112);
        }else if(temperature>37 && temperature<=40) {
            tempColor=new Color(156, 156, 99);
        }else if(temperature>40 && temperature<=43) {
            tempColor=new Color(169, 169, 86);
        }else if(temperature>43 && temperature<=46) {
            tempColor=new Color(182, 182, 73);
        }else if(temperature>46 && temperature<=49) {
            tempColor=new Color(195, 195, 60);
        }else if(temperature>49 && temperature<=52) {
            tempColor=new Color(208, 208, 47);
        }else if(temperature>52 && temperature<=55) {
            tempColor=new Color(221, 221, 34);
        }else if(temperature>55 && temperature<=58) {
            tempColor=new Color(234, 234, 21);
        }else if(temperature>58 && temperature<=61) {
            tempColor=new Color(247, 247, 8);
        }else if(temperature>61 && temperature<=64) {
            tempColor=new Color(255, 234, 0);
        }else if(temperature>64 && temperature<=67) {
            tempColor=new Color(255, 214, 0);
        }else if(temperature>67 && temperature<=70) {
            tempColor=new Color(255, 194, 0);
        }else if(temperature>70 && temperature<=73) {
            tempColor=new Color(255, 174, 0);
        }else if(temperature>73 && temperature<=76) {
            tempColor=new Color(255, 154, 0);
        }else if(temperature>76 && temperature<=79) {
            tempColor=new Color(255, 134, 0);
        }else if(temperature>79 && temperature<=82) {
            tempColor=new Color(255, 114, 0);
        }else if(temperature>82 && temperature<=85) {
            tempColor=new Color(255, 94, 0);
        }else if(temperature>85 && temperature<=88) {
            tempColor=new Color(255, 74, 0);
        }else if(temperature>88 && temperature<=91) {
            tempColor=new Color(255, 54, 0);
        }else if(temperature>91 && temperature<=94) {
            tempColor=new Color(255, 34, 0);
        }else if(temperature>94 && temperature<=97) {
            tempColor=new Color(255, 14, 0);
        }else if(temperature>97) {
            tempColor=Color.RED;
        }
        return tempColor;
    }

    //Will look through the forecast string from the API and set a unique weather effect depending on the string
    public Color setWeatherEffect() {
        API.APICall();
        forecast=API.getForecast();
        if(forecast.contains("Sunny") || forecast.contains("sunny")) {
            weatherEffect=sunnyEffect();
            return weatherEffect;
        }else if(forecast.contains("Thunder") || forecast.contains("thunder")) {
            weatherEffect=thunderEffect();
            return weatherEffect;
        }else if(forecast.contains("Snow") || forecast.contains("snow")) {
            weatherEffect=snowEffect();
            return weatherEffect;
        }else if(forecast.contains("Rain") || forecast.contains("rain")) {
            weatherEffect=rainEffect();
            return weatherEffect;
        }else if(forecast.contains("Cloudy") || forecast.contains("cloudy")) {
            weatherEffect=cloudyEffect();
            return weatherEffect;
        }else {
            weatherEffect=null;
        }
        return weatherEffect;
    }

    //Method for the sunny weather effect on the App simulation
    public Color sunnyEffect() {
        //Variables to decide color of sunnyEffect
        Random random=new Random();
        int sunnyRed=255;
        //Sets a random green value between 225 and 255
        int sunnyGreen=random.nextInt(30)+225;
        //Sets a random blue value between 100 and 130
        int sunnyBlue=random.nextInt(30)+100;
        Color sunnyColor=new Color(sunnyRed, sunnyGreen, sunnyBlue);
        weatherEffect=sunnyColor;

        return weatherEffect;
    }

    //Method for the thunder weather effect on the App simulation
    public Color thunderEffect() {
        //Variables to determine whether the color is yellow or gray
        Boolean lightningOrNot;
        Random random=new Random();
        int randomNumber=random.nextInt(10)+1;

        //It has a 1/20 chance to be yellow
        if(randomNumber==1) {
            lightningOrNot=true;
        }else {
            lightningOrNot=false;
        }

        //If the boolean is true the color will be yellow if not it will be light gray
        if(lightningOrNot==true) {
            weatherEffect=Color.YELLOW;
        }else {
            weatherEffect=Color.LIGHT_GRAY;
        }
        return weatherEffect;
    }

    //Method for the snow weather effect on the App simulation
    public Color snowEffect() {
        weatherEffect=Color.WHITE;
        return weatherEffect;
    }

    //Method for the rain weather effect on the App simulation
    public Color rainEffect() {
        //Variables to decide color of sunnyEffect
        Random random=new Random();
        int rainRed=255;
        //Sets a random green value between 225 and 255
        int rainGreen=random.nextInt(30)+225;
        //Sets a random blue value between 100 and 130
        int rainBlue=255;
        Color rainColor=new Color(rainRed, rainGreen, rainBlue);
        weatherEffect=rainColor;

        return weatherEffect;
    }

    //Method for the cloudy weather effect on the App simulation
    public Color cloudyEffect() {
        weatherEffect=Color.GRAY;
        return weatherEffect;
    }
    
}
