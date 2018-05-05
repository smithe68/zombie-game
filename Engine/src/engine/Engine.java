package engine;

import engine.rendering.Renderer;
import engine.rendering.Updater;
import engine.rendering.Window;

import java.awt.*;

/**
 * This class is the starting point of the program
 * and initializes the engine and main loops.
 * @author Evan and Jakub
 */
public class Engine
{
    public Engine()
    {
        // Create a Window
        var windowSize = new Dimension(1280, 720);
        Window window = new Window("Zombie Game", windowSize);

        // Initialize engine components
        Renderer.initialize(window, 384, 60);
        Updater updater = new Updater();
        updater.start();

        Input.initialize(window);
    }
}
