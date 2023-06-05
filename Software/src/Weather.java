package Software.src;

public class Weather {
    public double temperature;
    public double windSpeed;
    public String shortForecast;

    public void Weather(double temperature, double windSpeed, String shortForecast) {
        this.temperature=temperature;
        this.windSpeed=windSpeed;
        this.shortForecast=shortForecast;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature=temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed=windSpeed;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast=shortForecast;
    }

}
