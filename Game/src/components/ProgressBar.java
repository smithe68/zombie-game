package components;

import engine.Component;
import engine.Entity;
import engine.SceneManager;
import engine.utility.Mafs;

import java.awt.*;

public class ProgressBar extends Component
{
    public float value;
    public Color fillColor = Color.white;
    public ShapeRenderer renderer;

    private Entity fillObj;
    private ShapeRenderer fill;

    public ProgressBar(Entity parent)
    {
        super(parent);
        renderer = (ShapeRenderer)addComponent(new ShapeRenderer(parent));
        renderer.setInWorldSpace(false);
        renderer.setTint(Color.gray);

        parent.layer = 10;

        fillObj = SceneManager.createEntity("Fill");
        fill = (ShapeRenderer)fillObj.addComponent(new ShapeRenderer(fillObj));
        fillObj.layer = 11;
    }

    @Override
    protected void update(float delta)
    {
        fill.setInWorldSpace(false);
        fill.setScreenAnchor(renderer.getScreenAnchor());
        fill.setTint(fillColor);

        fillObj.transform.position.set(transform.position);

        value = Mafs.clamp(value, 0, 1);
        fillObj.transform.setSize(transform.getWidth() * value, transform.getHeight());
    }
}
