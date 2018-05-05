package engine;

import engine.rendering.Renderer;

import java.awt.*;

public class Component
{
    protected Entity parent;
    protected Dimension renderResolution;

    public Component(Entity parent)
    {
        this.parent = parent;
        renderResolution = Renderer.getScaledResolution();
    }

    protected void render(Graphics2D g) {}
    protected void update() {}
}
