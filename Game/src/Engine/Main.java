package Engine;

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
        // Create the Engine.Main Window
        Dimension windowSize = new Dimension(800, 600);
        Window window = new Window("Zombie Game", windowSize);

        Updater.initialize();

        Updater.setUpdateQueue(() ->
        {

        });

        // Add a initialize to the Window's Canvas
        Renderer.initialize(window.canvas, 256, 60);

        Renderer.setRenderQueue(g ->
        {
            // Render stuff here
            g.setColor(Color.white);
            g.fillRect(100, 100, 64, 64);
        });
    }
}
