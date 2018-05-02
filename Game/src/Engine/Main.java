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
        // Create a Window
        Dimension windowSize = new Dimension(1280, 720);
        Window window = new Window("Zombie Game", windowSize);
        window.canvas.addKeyListener(new Input());

        // Initialize Engine Components
        Renderer.initialize(window.canvas, 256, 60);
        SceneManager.initialize();
        Updater.initialize();
    }
}
