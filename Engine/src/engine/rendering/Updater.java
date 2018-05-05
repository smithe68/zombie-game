package engine.rendering;

import engine.SceneManager;
import engine.utility.Time;

public class Updater extends Thread
{
    private boolean isRunning;

    public void run()
    {
        if(isRunning) { return; }
        else { isRunning = true; }

        long lastTime = System.nanoTime();

        while(isRunning)
        {
            long startTime = System.nanoTime();

            SceneManager.update();

            Time.deltaTime = (float)((startTime - lastTime) / 1E8);
            lastTime = startTime;

            try
            {
                long optimalTime = (long) 1E9 / 120;
                Thread.sleep(Math.abs((lastTime - System.nanoTime() + optimalTime) / (long)1E6));
            }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}