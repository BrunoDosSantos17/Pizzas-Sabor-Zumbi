package com.mygdx.jogo.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PizzaSaborZumbi;
import static com.mygdx.game.PizzaSaborZumbi.LARGURACAMERA;
import com.mygdx.modos.jogoNormal;

public class GameOver implements Screen {

    PizzaSaborZumbi game;
    Texture fundoMenuOp;
    SpriteBatch batch;
    Texture BotaoMenu;
    Texture BotaoMenuAtivo;
    Texture opcoes;
    Texture opcoesAtivo;
     Texture BotaoJogarInativo;
    Texture BotaoJogarAtivo;
    private static final int BOTAO_PLAY_Y = 260;
    private static final int BOTAO_OPCOES_Y = 110;
    private static final int ALTURA_BOTAO_GERAL = 200;
    private static final int LARGURA_BOTAO_GERAL = 300;

    public GameOver(final PizzaSaborZumbi game) {
        this.game = game;
        fundoMenuOp = new Texture("GameOver.png");
        batch = new SpriteBatch();
        BotaoMenuAtivo = new Texture("Placar_MenuAtivo.png");
        BotaoMenu = new Texture("Placar_Menu.png");
        opcoes = new Texture("Opcoes.png");
        opcoesAtivo = new Texture("OpcoesAtivo.png");
        BotaoJogarAtivo = new Texture("JogarAtivo.png");
        BotaoJogarInativo = new Texture("Jogar.png");

        final GameOver op = this;
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int botao) {

                int variavelQQ = PizzaSaborZumbi.LARGURACAMERA / 2 - PizzaSaborZumbi.ALTURACAMERA / 2;

                if (game.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_GERAL + 130 && game.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - 115 - game.cam.getInputInGameWorld().y < BOTAO_PLAY_Y + ALTURA_BOTAO_GERAL - 190 && PizzaSaborZumbi.ALTURACAMERA - 75 - game.cam.getInputInGameWorld().y > BOTAO_PLAY_Y) {
                    op.dispose();
                    game.setScreen(new jogoNormal(game));
                }

               if (game.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_GERAL + 130 && game.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - 115 - game.cam.getInputInGameWorld().y < BOTAO_PLAY_Y + ALTURA_BOTAO_GERAL - 275 && PizzaSaborZumbi.ALTURACAMERA + 5 - game.cam.getInputInGameWorld().y > BOTAO_PLAY_Y) {
                    op.dispose();
                    game.setScreen(new TelaMenu(game));
                }

                if (game.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_GERAL + 130 && game.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - 115 - game.cam.getInputInGameWorld().y < BOTAO_PLAY_Y + ALTURA_BOTAO_GERAL - 370 && PizzaSaborZumbi.ALTURACAMERA + 95 - game.cam.getInputInGameWorld().y > BOTAO_PLAY_Y) {
                    op.dispose();
                    game.setScreen(new Opcoes(game, 2));
                }


                return super.touchUp(screenX, screenY, pointer, botao);
            }

        });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float f) {
        if (Gdx.input.isKeyPressed(Input.Keys.N)) {
            game.setScreen(new jogoNormal(game));
        }
        batch.begin();

        batch.draw(fundoMenuOp, 0, 0, LARGURACAMERA - 750, fundoMenuOp.getHeight() * (PizzaSaborZumbi.LARGURACAMERA / fundoMenuOp.getWidth()));
        int x = PizzaSaborZumbi.LARGURACAMERA / 2 - PizzaSaborZumbi.ALTURACAMERA / 2;
        
       if (game.cam.getInputInGameWorld().x < x + LARGURA_BOTAO_GERAL + 130 && game.cam.getInputInGameWorld().x > x && PizzaSaborZumbi.ALTURACAMERA - 115 - game.cam.getInputInGameWorld().y < BOTAO_PLAY_Y + ALTURA_BOTAO_GERAL - 190 && PizzaSaborZumbi.ALTURACAMERA - 75 - game.cam.getInputInGameWorld().y > BOTAO_PLAY_Y) {
            batch.draw(BotaoJogarAtivo, x - 280, BOTAO_PLAY_Y + 150, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        } else {
            batch.draw(BotaoJogarInativo, x - 280, BOTAO_PLAY_Y + 150, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        }
        
        if (game.cam.getInputInGameWorld().x < x + LARGURA_BOTAO_GERAL + 130 && game.cam.getInputInGameWorld().x > x && PizzaSaborZumbi.ALTURACAMERA - 115 - game.cam.getInputInGameWorld().y < BOTAO_PLAY_Y + ALTURA_BOTAO_GERAL - 275 && PizzaSaborZumbi.ALTURACAMERA + 5 - game.cam.getInputInGameWorld().y > BOTAO_PLAY_Y) {
            batch.draw(BotaoMenuAtivo, x - 280, BOTAO_PLAY_Y + 25, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        } else {
            batch.draw(BotaoMenu, x - 280, BOTAO_PLAY_Y + 25, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        }

        if (game.cam.getInputInGameWorld().x < x + LARGURA_BOTAO_GERAL + 130 && game.cam.getInputInGameWorld().x > x && PizzaSaborZumbi.ALTURACAMERA - 115 - game.cam.getInputInGameWorld().y < BOTAO_PLAY_Y + ALTURA_BOTAO_GERAL - 360 && PizzaSaborZumbi.ALTURACAMERA + 95 - game.cam.getInputInGameWorld().y > BOTAO_PLAY_Y) {
            batch.draw(opcoesAtivo, x - 280, BOTAO_PLAY_Y - 100, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        } else {
            batch.draw(opcoes, x - 280, BOTAO_PLAY_Y - 100, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        }

        batch.end();

    }

    @Override
    public void resize(int largura, int altura) {

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
