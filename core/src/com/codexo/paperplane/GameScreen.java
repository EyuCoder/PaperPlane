package com.codexo.paperplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.codexo.paperplane.helpers.InputHandler;
import com.codexo.paperplane.world.GameRenderer;
import com.codexo.paperplane.world.GameWorld;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    public float runTime;

    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");
        runTime = 0;

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("GameScreen FPS", (1 / delta) + " ");
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
        //world.resize(width, height);
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {
        Gdx.app.log("GameScreen", "dispose called");
    }
}
