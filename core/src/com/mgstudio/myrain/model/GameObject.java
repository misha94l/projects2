package com.mgstudio.myrain.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;

public abstract class GameObject {
    public Polygon bounds;
    public Sprite object;

    public GameObject(Texture texture, float x, float y, float width, float height) {

        // Инициализируем спрайт и указываем позицию, размеры, точку ротации
        object = new Sprite(texture);
        object.setSize(width, height);
        object.setOrigin(width/2, height/2);
        object.setPosition(x, y);

        // Инициализируем полигон и указываем позицию, размеры, точку ротации
        bounds = new Polygon(new float[] {0f, 0f, width, 0f, width, height, 0f, height});
        bounds.setPosition(x, y);
        bounds.setOrigin(width/2, height/2);
    }

    public void draw(SpriteBatch batch) {
        object.setPosition(bounds.getX(), bounds.getY());
        object.setRotation(bounds.getRotation());
        object.draw(batch);
    }
}
