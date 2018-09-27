package com.mgstudio.myrain.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mgstudio.myrain.MainMyRain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "MyRain";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new MainMyRain(), config);
	}
}
