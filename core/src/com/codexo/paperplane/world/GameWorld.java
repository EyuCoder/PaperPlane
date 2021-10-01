package com.codexo.paperplane.world;

import com.badlogic.gdx.Gdx;
import com.codexo.paperplane.objects.PaperPlane;
import com.codexo.paperplane.objects.ScrollHandler;

public class GameWorld {

    private PaperPlane paperPlane;
    private ScrollHandler scroller;

    public GameWorld(int midPointY){
        paperPlane = new PaperPlane(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta){
        //Gdx.app.log("GameWorld", "update");
        paperPlane.update(delta);
        scroller.update(delta);
    }

    public PaperPlane getPaperPlane() {
        return paperPlane;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }
}
