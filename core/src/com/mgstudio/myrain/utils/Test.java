package com.mgstudio.myrain.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mgstudio.myrain.model.Hearts;

public class Test {
    public static Hearts[] hs = new Hearts[3];
    private static float posX = 10f;
    private static Texture tex = new Texture(Gdx.files.internal("heart.png"));
    public static void addHearts() {
        for (int i = 0; i < hs.length; i++) {
            hs[i] = (new Hearts(tex, posX, 400f, 80f, 60f));
            posX += 100f;
        }
    }
}
