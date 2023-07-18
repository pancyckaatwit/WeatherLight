package Software.src;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.*;
import java.io.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
// import java.lang.Math.*;

// I'll clean this up later
public class API2 {

    public static String APICall() {
        return (callIt());
    }

    private static String callIt() {
        String ip = "8.8.8.8";
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            ip = in.readLine(); // you get the IP as a String
            System.out.println("IP Address: " + ip);

            String x = URLEncoder.encode(ip, StandardCharsets.UTF_8);
            String urlFormat = "http://ip-api.com/json/" + x;

            // http client for geocoding request
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlFormat))
                    .build();

            // what's he gonna say
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // string that contains the full response
            String responseBody = response.body();

            // System.out.println(responseBody);

            // Parse the JSON Object
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

            // Access the temperature information and get the value
            // JsonArray periodsArray =
            // jsonObject.getAsJsonObject("properties").getAsJsonArray("periods");

            // Access the temperature information and get the value
            // JsonObject temperatureObject = periodsArray.get(0).getAsJsonObject();
            String resultLocation = jsonObject.get("city").getAsString() + ", "
                    + jsonObject.get("regionName").getAsString();
            System.out.println(resultLocation);
            return resultLocation;
        } catch (IOException | InterruptedException e) {
        }
        return "525 Huntington Ave, Boston, MA";
    }
}