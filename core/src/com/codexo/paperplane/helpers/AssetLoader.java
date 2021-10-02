package com.codexo.paperplane.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation paperPlaneAnim;
    public static TextureRegion paperPlane, paperPlaneDown, paperPlaneUp;

    public static TextureRegion skullUp, skullDown, bar;

    public static Sound dead, flap, coin;

    public static BitmapFont font, shadow;

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        paperPlaneDown = new TextureRegion(texture, 136, 0, 17, 12);
        paperPlaneDown.flip(false, true);

        paperPlane = new TextureRegion(texture, 153, 0, 17, 12);
        paperPlane.flip(false, true);

        paperPlaneUp = new TextureRegion(texture, 170, 0, 17, 12);
        paperPlaneUp.flip(false, true);

        TextureRegion[] paperPlanes = {paperPlaneDown, paperPlane, paperPlaneUp};
        paperPlaneAnim = new Animation(0.06f, paperPlanes);
        paperPlaneAnim.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("audio/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("audio/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("audio/coin.wav"));

        font = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);
    }

    public static void dispose() {
        texture.dispose();
        dead.dispose();
        flap.dispose();
        coin.dispose();
        font.dispose();
        shadow.dispose();
    }
}
