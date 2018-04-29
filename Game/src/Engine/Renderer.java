package Engine;

import java.awt.*;
import java.awt.image.VolatileImage;

/**
 * Creates a Loop on a Separate CPU Thread where
 * Graphics should be drawn onto. Requires a Canvas
 * object attached to a Frame.
 * @author Jakub
 */
public class Renderer
{
    private static int resolution;
    private static int targetFPS;

    private static Dimension size;
    private static Canvas canvas;

    private static int currentFPS;
    private static long lastFPSCheck;
    private static int totalFrames;

    private static int targetTime;

    private static boolean isRunning;

    private static IRenderEvent renderEvent;

    public static void initialize(Canvas canvas, int resolution, int targetFPS)
    {
        if(targetFPS <= 0 | resolution <= 0 | isRunning) { return; }

        Renderer.canvas = canvas;
        Renderer.resolution = resolution;
        Renderer.targetFPS = targetFPS;

        targetTime = (int)1E9 / targetFPS;

        Thread thread = new Thread(() ->
        {
            isRunning = true;

            scaleResolution();
            GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
            VolatileImage vImage = gc.createCompatibleVolatileImage(size.width, size.height);

            while(isRunning)
            {
                long startTime = System.nanoTime();
                calculateFPS();

                if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
                    vImage = gc.createCompatibleVolatileImage(size.width, size.height);
                }

                Graphics g = vImage.getGraphics();

                // Make the Screen Black
                g.setColor(Color.black);
                g.fillRect(0, 0, size.width, size.height);

                // Draw the FPS Counter
                g.setColor(Color.white);
                g.drawString(currentFPS + "", 5, 15);

                if(renderEvent != null) { renderEvent.Invoke(g); }

                g.dispose();

                g = canvas.getGraphics();
                g.drawImage(vImage, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
                g.dispose();

                long totalTime = System.nanoTime() - startTime;

                if(totalTime < targetTime)
                {
                    try {
                        Thread.sleep((targetTime - totalFrames) / (int)1E6);
                    }
                    catch(InterruptedException e) { e.printStackTrace(); }
                }
            }
        });

        thread.setName("Rendering Thread");
        thread.start();
    }

    private static void scaleResolution()
    {
        double factor = (canvas.getWidth() + canvas.getHeight()) / 2;
        double width = canvas.getWidth() / (factor / resolution);
        double height = canvas.getHeight() / (factor / resolution);
        size = new Dimension((int)width, (int)height);
    }

    private static void calculateFPS()
    {
        totalFrames++;

        if(System.nanoTime() > lastFPSCheck + (int)1E9)
        {
            lastFPSCheck = System.nanoTime();
            currentFPS = totalFrames;
            totalFrames = 0;
        }
    }

    public static void setTargetFPS(int fps) {
        targetFPS = fps;
    }

    static void setRenderQueue(IRenderEvent event)  {
        renderEvent = event;
    }

    public static Dimension getResolution()
    {
        return new Dimension(size.width, size.height);
    }
}
