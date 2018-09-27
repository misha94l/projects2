package com.mgstudio.myrain.controler;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.mgstudio.myrain.view.GameScreen;

public class BucketControler {

    private Polygon buckBounds;
    float speed, velocity = 10000f, speedMax = 10000f;


    public BucketControler(Polygon buckBounds) {
        this.buckBounds = buckBounds;
    }

    public void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            speed += velocity * GameScreen.deltaCff;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            speed -= velocity * GameScreen.deltaCff;
        }
      else {
         downSpeed();
        }
        speed = sliceSpeed();

        buckBounds.setPosition(buckBounds.getX() + MathUtils.cosDeg(buckBounds.getRotation()) * speed * GameScreen.deltaCff,
                buckBounds.getY() + MathUtils.sinDeg(buckBounds.getRotation()) * speed * GameScreen.deltaCff);
        checkScreen();
    }

    private void downSpeed() {
        if (speed > velocity) {
           // speed -= velocity * GameScreen.deltaCff;
            speed = 0;
        }
        else if (speed < -velocity) {
            //speed += velocity * GameScreen.deltaCff;
            speed = 0;
        }
        else
            speed = 0;
    }

    private float sliceSpeed() {
        if (speed > speedMax) {
            return speedMax;
        }
        else if (speed < - speedMax) {
            return -speedMax;
        }
        return speed;
    }

    private void checkScreen() {
        if (buckBounds.getX() > 800f + 64f) {
            buckBounds.setPosition(-64f, 20f);
        }
        else if (buckBounds.getX() < 0 - 64f){
                buckBounds.setPosition(864f, 20f);
        }
    }
}
