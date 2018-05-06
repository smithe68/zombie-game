package components;

import engine.Component;
import engine.Entity;
import engine.components.Camera;
import engine.components.Transform;
import engine.utility.Vector;

import java.awt.*;

public class EntityRenderer extends Component
{
    protected Vector renderPosition = new Vector();

    private Color tint = Color.white;

    public EntityRenderer(Entity parent) { super(parent); }

    @Override
    protected void update(float delta)
    {
        renderPosition.setX(transform.position.getX() + (renderResolution.width / 2));
        renderPosition.setY(-transform.position.getY() + (renderResolution.height / 2));
        renderPosition.sub(transform.getWidth() / 2, transform.getHeight() / 2);
        renderPosition.add(-Camera.getPosition().getX(), Camera.getPosition().getY());
    }

    @Override
    protected final void render(Graphics2D g)
    {
        setInternalRotation(g, transform.getRotation());
        g.setColor(tint);
        onRender(g);
        setInternalRotation(g, -transform.getRotation());
    }

    protected void onRender(Graphics2D g) { }

    private void setInternalRotation(Graphics2D g, float rotation)
    {
        g.rotate(Math.toRadians(rotation), renderPosition.getX() + transform.getWidth() / 2,
                renderPosition.getY() + transform.getHeight() / 2);
    }

    public void setTint(Color tint) { this.tint = tint; }
    public Color getTint() { return tint; }
}
