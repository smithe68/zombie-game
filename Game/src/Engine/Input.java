package Engine;

import java.awt.event.*;
import java.util.HashMap;

public class Input implements KeyListener
{
    private static HashMap<Integer, Boolean> holdKeys = new HashMap<>();
    private static HashMap<Integer, Boolean> pressKeys = new HashMap<>();

    public static boolean getKey(int keycode) {
        return holdKeys.containsKey(keycode) && holdKeys.get(keycode);
    }

    public static boolean getKeyDown(int keycode)
    {
        var result = false;

        try
        {
            if(pressKeys.containsKey(keycode))
            {
                if(pressKeys.get(keycode))
                {
                    result = true;
                    pressKeys.put(keycode, false);
                }
            }
        }
        catch (NullPointerException ignored) {}

        return result;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e)
    {
        holdKeys.put(e.getKeyCode(), true);

        if(!pressKeys.containsKey(e.getKeyCode())) {
            pressKeys.put(e.getKeyCode(), true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        holdKeys.put(e.getKeyCode(), false);
        pressKeys.remove(e.getKeyCode());
    }
}
