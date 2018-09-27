package com.mgstudio.myrain.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mgstudio.myrain.model.*;

import java.util.Iterator;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Sound dropSound;
    private Music rainMusic;
    private Bucket bck;
    private Texture tex1;
    public static float deltaCff;
    private Texture tex2;
    private long lastDropTime;
    private Texture tex3;
    private Background bcg;
    private Texture tex4;
    private float posX = 7f;
    private Array<Hearts> arrHrt = new Array<Hearts>();
    private Texture go;
    private GameOver goo;

    @Override
    public void show() {

        // инициализируем отрисовщик
        batch = new SpriteBatch();

        // инициализация камеры
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // инициализация музыки и звуков капли
        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        rainMusic.setLooping(true);
        rainMusic.play();

        tex3 = new Texture(Gdx.files.internal("back.jpg"));
        tex3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bcg = new Background(tex3, 0, 0 , 800f, 480f);

        //
        tex1 = new Texture(Gdx.files.internal("bucket.png"));
        tex1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bck = new Bucket(tex1, (float) 800 / 2 -64/2, 20f, 64f, 64f);

        //
        tex2 = new Texture(Gdx.files.internal("droplet.png"));
        tex2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        tex4 = new Texture(Gdx.files.internal("heart.png"));
        tex4.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (int j = 0; j < 3; j++) {
            arrHrt.add(new Hearts(tex4, posX, 420f, 70f, 50f));
            posX += 77f;
        }

        go = new Texture(Gdx.files.internal("go.jpg"));
        go.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        goo = new GameOver(go, 0, 0, 800, 480);

        }



    @Override
    public void render(float delta) {
        //Коэффициент для величин объектов
        deltaCff = delta;

        // Перед каждым рендером очищаем экран
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Обновление матрицы камеры
        camera.update();

        // Указываем отрисовщику матрицу, которую надо использовать
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        bcg.draw(batch);
        bck.draw(batch);
        for(Droplet drop : Droplet.arrDrop) {
            drop.draw(batch);
        }
        for(Hearts tester : arrHrt) {
            tester.draw(batch);
        }
        if (arrHrt.size == 0) {
            goo.draw(batch);
            rainMusic.stop();
        }
        batch.end();
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            Droplet.arrDrop.add(new Droplet(tex2, MathUtils.random(0, 800-64), 480f, 64f, 64f));
            lastDropTime = TimeUtils.nanoTime();
        }



        Iterator<Droplet> iter = Droplet.arrDrop.iterator();
        while (iter.hasNext()) {
            Droplet tmp = iter.next();
            tmp.bounds.setPosition(tmp.bounds.getX(), tmp.bounds.getY() -200f * Gdx.graphics.getDeltaTime());
            if (tmp.bounds.getY() + 64f < 0) {
                iter.remove();
                if (arrHrt.size > 0) {
                    arrHrt.removeIndex(arrHrt.size - 1);
                }
            }
            if(tmp.bounds.getX() < bck.bounds.getX()+ bck.object.getWidth() && tmp.bounds.getX() + tmp.object.getWidth() > bck.bounds.getX() && tmp.bounds.getY() < bck.bounds.getY() + bck.object.getHeight() && tmp.bounds.getY() + tmp.object.getHeight() > bck.bounds.getY()) {
                dropSound.play();
                iter.remove();
            }
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        rainMusic.dispose();
        dropSound.dispose();
        tex3.dispose();
        tex1.dispose();
        tex2.dispose();
        tex4.dispose();
    }
}
