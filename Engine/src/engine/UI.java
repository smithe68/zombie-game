package engine;

import engine.components.Visual;

public class UI extends Entity
{
    public UI()
    {
        visual.setInScreenSpace(true);
        visual.setRenderType(Visual.RenderType.Rectangle);
    }
}
