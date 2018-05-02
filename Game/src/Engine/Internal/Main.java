package Engine.Internal;

import Engine.SceneManager;

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

        // Initialize Engine Components
        Renderer.initialize(window.canvas, 384, 60);

        // Intialize Input
        Input input = new Input();
        window.canvas.addKeyListener(input);
        window.canvas.addMouseMotionListener(input);
        window.canvas.addMouseListener(input);
        Input.initialize();

        SceneManager.initialize();
        Updater.initialize();
    }
}
