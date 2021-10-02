package com.codexo.paperplane.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.codexo.paperplane.PaperPlaneGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Paper Plane";
//		config.width = 272;
//		config.height = 408;
		config.width = 408;
		config.height = 612;
		new LwjglApplication(new PaperPlaneGame(), config);
	}
}
