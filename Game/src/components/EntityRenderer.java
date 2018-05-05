package components;

import engine.Component;
import engine.Entity;
import engine.components.Camera;
import engine.components.Transform;
import engine.utility.Vector;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class EntityRenderer extends Component
{
    protected Vector renderPosition = new Vector();

    private Color tint = Color.white;
    private AffineTransform affine;

    public EntityRenderer(Entity parent)
    {
        super(parent);
        affine = new AffineTransform();
    }

    @Override
    protected void update()
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
        g.transform(affine);
        g.rotate(Math.toRadians(rotation), renderPosition.getX() + transform.getWidth() / 2,
                renderPosition.getY() + transform.getHeight() / 2);
    }

    public void lookAt(Transform other)
    {
        transform.setRotation((float)Math.toDegrees(Math.atan2(-other.position.getY(),
                other.position.getX())));
    }

    public void setTint(Color tint) { this.tint = tint; }
    public Color getTint() { return tint; }
}
