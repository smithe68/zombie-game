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
    protected void update(float delta) {
        renderPosition = getRenderedPosition(transform);
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

    public static Vector getRenderedPosition(Transform t)
    {
        Vector renderPos = new Vector();
        renderPos.setX(t.position.getX() + (renderResolution.width / 2));
        renderPos.setY(-t.position.getY() + (renderResolution.height / 2));
        renderPos.sub(t.getWidth() / 2, t.getHeight() / 2);
        renderPos.add(-Camera.getPosition().getX(), Camera.getPosition().getY());
        return renderPos;
    }
}
