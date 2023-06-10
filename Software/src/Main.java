package Software.src;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) {

        //Creates the desktop application
        Application desktopApp=new Application();

        //Calls API every 10 minutes
        ScheduledExecutorService executorService;
        executorService=Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(Main::APICaller, 0, 10, TimeUnit.SECONDS);
    }

    //API reference
    public static void APICaller() {
        API.APICall();
    }
}
