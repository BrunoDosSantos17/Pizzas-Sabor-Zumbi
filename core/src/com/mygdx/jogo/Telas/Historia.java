package com.mygdx.jogo.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PizzaSaborZumbi;

/**
 *
 * @author bruno
 */
public class Historia implements Screen {

    PizzaSaborZumbi jogo;
    private BitmapFont letrasHistoria;
    private BitmapFont Historia;
    SpriteBatch sprite;
    Texture botaoVoltar;
    private static final int LARGURA_BOTAO_VOLTA = 150;
    private static final int ALTURA_BOTAO_VOLTA = 50;
    private static final int Y_BOTAO_VOLTA = 440;

    Historia(final PizzaSaborZumbi jogo) {
        this.jogo = jogo;
        botaoVoltar = new Texture("BotaoVolta.png");
        letrasHistoria = new BitmapFont(Gdx.files.internal("letrasHistoria.fnt"), false);
        Historia = new BitmapFont(Gdx.files.internal("Historia.fnt"), false);
        letrasHistoria.setColor(Color.GOLD);
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int botao) {
                int variavelQQ = PizzaSaborZumbi.LARGURACAMERA / 2 - LARGURA_BOTAO_VOLTA / 2 - 32;
                if (jogo.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_VOLTA && jogo.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - jogo.cam.getInputInGameWorld().y < Y_BOTAO_VOLTA + ALTURA_BOTAO_VOLTA && PizzaSaborZumbi.ALTURACAMERA - jogo.cam.getInputInGameWorld().y > Y_BOTAO_VOLTA) {
                    jogo.setScreen(new TelaMenu(jogo));
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
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        jogo.batch.begin();
        int x = PizzaSaborZumbi.LARGURACAMERA / 2 - PizzaSaborZumbi.ALTURACAMERA / 2 + 125;
        jogo.batch.draw(botaoVoltar, x, Y_BOTAO_VOLTA, LARGURA_BOTAO_VOLTA, ALTURA_BOTAO_VOLTA);
        letrasHistoria.draw(jogo.batch, " Historia ", 900, 420);
        Historia.draw(jogo.batch, "A história do jogo gira em torno do personagem \n"
                + "Jack Hot Knife, conhecido também como JHK, que é um renomado cientista \n"
                + "cujas pesquisas estão sempre relacionadas à alimentos, mais especificamente \n"
                + "focada em um único alimento, a PIZZA. Num dia, JHK estava realizando\n"
                + "uma de suas pesquisas quando de repente,   de maneira acidental,\n"
                + "ele derruba numa pizza um recipiente que continha uma solução perigosa, \n"
                + "e em seguida, uma explosão ocorre.\n"
                + "Nesta explosão as pizzas lá obtiveram inteligência e uma fome insaciável por carne humana. \n"
                + "Agora JHK tenta sobreviver ao caos instaurado por ele mesmo.", 500, 380);
        jogo.batch.end();
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
    }

}
