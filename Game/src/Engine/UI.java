package Engine;

import Engine.Components.Visual;

public class UI extends Entity
{
    public UI()
    {
        visual.setInScreenSpace(true);
        visual.setRenderType(Visual.RenderType.Rectangle);
    }
}
