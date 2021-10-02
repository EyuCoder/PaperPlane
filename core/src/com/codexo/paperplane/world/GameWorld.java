package com.codexo.paperplane.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.codexo.paperplane.helpers.AssetLoader;
import com.codexo.paperplane.objects.PaperPlane;
import com.codexo.paperplane.objects.ScrollHandler;

public class GameWorld {

    private PaperPlane paperPlane;
    private ScrollHandler scroller;

    private Rectangle ground;
    private int score = 0;
    private int midPointY;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE;
    }

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        this.midPointY = midPointY;
        paperPlane = new PaperPlane(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    private void updateReady(float delta) {
        // Do nothing for now
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        paperPlane.update(delta);
        scroller.update(delta);

        if (scroller.collides(paperPlane) && paperPlane.isAlive()) {
            // Clean up on game over
            scroller.stop();
            paperPlane.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(paperPlane.getBoundingCircle(), ground)) {
            scroller.stop();
            paperPlane.die();
            paperPlane.decelerate();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public PaperPlane getPaperPlane() {
        return paperPlane;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        paperPlane.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
}
