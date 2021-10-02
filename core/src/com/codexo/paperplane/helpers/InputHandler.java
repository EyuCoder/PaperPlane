package com.codexo.paperplane.helpers;

import com.badlogic.gdx.InputProcessor;
import com.codexo.paperplane.objects.PaperPlane;
import com.codexo.paperplane.world.GameWorld;

public class InputHandler implements InputProcessor {
    private final PaperPlane paperPlane;
    private final GameWorld gameWorld;

    public InputHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        paperPlane = gameWorld.getPaperPlane();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (gameWorld.isReady()) {
            gameWorld.start();
        }

        paperPlane.onClick();

        if (gameWorld.isGameOver()|| gameWorld.isHighScore()) {
            // Reset all variables, go to GameState.READ
            gameWorld.restart();
        }

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
