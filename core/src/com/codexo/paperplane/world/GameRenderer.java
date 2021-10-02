package com.codexo.paperplane.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.codexo.paperplane.helpers.AssetLoader;
import com.codexo.paperplane.objects.Grass;
import com.codexo.paperplane.objects.PaperPlane;
import com.codexo.paperplane.objects.Pipe;
import com.codexo.paperplane.objects.ScrollHandler;

public class GameRenderer {
    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private int midPointY;
    private int gameHeight;

    //Game Objects
    private PaperPlane paperPlane;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    //Game Assets
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

        ScreenUtils.clear(0, 0, 0, 1);

        shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
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

        // 1. Draw Grass
        drawGrass();

        // 2. Draw Pipes
        drawPipes();
        batch.enableBlending();

        // 3. Draw Skulls (requires transparency)
        drawSkulls();

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

        // TEMPORARY CODE! We will fix this section later:

        if (world.isReady()) {
            // Draw shadow first
            AssetLoader.shadow.draw(batch, "Touch me", (136 / 2) - (42), 76);
            // Draw text
            AssetLoader.font
                    .draw(batch, "Touch me", (136 / 2) - (42 - 1), 75);
        } else {

            if (world.isGameOver() || world.isHighScore()) {

                if (world.isGameOver()) {
                    AssetLoader.shadow.draw(batch, "Game Over", 25, 56);
                    AssetLoader.font.draw(batch, "Game Over", 24, 55);

                    AssetLoader.shadow.draw(batch, "High Score:", 23, 106);
                    AssetLoader.font.draw(batch, "High Score:", 22, 105);

                    String highScore = AssetLoader.getHighScore() + "";

                    // Draw shadow first
                    AssetLoader.shadow.draw(batch, highScore, (136 / 2)
                            - (3 * highScore.length()), 128);
                    // Draw text
                    AssetLoader.font.draw(batch, highScore, (136 / 2)
                            - (3 * highScore.length() - 1), 127);
                } else {
                    AssetLoader.shadow.draw(batch, "High Score!", 19, 56);
                    AssetLoader.font.draw(batch, "High Score!", 18, 55);
                }

                AssetLoader.shadow.draw(batch, "Try again?", 23, 76);
                AssetLoader.font.draw(batch, "Try again?", 24, 75);

                // Convert integer into String
                String score = world.getScore() + "";

                // Draw shadow first
                AssetLoader.shadow.draw(batch, score,
                        (136 / 2) - (3 * score.length()), 12);
                // Draw text
                AssetLoader.font.draw(batch, score,
                        (136 / 2) - (3 * score.length() - 1), 11);

            }

            // Convert integer into String
            String score = world.getScore() + "";

            // Draw shadow first
            AssetLoader.shadow.draw(batch, "" + world.getScore(), (136 / 2)
                    - (3 * score.length()), 12);
            // Draw text
            AssetLoader.font.draw(batch, "" + world.getScore(), (136 / 2)
                    - (3 * score.length() - 1), 11);

        }

        batch.end();

    }

    private void drawGrass() {
        // Draw the grass
        batch.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batch.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        batch.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batch.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batch.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batch.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batch.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batch.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        batch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batch.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batch.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batch.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }

    private void initGameObjects() {
        paperPlane = world.getPaperPlane();
        scroller = world.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
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