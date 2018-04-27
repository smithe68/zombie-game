import Engine.Frame;

import java.awt.*;
import java.util.EventListener;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(()-> {
            Frame window = new Frame();
            window.setVisible(true);
        });

    }
}
