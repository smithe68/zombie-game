package engine;

import engine.rendering.Renderer;
import engine.rendering.Window;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.HashMap;

public class Input implements KeyListener, MouseListener, MouseMotionListener
{
    private static HashMap<Integer, Boolean> holdKeys = new HashMap<>();
    private static HashMap<String, KeyContainer> inputAxis = new HashMap<>();
    private static HashMap<Integer, Boolean> pressKeys = new HashMap<>();

    private static boolean[] mouseButtons;

    private static float mouseX;
    private static float mouseY;

    private static Dimension scaledRes;
    private static Dimension screenRes;

    public static void initialize(Window window)
    {
        var input = new Input();

        window.addKeyListener(input);
        window.addMouseMotionListener(input);
        window.addMouseListener(input);

        scaledRes = Renderer.getScaledResolution();
        screenRes = Renderer.getResolution();

        mouseButtons = new boolean[5];

        inputAxis.put("Horizontal", new KeyContainer(KeyEvent.VK_D, KeyEvent.VK_A));
        inputAxis.put("Vertical", new KeyContainer(KeyEvent.VK_W, KeyEvent.VK_S));
    }

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

    public static int getInputAxis(String axisName)
    {
        if(!inputAxis.containsKey(axisName))
        {
            System.err.println("Axis " + axisName + " does not exist!");
            return 0;
        }

        int axis = 0;

        if(getKey(inputAxis.get(axisName).posKey)) { axis = 1; }
        if(getKey(inputAxis.get(axisName).negKey)) { axis = -1; }

        return axis;
    }

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

    public static float getMouseX() { return mouseX; }
    public static float getMouseY() { return mouseY; }

    public static boolean getMouseButton(int button) {
        return mouseButtons[button];
    }

    public static float getRelativeMouseX() {
        return mouseX - ((screenRes.width / (screenRes.width / scaledRes.width)) / 2);
    }

    public static float getRelativeMouseY() {
        return mouseY - ((screenRes.height / (screenRes.height / scaledRes.height)) / 2);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        mouseX = (e.getX() / (screenRes.width / scaledRes.width));
        mouseY = (e.getY() / (screenRes.height / scaledRes.height));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseButtons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButtons[e.getButton()] = false;
    }

    //<editor-fold desc="> Unused Implementation">

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    //</editor-fold>
}
