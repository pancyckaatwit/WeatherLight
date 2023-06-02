package Software;

import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

//Converts from gson to a map
import com.google.gson.*;
import com.google.gson.reflect.*;

//This class will handle the API calls from openweathermap.org
public class API {
    //Converts json to map
    public static Map<String, Object> jsonToMap (String str) {
        Map<String, Object> map = new Gson().fromJson(
            str, new TypeToken<HashMap<String, Object>>() {}.getType()
        );
        return map;
    }
    public static void main(String args[]) {
        String APIKey="2901407f0cd76bfb44b44738a1fa4947";
        String location="Boston, MA";
        String URLString="http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + APIKey + "&units=imperial";
        try {
            StringBuilder result=new StringBuilder();
            URL url=new URL(URLString);
            URLConnection connection=url.openConnection();
            BufferedReader read=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line=read.readLine()) !=null) {
                result.append(line);
            }
            read.close();
            System.out.println(result);

            Map<String, Object> respMap=jsonToMap(result.toString());
            Map<String, Object> mainMap=jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap=jsonToMap(respMap.get("wind").toString());

            System.out.println("Temperature: " + mainMap.get("temp"));
            System.out.println("Humidity: " + mainMap.get("humidity"));
            System.out.println("Wind Speed: " + windMap.get("speed"));
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
