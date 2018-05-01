package Engine;

import java.awt.*;

public class UI extends Entity
{
    protected Anchor anchor = Anchor.TOP_LEFT;

    public UI() {
    }

    protected final void onRender(Graphics2D g)
    {
        switch(anchor)
        {
            case TOP_LEFT:

                break;
        }
    }

    public enum Anchor
    {
        TOP_LEFT,
        TOP_RIGHT,
        CENTER,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }
}
