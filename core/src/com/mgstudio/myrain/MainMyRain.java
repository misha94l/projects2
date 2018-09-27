package com.mgstudio.myrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mgstudio.myrain.view.GameScreen;

public class MainMyRain extends Game {

	private Screen gameScreen;
	@Override
	public void create () {
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}


}

