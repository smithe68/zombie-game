package Engine;
import com.sun.javaws.util.JfxHelper;

import javax.swing.JFrame;

public class Frame{
    JFrame frame = new JFrame();
    public Frame(){
        frame.setVisible(true);
    }
    private void window(){
        frame.add(frame);
        frame.setSize(1920,1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
