package components;

import engine.Component;
import engine.Entity;
import engine.components.Camera;
import engine.utility.Vector;

import java.awt.*;

public class EntityRenderer extends Component
{
    protected Vector renderPosition = new Vector();
    private Color tint = Color.white;

    public EntityRenderer(Entity parent) {
        super(parent);
    }

    @Override
    protected void update()
    {
        renderPosition.setX(parent.transform.position.getX() + (renderResolution.width / 2));
        renderPosition.setY(-parent.transform.position.getY() + (renderResolution.height / 2));
        renderPosition.sub(parent.transform.getWidth() / 2, parent.transform.getHeight() / 2);
        renderPosition.add(-Camera.getPosition().getX(), Camera.getPosition().getY());
    }

    @Override
    protected void render(Graphics2D g)
    {
        g.setColor(tint);
    }

    public void setTint(Color tint) { this.tint = tint; }
    public Color getTint() { return tint; }
}
