package Software.src;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.*;
import java.io.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;

//Class that calls the weather.gov API
public class API {

    // Variables
    private static double temperature;
    private static String windSpeed;
    private static String forecast;
    private static String address = "525 Huntington Avenue, Boston MA!";

    public static void APICall() {

        if (address == "525 Huntington Avenue, Boston MA!") {
            address = API2.APICall();
        }

        // Just trust me, it has to do this
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String urlFormat = "https://geocode.maps.co/search?q=" + encodedAddress;

        // geocode from address
        // 0 is latitude, 1 is longitude
        Double coords[] = { 0.0, 0.0 };
        geocoding(urlFormat, coords);

        // Format the URL, feed into gridpoints function, then feed into getTheWeather
        getTheWeather(gridpoints(formatURLGridpoints(urlFormat, coords)));
    }

    private static String formatURLGridpoints(String urlFormat, Double coords[]) {
        // I just want less stuff in main honestly
        // Same idea as before, just different API we have to access
        String x = URLEncoder.encode(coords[0].toString(), StandardCharsets.UTF_8);
        urlFormat = "https://api.weather.gov/points/" + x + ",";
        x = URLEncoder.encode(coords[1].toString(), StandardCharsets.UTF_8);
        urlFormat = urlFormat + x;

        return urlFormat;
    }

    // It does what you think it does
    // Sets coords to values of lat and lon
    // we love how java treats arrays
    private static void geocoding(String urlFormat, Double[] returner) {
        try {

            // http client for geocoding request
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlFormat))
                    .build();

            // what's he gonna say
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // string that contains the full response
            String responseBody = response.body();

            JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                JsonArray boundingbox = jsonObject.get("boundingbox").getAsJsonArray();
                // use minimum values within the given area
                // should nearly always be close enough
                double minLat = boundingbox.get(0).getAsDouble();
                double minLon = boundingbox.get(2).getAsDouble();

                returner[0] = Math.round(minLat * 1000.0) / 1000.0;
                returner[1] = Math.round(minLon * 1000.0) / 1000.0;
                return;
            }
        } catch (IOException |

                InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String gridpoints(String urlFormat) {
        try {

            // http client for gridpoint request
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlFormat))
                    .build();

            // what's he gonna say
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // int statusCode = response.statusCode();
            // System.out.println(statusCode);

            // string that contains the full response
            String responseBody = response.body();

            String gridX = hitTheGriddy(0, responseBody);
            String gridY = hitTheGriddy(1, responseBody);
            String gridId = hitTheGriddy(2, responseBody);
            // gridId comes back with quotes, removing them
            gridId = gridId.replace("\"", "");

            // https://api.weather.gov/gridpoints/{office}/{grid X},{grid Y}/forecast

            // Just format the string here to return it
            String encodedAddress = URLEncoder.encode(gridId.toString(), StandardCharsets.UTF_8);
            String x = "https://api.weather.gov/gridpoints/" + encodedAddress + "/";
            encodedAddress = URLEncoder.encode(gridX.toString(), StandardCharsets.UTF_8);
            x = x + encodedAddress + ",";
            encodedAddress = URLEncoder.encode(gridY.toString(), StandardCharsets.UTF_8);
            x = x + encodedAddress + "/forecast/hourly";

            // Returns the formatted URL for the next request
            return x;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // In case you really broke it
        return "you broke it";
    }

    // Search and format for gridpoints
    // I genuinely apologize for this name
    private static String hitTheGriddy(int opt, String responseBody) {

        String lookingFor = "empty";
        if (opt == 0) {
            lookingFor = "gridX";
        } else if (opt == 1) {
            lookingFor = "gridY";
        } else {
            lookingFor = "gridId";
        }

        int startIndex = responseBody.indexOf(lookingFor);

        // this is the worst possible way to do this
        // just finding the specific string and getting 10 chars
        if (startIndex != -1 && startIndex + lookingFor.length() + 10 <= responseBody.length()) {
            String result = responseBody.substring(startIndex + lookingFor.length(),
                    startIndex + lookingFor.length() + 10);
            result = result.substring(3);

            int commaIndex = result.indexOf(",");
            if (commaIndex != -1) {
                // Return result before comma
                return result.substring(0, commaIndex);
            }

        }

        // If something really broke
        return "something broke";
    }

    private static void getTheWeather(String urlFormat) {
        try {

            // http client for weather request
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlFormat))
                    .build();

            // what's he gonna say
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // string that contains the full response
            String responseBody = response.body();

            // Parse the JSON Object
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

            // Access the temperature information and get the value
            JsonArray periodsArray = jsonObject.getAsJsonObject("properties").getAsJsonArray("periods");

            // Access the temperature information and get the value
            JsonObject temperatureObject = periodsArray.get(0).getAsJsonObject();
            temperature = temperatureObject.get("temperature").getAsDouble();

            // Access the windspeed information
            JsonObject windSpeedObject = periodsArray.get(0).getAsJsonObject();
            windSpeed = windSpeedObject.get("windSpeed").getAsString();
            // Access the short forecast information
            JsonObject forecastObject = periodsArray.get(0).getAsJsonObject();
            forecast = forecastObject.get("shortForecast").getAsString();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static double getTemperature() {
        return temperature;
    }

    public static String getWindSpeed() {
        return windSpeed;
    }

    public static String getForecast() {
        return forecast;
    }

    public static void setAddress(String newAddress) {
        address = newAddress;
    }
}
