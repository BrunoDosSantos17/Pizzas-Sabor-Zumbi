/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.PizzaSaborZumbi;

/**
 *
 * @author bruno
 */
public class Controles implements Screen {

    private static final int LARGURA_BOTAO_VOLTA = 150;
    private static final int ALTURA_BOTAO_VOLTA = 50;
    private static final int Y_BOTAO_VOLTA = 440;
    private static final int LARGURA_BOTAO_VOLUME = 200;
    final PizzaSaborZumbi control;
    int local = 0;
    Texture Manual;
    Texture botaoVoltar;

    Controles(final PizzaSaborZumbi control) {
        this.control = control;
        this.local = local;

        botaoVoltar = new Texture("BotaoVolta.png");
        Manual = new Texture("Manual.png");

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int botao) {
                int variavelQQ = PizzaSaborZumbi.LARGURACAMERA / 2 - LARGURA_BOTAO_VOLUME / 2 - 32;
                int variavelQQ2 = PizzaSaborZumbi.LARGURACAMERA / 2 - LARGURA_BOTAO_VOLUME / 2 + 195;
                int variavelQQ3 = PizzaSaborZumbi.LARGURACAMERA / 2 - LARGURA_BOTAO_VOLUME / 2 - 195;

                if (control.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_VOLTA && control.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - control.cam.getInputInGameWorld().y < Y_BOTAO_VOLTA + ALTURA_BOTAO_VOLTA && PizzaSaborZumbi.ALTURACAMERA - control.cam.getInputInGameWorld().y > Y_BOTAO_VOLTA) {
                    // Renderiza a tela com as opções
                    if (local == 0) {
                        control.setScreen(new TelaMenu(control));
                    }
                    if (local == 1) {
                        control.setScreen(new OpcoesJogo(control, null));
                    }
                    if (local == 2) {
                        control.setScreen(new GameOver(control));
                    }
                }

                return super.touchUp(screenX, screenY, pointer, botao);
            }
        });

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        control.batch.begin();
        int x = PizzaSaborZumbi.LARGURACAMERA / 2 - PizzaSaborZumbi.ALTURACAMERA / 2 + 125;
        control.batch.draw(botaoVoltar, x, Y_BOTAO_VOLTA, LARGURA_BOTAO_VOLTA, ALTURA_BOTAO_VOLTA);

        control.batch.draw(Manual, 550, 150, PizzaSaborZumbi.LARGURACAMERA - 1000, (Manual.getHeight() / 2 - 10) * (PizzaSaborZumbi.LARGURACAMERA / Manual.getWidth() / 2));
        control.batch.end();
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
        Gdx.input.setInputProcessor(null);
        control.batch.dispose();
    }

}
