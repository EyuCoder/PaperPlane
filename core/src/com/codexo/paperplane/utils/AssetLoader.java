package com.codexo.paperplane.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.codexo.paperplane.Constants;

public class AssetLoader implements Disposable, AssetErrorListener {

    public static final String TAG = AssetLoader.class.getName();
    public static final AssetLoader instance = new AssetLoader();

    public PaperPlaneAssets paperPlaneAssets;
    public PlatformAssets platformAssets;

    private AssetManager assetManager;

    public static Sound dead, flap, coin;
    public static BitmapFont font, shadow;

    private static Preferences prefs;

    private AssetLoader() {
    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        paperPlaneAssets = new PaperPlaneAssets(atlas);
        platformAssets = new PlatformAssets(atlas);


        dead = Gdx.audio.newSound(Gdx.files.internal("audio/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("audio/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("audio/coin.wav"));

        font = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        prefs = Gdx.app.getPreferences("PaperPlane");
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
            prefs.flush();
        }
    }

    public class PaperPlaneAssets {
        public final AtlasRegion paperPlane;
        public final AtlasRegion paperPlaneDown;
        public final AtlasRegion paperPlaneUp;

        public final Animation paperPlaneAnim;

        public PaperPlaneAssets(TextureAtlas atlas) {
            paperPlane = atlas.findRegion(Constants.PAPERPLANE);
            paperPlane.flip(false, true);
            paperPlaneDown = atlas.findRegion(Constants.PAPERPLANE_DOWN);
            paperPlaneDown.flip(false, true);
            paperPlaneUp = atlas.findRegion(Constants.PAPERPLANE_UP);
            paperPlaneUp.flip(false, true);

            Array<AtlasRegion> paperPlaneFrames = new Array<>();
            paperPlaneFrames.add(atlas.findRegion(Constants.PAPERPLANE_UP));
            paperPlaneFrames.add(atlas.findRegion(Constants.PAPERPLANE));
            paperPlaneFrames.add(atlas.findRegion(Constants.PAPERPLANE_DOWN));
            paperPlaneAnim = new Animation(Constants.FLAP_LOOP_DURATION, paperPlaneFrames, Animation.PlayMode.LOOP_PINGPONG);

        }
    }

    public class PlatformAssets {
        public final AtlasRegion skullUp, skullDown, bar, bg, grass, logo, gameLogo;
        public PlatformAssets(TextureAtlas atlas){
            skullUp = atlas.findRegion(Constants.PIPE_HEAD);
            skullDown = atlas.findRegion(Constants.PIPE_HEAD);
            skullDown.flip(false, true);
            bar = atlas.findRegion(Constants.PIPE);
            bg = atlas.findRegion(Constants.BACKGROUND);
            bg.flip(false, true);
            //bgNight = atlas.findRegion(Constants.BACKGROUND_NIGHT);
            grass = atlas.findRegion(Constants.GRASS);
            grass.flip(false, true);
            logo = atlas.findRegion(Constants.LOGO);
            gameLogo = atlas.findRegion(Constants.LOGO);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();

        dead.dispose();
        flap.dispose();
        coin.dispose();
        font.dispose();
        shadow.dispose();
    }
}
