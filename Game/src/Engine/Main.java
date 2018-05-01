package Engine;

import Entities.*;

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
        window.canvas.addKeyListener(new Input());

        Renderer.initialize(window.canvas, 256, 60);
        Updater.initialize();

        SceneManager.createEntity(new Hero());
        SceneManager.createEntity(new Tile());
        SceneManager.createEntity(new SmallBackAndForthZombie());
        SceneManager.createEntity(new SmallCircleZombie());
        SceneManager.createEntity(new SmallFollowZombie());
        SceneManager.createEntity(new Bullet());
    }
}
