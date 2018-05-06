package engine.rendering;

import engine.SceneManager;

public class Updater extends Thread
{
    private boolean isRunning;

    public void run()
    {
        if(isRunning) { return; }
        else { isRunning = true; }

        long lastTime = System.nanoTime();
        long optimalTime = (long)1E9 / 60;

        while(isRunning)
        {
            long startTime = System.nanoTime();
            long updateLength = startTime - lastTime;
            lastTime = startTime;

            float delta = updateLength / (float)optimalTime;
            SceneManager.update(delta);

            try { Thread.sleep((lastTime - System.nanoTime() + optimalTime) / (long)1E6); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}