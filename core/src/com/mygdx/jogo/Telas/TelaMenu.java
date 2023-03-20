package com.mygdx.jogo.Telas;
//Autor:Bruno dos Santos, Gabriel Kresin e Werner Kupske
//Orientador: Adriano Pessini

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.PizzaSaborZumbi;
import static com.mygdx.game.PizzaSaborZumbi.LARGURACAMERA;
import com.mygdx.jogo.recursos.desenhosAnimados;
import com.mygdx.modos.jogoNormal;

public class TelaMenu implements Screen {

    Texture fundoTela;
    private desenhosAnimados m;
    private int trocas = 0;

    // Define as constantes de tamanho dos botões
    private static final int ALTURA_BOTAO_GERAL = 100;
    private static final int LARGURA_BOTAO_GERAL = 300;
    private static final int BOTAO_PLAY_Y = 260;
    private static final int BOTAO_HISTORIA_Y = 210;
    private static final int BOTAO_OPCOES_Y = 110;
    private static final int BOTAO_CONFIG_Y = 160;
    private static final int BOTAO_SAIR_Y = 60;

    final PizzaSaborZumbi jogo;
    // Importa os sprites dos botões
    Texture fundoMenu;
    Texture BotaoJogarInativo;
    Texture BotaoJogarAtivo;
    Texture historia;
    Texture historiaAtiva;
    Texture opcoes;
    Texture opcoesAtivo;
    Texture Controles;
    Texture ControlesAtivos;
    Texture sair;
    Texture sairAtivo;

    public TelaMenu(final PizzaSaborZumbi jogo) {
        this.jogo = jogo;

        fundoMenu = new Texture("FundoMenuPNG.png");
        BotaoJogarAtivo = new Texture("JogarAtivo.png");
        BotaoJogarInativo = new Texture("Jogar.png");
        historia = new Texture("PlacarHist.png");
        historiaAtiva = new Texture("PlacarHistAtivo.png");
        opcoes = new Texture("Opcoes.png");
        opcoesAtivo = new Texture("OpcoesAtivo.png");
        Controles = new Texture("PlacarControl.png");
        ControlesAtivos = new Texture("PlacarControlAtivo.png");
        sair = new Texture("PlacarSair.png");
        sairAtivo = new Texture("PlacarSairAtivo.png");

        // Esta função irá criar um novo objeto música e irá puxar a música com o getFile da pasta onde está localizada
        // que irá ser reproduzido de fundo
        desenhosAnimados d1 = new desenhosAnimados("FundoMenu1.png");
        desenhosAnimados d2 = new desenhosAnimados("FundoMenu2.png");
        desenhosAnimados d3 = new desenhosAnimados("FundoMenu3.png");
        d1.setproximo(d2);
        d2.setproximo(d3);
        d3.setproximo(d1);
        this.m = d1;

        final TelaMenu telaPrincipal = this;
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int botao) {

                int variavelQQ = PizzaSaborZumbi.LARGURACAMERA / 2 - LARGURA_BOTAO_GERAL / 2;
                if (jogo.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_PLAY_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_PLAY_Y) {
                    telaPrincipal.dispose();
                    jogo.setScreen(new jogoNormal(jogo));
                }

                if (jogo.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_HISTORIA_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_HISTORIA_Y) {
                    telaPrincipal.dispose();
                    jogo.setScreen(new Historia(jogo));
                }
                 if (jogo.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_CONFIG_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_CONFIG_Y) {
                    telaPrincipal.dispose();

                    jogo.setScreen(new Controles(jogo));
                }
                if (jogo.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_OPCOES_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_OPCOES_Y) {
                    telaPrincipal.dispose();
                    jogo.setScreen(new Opcoes(jogo,0));
                }
                
                if (jogo.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_SAIR_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_SAIR_Y) {
                    telaPrincipal.dispose();
                    System.exit(0);
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
        //desenha a tela de fundo
        // posiciona na próxima animação da lista encadeada...
        trocas++;
        if (trocas == 25) {
            m = m.getProximo();
            trocas = 0;
        }

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        jogo.batch.begin();
        jogo.batch.draw(m, 0, 0, LARGURACAMERA, m.getHeight() * (PizzaSaborZumbi.LARGURACAMERA / m.getWidth()) - 200);

        int x = PizzaSaborZumbi.LARGURACAMERA / 2 - PizzaSaborZumbi.ALTURACAMERA / 2 + 125;

        if (jogo.cam.getInputInGameWorld().x < x + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > x && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_PLAY_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_PLAY_Y) {
            jogo.batch.draw(BotaoJogarAtivo, x, BOTAO_PLAY_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        } else {
            jogo.batch.draw(BotaoJogarInativo, x, BOTAO_PLAY_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        }

        if (jogo.cam.getInputInGameWorld().x < x + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > x && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_HISTORIA_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_HISTORIA_Y) {
            jogo.batch.draw(historiaAtiva, x, BOTAO_HISTORIA_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);

        } else {
            jogo.batch.draw(historia, x, BOTAO_HISTORIA_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        }

        if (jogo.cam.getInputInGameWorld().x < x + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > x && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_CONFIG_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_CONFIG_Y) {
            jogo.batch.draw(ControlesAtivos, x, BOTAO_CONFIG_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        } else {
            jogo.batch.draw(Controles, x, BOTAO_CONFIG_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        }

        if (jogo.cam.getInputInGameWorld().x < x + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > x && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_OPCOES_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_OPCOES_Y) {
            jogo.batch.draw(opcoesAtivo, x, BOTAO_OPCOES_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        } else {
            jogo.batch.draw(opcoes, x, BOTAO_OPCOES_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        }
        if (jogo.cam.getInputInGameWorld().x < x + LARGURA_BOTAO_GERAL && jogo.cam.getInputInGameWorld().x > x && PizzaSaborZumbi.ALTURACAMERA - 50 - jogo.cam.getInputInGameWorld().y < BOTAO_SAIR_Y + ALTURA_BOTAO_GERAL - 65 && PizzaSaborZumbi.ALTURACAMERA - 40 - jogo.cam.getInputInGameWorld().y > BOTAO_SAIR_Y) {
            jogo.batch.draw(sairAtivo, x, BOTAO_SAIR_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        } else {
            jogo.batch.draw(sair, x, BOTAO_SAIR_Y, LARGURA_BOTAO_GERAL, ALTURA_BOTAO_GERAL);
        }

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
        m.dispose();
        Gdx.input.setInputProcessor(null);
    }
}
