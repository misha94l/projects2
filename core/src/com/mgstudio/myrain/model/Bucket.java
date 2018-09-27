package com.mgstudio.myrain.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mgstudio.myrain.controler.BucketControler;


public class Bucket extends GameObject {
    private BucketControler bc;
    public Bucket(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
        bc = new BucketControler(bounds);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        bc.handle();
    }

}
