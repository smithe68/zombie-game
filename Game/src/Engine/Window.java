package Engine;

import java.awt.*;
import java.awt.event.*;

/**
 * This class creates a window on the screen.
 * @author Evan, updated by: Jakub
 */
public class Window
{
    public Canvas canvas;

    private Frame frame;

    public Window(String title)
    {
        this(title, new Dimension(800, 600));
        makeFullscreen();
    }

    public Window(String title, Dimension size)
    {
        frame = new Frame(title);
        frame.setPreferredSize(size);

        canvas = new Canvas();
        canvas.setSize(size);

        // Closes the Window when the 'X' Button is Pressed
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        frame.add(canvas);
        frame.pack();

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        canvas.requestFocus();
        canvas.transferFocus();
    }

    private void makeFullscreen()
    {
        frame.dispose();
        frame.setUndecorated(true);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        frame.setSize(toolkit.getScreenSize());

        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
