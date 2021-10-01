package com.codexo.paperplane.world;

import com.badlogic.gdx.Gdx;
import com.codexo.paperplane.objects.PaperPlane;

public class GameWorld {

    private PaperPlane paperPlane;

    public GameWorld(int midPointY){
        paperPlane = new PaperPlane(33, midPointY - 5, 17, 12);
    }

    public void update(float delta){
        //Gdx.app.log("GameWorld", "update");
        paperPlane.update(delta);
    }

    public PaperPlane getPaperPlane() {
        return paperPlane;
    }
}
