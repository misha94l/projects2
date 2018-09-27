package com.mgstudio.myrain.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;


public class Droplet extends GameObject {

        public static Array<Droplet> arrDrop = new Array<Droplet>();

    public Droplet(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
    }


}
