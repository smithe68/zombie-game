package components;

import engine.Entity;
import enums.Shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ShapeRenderer extends EntityRenderer
{
    private boolean isFilled = true;
    private Shapes shape = Shapes.Rectangle;

    public ShapeRenderer(Entity parent) {
        super(parent);
    }

    @Override
    protected void onRender(Graphics2D g)
    {
        switch(shape)
        {
            case Rectangle:
                if(isFilled) { g.fill(new Rectangle2D.Float(renderPosition.getX(), renderPosition.getY(),
                        transform.getWidth(), transform.getHeight())); }
                else { g.draw(new Rectangle.Float(renderPosition.getX(), renderPosition.getY(),
                        transform.getWidth(), transform.getHeight())); }
                break;

            case Ellipse:
                if(isFilled) { g.fill(new Ellipse2D.Float(renderPosition.getX(), renderPosition.getY(),
                        transform.getWidth(), transform.getHeight())); }
                else { g.draw(new Rectangle.Float(renderPosition.getX(), renderPosition.getY(),
                        transform.getWidth(), transform.getHeight())); }
                break;
        }
    }

    public void setFilled(boolean isFilled) { this.isFilled = isFilled; }
    public boolean getFilled() { return isFilled; }

    public void setShape(Shapes shape) { this.shape = shape; }
    public Shapes getShape() { return shape; }
}
