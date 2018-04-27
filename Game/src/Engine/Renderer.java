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
    private int resolution;
    private int targetFPS;

    private Dimension size;
    private Canvas canvas;

    private int currentFPS;
    private long lastFPSCheck;
    private int totalFrames;

    private int targetTime;

    private boolean isRunning;

    private IRenderEvent renderEvent;

    public Renderer(Canvas canvas, int resolution, int targetFPS)
    {
        if(targetFPS <= 0 | resolution <= 0) { return; }

        this.canvas = canvas;
        this.resolution = resolution;
        this.targetFPS = targetFPS;

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

                renderEvent.Invoke(g);

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

    private void scaleResolution()
    {
        double factor = (canvas.getWidth() + canvas.getHeight()) / 2;
        double width = canvas.getWidth() / (factor / resolution);
        double height = canvas.getHeight() / (factor / resolution);
        size = new Dimension((int)width, (int)height);
    }

    private void calculateFPS()
    {
        totalFrames++;

        if(System.nanoTime() > lastFPSCheck + (int)1E9)
        {
            lastFPSCheck = System.nanoTime();
            currentFPS = totalFrames;
            totalFrames = 0;
        }
    }

    public void setTargetFPS(int fps) {
        targetFPS = fps;
    }

    public void setRenderQueue(IRenderEvent event)  {
        renderEvent = event;
    }
}
