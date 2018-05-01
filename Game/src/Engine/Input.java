package Engine;

import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Input implements KeyListener
{
    private static boolean[] currentKeys = new boolean[256];
    private static boolean[] lastKeys = new boolean[256];
    private static long time = 12;

    public static boolean getKey(int keycode) { return currentKeys[keycode]; }

    public static boolean getKeyDown(int keycode) {
        try
        {
            TimeUnit.MILLISECONDS.sleep(time);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }


        return currentKeys[keycode] && !lastKeys[keycode];
    }

    public static boolean getKeyUp(int keycode) {

        return !currentKeys[keycode] && lastKeys[keycode];
    }

    static void updateInput() {
        lastKeys = currentKeys.clone();

    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        currentKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        currentKeys[e.getKeyCode()] = false;
    }
}
