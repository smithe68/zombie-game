package engine;

import engine.components.Transform;
import engine.rendering.Renderer;

import java.awt.*;

public class Component
{
    protected Entity parent;
    protected Transform transform;
    protected GraphicsConfiguration graphicsConfiguration;
    protected Dimension renderResolution;

    public Component(Entity parent)
    {
        this.parent = parent;
        transform = parent.transform;
        renderResolution = Renderer.getScaledResolution();
        graphicsConfiguration = Renderer.getGC();
    }

    protected void render(Graphics2D g) {}
    protected void update() {}
}
