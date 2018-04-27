import Engine.Frame;

import java.awt.*;

public class Main
{
    public static boolean isRunning;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            Frame window = new Frame();
        });
    }
}
