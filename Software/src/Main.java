package Software.src;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Desktop application for controlling ESP32 WeatherLight device
 * WIT Senior Project Summer 2023
 * 
 * @author pancycka@wit.edu , brownc32@wit.edu
 *
 */


public class Main {

    //Responsible for running the project.
    public static void main(String args[]) {

        //Creates the desktop application
        Application desktopApp=new Application();

        //Calls the Client class
        try {
            Client.ClientCall(args);
        }catch (Exception e){
            e.printStackTrace();
            //System.exit(1);
        }

        //Calls API every 10 minutes
        ScheduledExecutorService executorService;
        executorService=Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(Main::APICaller, 0, 10, TimeUnit.MINUTES);
    }

    //API reference
    public static void APICaller() {
        API.APICall();
    }
}
