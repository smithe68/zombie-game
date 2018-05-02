package Engine;

import java.awt.event.*;

public class Input implements KeyListener
{
    private static boolean[] currentKeys = new boolean[196];
    private static boolean[] lastKeys = new boolean[196];

    public static boolean getKey(int keycode) { return currentKeys[keycode]; }

    public static boolean getKeyDown(int keycode) {
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
