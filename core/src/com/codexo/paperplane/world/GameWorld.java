package com.codexo.paperplane.world;

import com.badlogic.gdx.Gdx;
import com.codexo.paperplane.helpers.AssetLoader;
import com.codexo.paperplane.objects.PaperPlane;
import com.codexo.paperplane.objects.ScrollHandler;

public class GameWorld {

    private PaperPlane paperPlane;
    private ScrollHandler scroller;

    private boolean isAlive = true;

    public GameWorld(int midPointY){
        paperPlane = new PaperPlane(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta){
        //Gdx.app.log("GameWorld", "update");
        paperPlane.update(delta);
        scroller.update(delta);

        if (scroller.collides(paperPlane) && isAlive) {
            // Clean up on game over
            scroller.stop();
            AssetLoader.dead.play();
            isAlive = false;
        }
    }

    public PaperPlane getPaperPlane() {
        return paperPlane;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }
}
