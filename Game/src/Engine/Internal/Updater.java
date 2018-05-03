package Engine.Internal;

import Engine.SceneManager;

public class Updater
{
    public static float deltaTime;
    private static boolean isRunning;

    static void initialize()
    {
        if(isRunning) { return; }

        Thread thread = new Thread(() ->
        {
            isRunning = true;

            long lastTime = System.nanoTime();

            while(isRunning)
            {
                long startTime = System.nanoTime();

                if(SceneManager.currentScene.entities != null) {
                    SceneManager.updateEntities();
                }

                deltaTime = (float)((startTime - lastTime) / 1E7);
                lastTime = startTime;
            }
        });

        thread.setName("Updater Thread");
        thread.start();
    }
}