package com.codexo.paperplane.helpers;

import com.badlogic.gdx.InputProcessor;
import com.codexo.paperplane.objects.PaperPlane;

public class InputHandler implements InputProcessor {
    private PaperPlane paperPlane;

    public InputHandler(PaperPlane paperPlane){
        this.paperPlane = paperPlane;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        paperPlane.onClick();
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
