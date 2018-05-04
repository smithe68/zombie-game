package entities;

import engine.*;
import engine.components.Visual;
import engine.utility.Mafs;
import engine.SceneManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Hero extends Entity
{
    private float health = 100f;
    private float moveSpeed = 1f;

    private ProgressBar healthBar;

    public void start()
    {
        visual.setRenderType(Visual.RenderType.Image);
        visual.setSprite("Hero.png");
        visual.setLayer(1);

        physics.setHasCollision(true);

        healthBar = (ProgressBar)SceneManager.createEntity(new ProgressBar());

        setupHealthBar();
    }

    public void update()
    {
        Camera.x = Mafs.lerp(Camera.x, transform.getX(), Time.deltaTime);
        Camera.y = Mafs.lerp(Camera.y, transform.getY(), Time.deltaTime);

        float hori = Input.getInputAxis("Horizontal");
        float vert = Input.getInputAxis("Vertical");

        physics.setVelocity(hori * moveSpeed * Time.deltaTime,
                vert * moveSpeed * Time.deltaTime);

        if(Input.getKeyDown(KeyEvent.VK_SPACE)) {
            SceneManager.createEntity(new Bullet());
        }

        float mouseX = Input.getRelativeMouseX();
        float mouseY = Input.getRelativeMouseY();

        transform.setRot((float)Math.toDegrees(Math.atan2(mouseY, mouseX)));

        healthBar.fillAmount = health / 100f;
    }

    private void setupHealthBar()
    {
        healthBar.transform.setPos(-10, 10);
        healthBar.transform.setSize(84, 16);

        healthBar.visual.setAnchor(Visual.Anchor.TOP_RIGHT);

        healthBar.fillColor = Color.red;
    }

    public void takeDamage(float amount)
    {
        health -= amount;
        health = Mafs.clamp(health, 0, 100);
    }
}
