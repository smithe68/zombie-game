package Engine;

public class Updater
{
    public static float deltaTime;

    private static boolean isRunning;

    private static IUpdateEvent updateEvent;

    public static void initialize()
    {
        if(isRunning) { return; }

        Thread thread = new Thread(() ->
        {
            isRunning = true;

            long lastTime = System.nanoTime();

            while(isRunning)
            {
                long currentTime = System.nanoTime();

                updateEvent.Invoke();

                deltaTime = (float)((currentTime - lastTime) / 1E6);
                lastTime = currentTime;
            }
        });

        thread.setName("Updater Thread");
        thread.start();
    }

    static void setUpdateQueue(IUpdateEvent event) {
        updateEvent = event;
    }
}