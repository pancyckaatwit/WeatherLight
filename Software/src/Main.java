package Software.src;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String ags[]) {

        //Creates the desktop application
        Application desktopApp=new Application();

        //Calls API every 10 minutes
        ScheduledExecutorService executorService;
        executorService=Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(Main::APICaller, 0, 10, TimeUnit.MINUTES);
    }

    //API reference
    public static void APICaller() {
        new API();
    }
}
