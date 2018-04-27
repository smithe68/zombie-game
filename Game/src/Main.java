import Engine.Renderer;
import Engine.Window;

import java.awt.*;

/**
 * This class is the starting point of the program
 * and initializes the engine and main loops.
 * @author Evan and Jakub
 */
public class Main
{
    public static void main(String[] args)
    {
        // Create the Main Window
        Dimension windowSize = new Dimension(800, 600);
        Window window = new Window("Zombie Game", windowSize);

        // Add a Renderer to the Window's Canvas
        Renderer renderer = new Renderer(window.canvas, 256, 60);

        renderer.setRenderQueue(g ->
        {
            // Render stuff here
            g.setColor(Color.white);
            g.fillRect(100, 100, 64, 64);
        });
    }
}
