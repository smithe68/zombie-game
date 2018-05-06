package engine;

import engine.components.Transform;
import engine.rendering.Renderer;

import java.awt.*;

public class Component
{
    protected Entity parent;
    protected Transform transform;

    protected boolean debugActive = false;

    protected static GraphicsConfiguration graphicsConfiguration;
    protected static Dimension renderResolution;

    public Component(Entity parent)
    {
        this.parent = parent;
        transform = parent.transform;
        renderResolution = Renderer.getScaledResolution();
        graphicsConfiguration = Renderer.getGC();
    }

    protected void render(Graphics2D g) {}
    protected void update(float delta) {}
    protected void cleanup() {}

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

    public void setDebugActive(boolean active) {
        debugActive = active;
    }
}
