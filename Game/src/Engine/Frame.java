package Engine;
import javax.swing.JFrame;

public class Frame extends JFrame{
    public Frame(){
    }
    private void window(){
        add(new Frame());

        setSize(1920,1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
