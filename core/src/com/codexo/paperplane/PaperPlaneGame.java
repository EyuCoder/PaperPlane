package com.codexo.paperplane;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.codexo.paperplane.helpers.AssetLoader;
import com.codexo.paperplane.screens.SplashScreen;

public class PaperPlaneGame extends Game {
	@Override
	public void create() {
		Gdx.app.log("PaperPlaneGame", "created");
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
