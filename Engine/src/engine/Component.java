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
    protected void update(float delta) {}

    // Add Component Shortcut
    protected Component addComponent(Component component) {
        return parent.addComponent(component);
    }

    // Get Component Shortcut
    public <T extends Component> Component getComponent(Class<T> clazz) {
        return parent.getComponent(clazz);
    }

    // Remove Component Shortcut
    public void removeComponent(Component component) {
        parent.removeComponent(component);
    }
}
