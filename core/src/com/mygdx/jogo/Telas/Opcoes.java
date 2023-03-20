package com.mygdx.jogo.Telas;
//Autor:Bruno dos Santos, Gabriel Kresin e Werner Kupske
//Orientador: Adriano Pessini

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.PizzaSaborZumbi;

public class Opcoes implements Screen {

    // Define as constantes de tamanho dos botões que serão utilizados
    private static final int LARGURA_BOTAO_VOLUME = 200;
    private static final int ALTURA_BOTAO_VOLUME = 100;
    private static final int Y_BOTAO_VOLUME = 300;
    private static final int LARGURA_BOTAO_VOLUME_MAIS = 150;
    private static final int ALTURA_BOTAO_VOLUME_MAIS = 100;
    private static final int Y_BOTAO_VOLUME_MAIS = 300;
    private static final int LARGURA_BOTAO_VOLTA = 150;
    private static final int ALTURA_BOTAO_VOLTA = 50;
    private static final int Y_BOTAO_VOLTA = 440;
    // Define o volume da música de fundo
    private static float VolumeDaMusica = 0.5f;
    // Define o valor máximo do volume da música
    private static int VolumeControlador = 5;
    // quando for false música executando e quando for true a música para 
    // cria um atributo da classe opções
    final PizzaSaborZumbi opcoes;
    // cria variaveis para amarzenar as texturas / imagens
    Texture botaoVolumeLigado;
    Texture botaoVolumeDesligado;
    Texture botaoVoltar;
    Texture botaoAumentarVolume;
    Texture botaoAbaixarVolume;
    Texture Manual;
    int local = 0;
    public static boolean controleMusica = false;

    Opcoes(final PizzaSaborZumbi opcoes, final int local) {

        this.opcoes = opcoes;
        this.local = local;
        // Cria e carrega os texturas dos botões do jogo
        botaoVolumeLigado = new Texture("BotaoVolume.png");
        botaoVolumeDesligado = new Texture("BotaoVolumeDesligado.png");
        botaoVoltar = new Texture("BotaoVolta.png");
        botaoAumentarVolume = new Texture("BotaoVolMais.png");
        botaoAbaixarVolume = new Texture("BotaoVolMenos.png");
        Manual = new Texture("Manual.png");

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int botao) {
                // Define a posição em que o botão ficará na tela 
                int variavelQQ = PizzaSaborZumbi.LARGURACAMERA / 2 - LARGURA_BOTAO_VOLUME / 2 - 32;
                int variavelQQ2 = PizzaSaborZumbi.LARGURACAMERA / 2 - LARGURA_BOTAO_VOLUME / 2 + 195;
                int variavelQQ3 = PizzaSaborZumbi.LARGURACAMERA / 2 - LARGURA_BOTAO_VOLUME / 2 - 195;
                // localiza a posção x e y do botão para saber aonde ele se encontra 
                if (opcoes.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_VOLTA && opcoes.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - opcoes.cam.getInputInGameWorld().y < Y_BOTAO_VOLTA + ALTURA_BOTAO_VOLTA && PizzaSaborZumbi.ALTURACAMERA - opcoes.cam.getInputInGameWorld().y > Y_BOTAO_VOLTA) {
                    // Renderiza a tela com as opções
                    if (local == 0) {
                        opcoes.setScreen(new TelaMenu(opcoes));

                    }
                    if (local == 1) {
                        opcoes.setScreen(new OpcoesJogo(opcoes, null));
                    }
                    if (local == 2) {
                        opcoes.setScreen(new GameOver(opcoes));
                    }

                }
                if (opcoes.cam.getInputInGameWorld().x < variavelQQ + LARGURA_BOTAO_VOLUME && opcoes.cam.getInputInGameWorld().x > variavelQQ && PizzaSaborZumbi.ALTURACAMERA - opcoes.cam.getInputInGameWorld().y < Y_BOTAO_VOLUME + ALTURA_BOTAO_VOLUME && PizzaSaborZumbi.ALTURACAMERA - opcoes.cam.getInputInGameWorld().y > Y_BOTAO_VOLUME) {
                    // Verifica o volume da música e executa a música no volume atual
                    if (PizzaSaborZumbi.executando) {
                        PizzaSaborZumbi.musicaFundo.pause();
                        PizzaSaborZumbi.executando = false;
                    } else {
                        PizzaSaborZumbi.musicaFundo.play();
                        PizzaSaborZumbi.executando = true;
                    }

                }
                // Aumenta o volume da música
                if (opcoes.cam.getInputInGameWorld().x < variavelQQ2 + LARGURA_BOTAO_VOLUME_MAIS && opcoes.cam.getInputInGameWorld().x > variavelQQ2 && PizzaSaborZumbi.ALTURACAMERA - opcoes.cam.getInputInGameWorld().y < Y_BOTAO_VOLUME_MAIS + ALTURA_BOTAO_VOLUME_MAIS && PizzaSaborZumbi.ALTURACAMERA - opcoes.cam.getInputInGameWorld().y > Y_BOTAO_VOLUME_MAIS && VolumeDaMusica <= 1) {
                    if (PizzaSaborZumbi.executando) {
                        VolumeDaMusica = VolumeDaMusica + 0.1f;
                        PizzaSaborZumbi.musicaFundo.setVolume(VolumeDaMusica);
                        System.out.println(VolumeDaMusica);
                        VolumeControlador ++;
                    }

                }
                // Diminui o volume da música
                if (opcoes.cam.getInputInGameWorld().x < variavelQQ3 + LARGURA_BOTAO_VOLUME_MAIS && opcoes.cam.getInputInGameWorld().x > variavelQQ3 && PizzaSaborZumbi.ALTURACAMERA - opcoes.cam.getInputInGameWorld().y < Y_BOTAO_VOLUME_MAIS + ALTURA_BOTAO_VOLUME_MAIS && PizzaSaborZumbi.ALTURACAMERA - opcoes.cam.getInputInGameWorld().y > Y_BOTAO_VOLUME_MAIS) {
                    if (PizzaSaborZumbi.executando && VolumeControlador != 0) {
                        VolumeDaMusica = VolumeDaMusica - 0.1f;
                        PizzaSaborZumbi.musicaFundo.setVolume(VolumeDaMusica);
                        System.out.println(VolumeDaMusica);
                        VolumeControlador --;
                        
                    }

                }
                // Retorna  a posição do ponteiro 
                return super.touchUp(screenX, screenY, pointer, botao);
            }

        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float f) {
        // determina a cor de fundo 
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //diz que é o começo dos desenhos
        opcoes.batch.begin();

        //começa a desenhar os botões conforme 
        int x = PizzaSaborZumbi.LARGURACAMERA / 2 - PizzaSaborZumbi.ALTURACAMERA / 2 + 125;
        opcoes.batch.draw(botaoVoltar, x, Y_BOTAO_VOLTA, LARGURA_BOTAO_VOLTA, ALTURA_BOTAO_VOLTA);
        if (!PizzaSaborZumbi.executando || VolumeControlador == 0) {
            opcoes.batch.draw(botaoVolumeDesligado, x, Y_BOTAO_VOLUME, LARGURA_BOTAO_VOLUME, ALTURA_BOTAO_VOLUME);
        } else {
            opcoes.batch.draw(botaoVolumeLigado, x, Y_BOTAO_VOLUME, LARGURA_BOTAO_VOLUME, ALTURA_BOTAO_VOLUME);
        }

        opcoes.batch.draw(botaoAumentarVolume, x + 200, Y_BOTAO_VOLUME_MAIS, LARGURA_BOTAO_VOLUME_MAIS, ALTURA_BOTAO_VOLUME_MAIS);
        opcoes.batch.draw(botaoAbaixarVolume, x - 150, Y_BOTAO_VOLUME_MAIS, LARGURA_BOTAO_VOLUME_MAIS, ALTURA_BOTAO_VOLUME_MAIS);
        
        //finaliza a sessão de desenhar
        opcoes.batch.end();

    }

    @Override
    public void resize(int i, int i1) {

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
        //limpa os clicks feitos na tela
        Gdx.input.setInputProcessor(null);
        opcoes.batch.dispose();
    }

}
