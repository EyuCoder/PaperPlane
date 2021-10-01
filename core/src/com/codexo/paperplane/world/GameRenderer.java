package com.codexo.paperplane.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.codexo.paperplane.helpers.AssetLoader;
import com.codexo.paperplane.objects.PaperPlane;

public class GameRenderer {
    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private int midPointY;
    private int gameHeight;

    private PaperPlane paperPlane;

    private TextureRegion bg, grass;
    private Animation paperPlaneAnim;
    private TextureRegion paperPlaneMid, paperPlaneDown, paperPlaneUp;
    private TextureRegion skullUp, skullDown, bar;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        this.world = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();
    }

    public void render(float runTime) {
//        Gdx.app.log("GameRenderer", "render");
        ScreenUtils.clear(0, 0, 0, 1);

        shapeRenderer.begin(ShapeType.Filled);
        // Draw Background
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);
        shapeRenderer.end();

        batch.begin();
        batch.disableBlending();
        batch.draw(bg, 0, midPointY + 23, 136, 43);

        batch.enableBlending();
        if (paperPlane.shouldntFlap()) {
            batch.draw(paperPlaneMid, paperPlane.getX(), paperPlane.getY(),
                    paperPlane.getWidth() / 2.0f, paperPlane.getHeight() / 2.0f,
                    paperPlane.getWidth(), paperPlane.getHeight(), 1, 1, paperPlane.getRotation());
        } else {
            batch.draw((TextureRegion) paperPlaneAnim.getKeyFrame(runTime), paperPlane.getX(),
                    paperPlane.getY(), paperPlane.getWidth() / 2.0f,
                    paperPlane.getHeight() / 2.0f, paperPlane.getWidth(), paperPlane.getHeight(),
                    1, 1, paperPlane.getRotation());
        }
        batch.end();
    }

    private void initGameObjects() {
        paperPlane = world.getPaperPlane();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        paperPlaneAnim = AssetLoader.paperPlaneAnim;
        paperPlaneMid = AssetLoader.paperPlane;
        paperPlaneDown = AssetLoader.paperPlaneDown;
        paperPlaneUp = AssetLoader.paperPlaneUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }
}