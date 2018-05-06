package components;

import engine.*;
import engine.components.Camera;
import engine.enums.Anchor;
import engine.utility.*;

import java.awt.Color;

public class Player extends Component
{
    private float health = 100f;

    private Physics physics;
    private SpriteRenderer spriteRenderer;

    private ProgressBar healthBar;

    public Player(Entity parent)
    {
        super(parent);

        spriteRenderer = (SpriteRenderer)addComponent(new SpriteRenderer(parent));

        physics = (Physics)addComponent(new Physics(parent));
        physics.colliderSize.set(16, 16);
        setupCollisionCheck();

        spriteRenderer.setTint(Color.cyan);
        spriteRenderer.setSprite("Hero.png");

        parent.layer = 1;

        createHealthBar();
    }

    @Override
    protected void update(float delta)
    {
        movement();
        rotation();

        cameraFollow(delta);
        shooting();

        manageHealthBar();
    }

    private void cameraFollow(float delta) {
        Camera.setPosition(Vector.lerp(Camera.getPosition(), transform.position, delta * 0.1f));
    }

    private void rotation()
    {
        float mouseX = Input.getRelativeMouseX();
        float mouseY = Input.getRelativeMouseY();

        transform.setRotation((float)Math.toDegrees(Math.atan2(mouseY, mouseX)));
    }

    private void movement()
    {
        float hori = Input.getInputAxis("Horizontal");
        float vert = Input.getInputAxis("Vertical");

        if(hori != 0 & vert != 0)
        {
            hori *= 0.75f;
            vert *= 0.75f;
        }

        physics.velocity.set(hori * 2, vert * 2);
    }

    private void shooting()
    {
        if(Input.getMouseButton(1))
        {
            Vector dir = Vector.rotToVector(transform.getRotation());
            dir.normalize();

            var obj = SceneManager.createEntity("Bullet");
            var bullet = (Bullet)obj.addComponent(new Bullet(obj));
            obj.transform.position.set(transform.position);
            bullet.setInitialVelocity(dir);
        }
    }

    private void createHealthBar()
    {
        var obj = SceneManager.createEntity("Health Bar");

        healthBar = (ProgressBar)obj.addComponent(new ProgressBar(obj));
        healthBar.renderer.setScreenAnchor(Anchor.TOP_RIGHT);
        healthBar.fillColor = Color.red;

        obj.transform.position.set(-10, 10);
        obj.transform.setSize(64, 12);
    }

    private void manageHealthBar()
    {
        if(healthBar == null) { return; }
        healthBar.value = health / 100f;
    }

    private void setupCollisionCheck()
    {
        if(physics == null) { return; }

        physics.onCollision(e ->
        {
            if(e.name.equals("Zombie"))
            {
                changeHealth(-0.1f);
            }
        });
    }

    public void changeHealth(float amount)
    {
        health += amount;
        health = Mafs.clamp(health, 0, 100);
    }
}
