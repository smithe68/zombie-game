package engine.internal;

import engine.SceneManager;
import engine.Time;

public class Updater
{
    private static boolean isRunning;

    public static void initialize()
    {
        if(isRunning) { return; }

        Thread thread = new Thread(() ->
        {
            isRunning = true;

            long lastTime = System.nanoTime();

            while(isRunning)
            {
                long startTime = System.nanoTime();

                SceneManager.updateEntities();

                System.out.println("HELLO");

                Time.deltaTime = (float)((startTime - lastTime) / 1E7);
                lastTime = startTime;
            }
        });

        thread.setName("Updater Thread");
        thread.start();
    }
}