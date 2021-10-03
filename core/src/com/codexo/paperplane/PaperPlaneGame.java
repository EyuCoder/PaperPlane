package com.codexo.paperplane;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.codexo.paperplane.utils.AssetLoader;
import com.codexo.paperplane.screens.SplashScreen;

public class PaperPlaneGame extends Game {
	@Override
	public void create() {
		Gdx.app.log("PaperPlaneGame", "created");
		AssetManager am = new AssetManager();
		AssetLoader.instance.init(am);
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
//		AssetLoader.dispose();
	}
}
