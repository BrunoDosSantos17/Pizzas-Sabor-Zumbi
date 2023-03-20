package com.mygdx.jogo.recursos;
//Autor:Bruno dos Santos, Gabriel Kresin e Werner Kupske
//Orientador: Adriano Pessini

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Camera {

    private OrthographicCamera came;
    private StretchViewport janela;

    public Camera(int largura, int altura) {
        came = new OrthographicCamera();
        janela = new StretchViewport(largura, altura , came);
        janela.apply();
        came.position.set(largura / 2, altura / 2, 0);
        came.update();
    }
    public Matrix4 combined(){
        return came.combined;
    }
    public void upadate(int largura, int altura){
        janela.update(largura, altura);
    }
    public Vector2 getInputInGameWorld(){
        Vector3 entradaTela = new Vector3(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY(),0);
        Vector3 unproject = came.unproject(entradaTela);
        return new Vector2(unproject.x, unproject.y);
    }
    
}
