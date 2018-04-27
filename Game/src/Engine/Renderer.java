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

    private int currentFPS;
    private long lastFPSCheck;
    private int totalFrames;

    private int targetTime = (int)1E9 / targetFPS;

    public Renderer(Canvas canvas, int resolution, int targetFPS)
    {
        this.resolution = resolution;
        this.targetFPS = targetFPS;

        size = getScaleResolution(canvas);

        Thread thread = new Thread(() ->
        {
            GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
            VolatileImage vImage = gc.createCompatibleVolatileImage(size.width, size.height);

            while(true)
            {
                long startTime = System.nanoTime();
                calculateFPS();

                if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
                    vImage = gc.createCompatibleVolatileImage(size.width, size.height);
                }

                Graphics g = vImage.getGraphics();

                g.setColor(Color.black);
                g.fillRect(0, 0, size.width, size.height);

                g.setColor(Color.white);
                g.drawString(currentFPS + "", 10, 10);

                // TODO - Render Stuff

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

    private Dimension getScaleResolution(Canvas canvas)
    {
        double factor = (canvas.getWidth() + canvas.getHeight()) / 2;
        double width = canvas.getWidth() / (factor / resolution);
        double height = canvas.getHeight() / (factor / resolution);

        return new Dimension((int)width, (int)height);
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
}
