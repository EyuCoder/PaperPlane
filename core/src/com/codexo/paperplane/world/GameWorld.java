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

    public GameWorld(int midPointY) {
        paperPlane = new PaperPlane(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
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
        }
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
}
