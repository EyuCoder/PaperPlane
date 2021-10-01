package com.codexo.paperplane;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.codexo.paperplane.helpers.AssetLoader;

public class PaperPlaneGame extends Game {
	@Override
	public void create() {
		Gdx.app.log("PaperPlaneGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
