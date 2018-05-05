package components;

import engine.Entity;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteRenderer extends EntityRenderer
{
    private BufferedImage sprite;

    public SpriteRenderer(Entity parent) {
        super(parent);
    }

    @Override
    protected void onRender(Graphics2D g)
    {
        if(sprite == null) { return; }

        g.drawImage(sprite, (int)renderPosition.getX(), (int)renderPosition.getY(),
                (int)parent.transform.getWidth(), (int)parent.transform.getHeight(), null);
    }

    public void setSprite(BufferedImage image) {
        this.sprite = image;
    }

    public void setSprite(String spriteName)
    {
        var view = FileSystemView.getFileSystemView();
        String docs = view.getDefaultDirectory().toString();
        var path = new File(docs + "/Zombie Game/Game/src/resources/sprites/" + spriteName);
        if(path.mkdirs()) { System.out.println("Created Sprites Folder"); }

        BufferedImage raw;

        try
        {
            raw = ImageIO.read(path);
            sprite = graphicsConfiguration.createCompatibleImage(raw.getWidth(), raw.getHeight(), raw.getTransparency());
            sprite.getGraphics().drawImage(raw, 0, 0, raw.getWidth(), raw.getHeight(), null);
        }
        catch(IOException e) {
            System.err.println("Image [" + path + "] does not exist");
        }
    }
}
