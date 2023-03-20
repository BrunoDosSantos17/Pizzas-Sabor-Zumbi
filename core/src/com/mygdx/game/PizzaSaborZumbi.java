package com.mygdx.game;
//Autores:Bruno dos Santos, Gabriel Kresin e Werner Kupske
//Orientador: Adriano Pessini

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.jogo.Telas.TelaMenu;
import com.mygdx.jogo.recursos.Camera;

public class PizzaSaborZumbi extends Game {

    public static final int LARGURATELA = 1280;
    public static final int ALTURATELA = 736;
    public static final int LARGURACAMERA = 2000;
    public static final int ALTURACAMERA = 500;
    public static boolean executando = true;
    public Camera cam;
    public SpriteBatch batch;
    public static Music musicaFundo;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Define o zoom da câmera
        cam = new Camera(LARGURACAMERA, ALTURACAMERA);
        // cria a animação do fundo da tela        
        //chama a tela inicial
        musicaFundo = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/musica.mp3", Files.FileType.Local));
        musicaFundo.setLooping(true);
       // musicaFundo.play();
        
        this.setScreen(new TelaMenu(this));
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(cam.combined());
        super.render();
    }

    @Override
    public void resize(int largura, int altura) {
        cam.upadate(largura, altura);
        super.resize(largura, altura);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
