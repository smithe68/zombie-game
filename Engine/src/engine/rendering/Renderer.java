package engine.rendering;

import engine.SceneManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
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
    private static Dimension canvasSize;

    private static int currentFPS;
    private static long lastFPSCheck;
    private static int totalFrames;

    private static int targetTime;

    private static boolean isRunning;

    private static GraphicsConfiguration gc;
    private static AffineTransform affine;

    public static void initialize(Window window, int resolution, int targetFPS)
    {
        if(targetFPS <= 0 | resolution <= 0 | isRunning) { return; }

        Renderer.resolution = resolution;
        Renderer.canvasSize = window.getSize();
        Renderer.targetFPS = targetFPS;

        scaleResolution();

        affine = new AffineTransform();
        targetTime = (int)1E9 / targetFPS;

        Thread thread = new Thread(() ->
        {
            isRunning = true;

            gc = window.getGC();
            VolatileImage vImage = gc.createCompatibleVolatileImage(size.width, size.height);

            while(isRunning)
            {
                long startTime = System.nanoTime();
                calculateFPS();

                if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
                    vImage = gc.createCompatibleVolatileImage(size.width, size.height);
                }

                Graphics2D g = (Graphics2D)vImage.getGraphics();

                g.transform(affine);

                // Make the Screen Black
                g.setColor(Color.black);
                g.fillRect(0, 0, size.width, size.height);

                SceneManager.render(g);

                // Draw the FPS Counter
                g.setColor(Color.white);
                g.drawString(currentFPS + "", 5, 15);

                g.dispose();

                g = (Graphics2D)window.getGraphics();
                g.drawImage(vImage, 0, 0, canvasSize.width, canvasSize.height, null);
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
        double factor = (canvasSize.width + canvasSize.height) / 2;
        double width = canvasSize.width / (factor / resolution);
        double height = canvasSize.height / (factor / resolution);
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

    public static Dimension getResolution() {
        return new Dimension(canvasSize.width, canvasSize.height);
    }

    public static Dimension getScaledResolution() {
        return new Dimension(size.width, size.height);
    }

    public static GraphicsConfiguration getGC() { return gc; }
}
