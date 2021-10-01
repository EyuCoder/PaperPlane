package com.codexo.paperplane;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class PaperPlaneGame extends Game {
	@Override
	public void create() {
		Gdx.app.log("PaperPlaneGame", "created");
		setScreen(new GameScreen());
	}
}
