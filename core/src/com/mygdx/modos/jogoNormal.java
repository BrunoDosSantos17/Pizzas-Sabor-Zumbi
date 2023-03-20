/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.modos;
//Autor:Bruno dos Santos, Gabriel Kresin e Werner Kupske
//Orientador: Adriano Pessini

import BancoDeDados.scoreDTO;
import Inimigos.Pizza;
import Inimigos.PizzaGorda;
import Inimigos.PizzaTank;
import Jogador.Projetil;
import Jogador.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.PizzaSaborZumbi;
import com.mygdx.jogo.Telas.Dados;
import com.mygdx.jogo.Telas.OpcoesJogo;
import com.mygdx.jogo.recursos.Aleatorio;
import com.mygdx.jogo.recursos.SetarPosicao;
import com.mygdx.jogo.recursos.desenhosAnimados;
import com.mygdx.jogo.recursos.colisao;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno dos Santos
 */
public class jogoNormal implements Screen {

    PizzaSaborZumbi game;
    desenhosAnimados personagem1;
    desenhosAnimados personagem2;
    desenhosAnimados personagem3;
    desenhosAnimados facada1;
    desenhosAnimados facada2;
    desenhosAnimados facada3;
    desenhosAnimados facada4;
    desenhosAnimados personagemDandoFacada;
    private desenhosAnimados personagem;
    private TiledMap map;
    private TiledMapTileLayer cLayer;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private BitmapFont currentFont;
    private Sprite spritePersonagem;
    Projetil projetil = new Projetil(50, 50);
    private final SetarPosicao setar = new SetarPosicao();
    private Aleatorio aleatorio = new Aleatorio();
    public Player p = new Player();
    private int di;
    float tempo2;
    float tempoFacada;
    private float tW, tH;
    private float oldX, oldY, oldRot;
    float intervaloMudancaSpritePizzaGorda = 0.1f;
    float somaTempoPizzaGorda = 0.0f;
    float tempo = 0.0f;
    private boolean ani = true;
    boolean morreu;
    boolean cX = false, cY = false;
    boolean directX, directY;//true +; false 
    boolean mudou = false;
    boolean atirou = false;
    boolean vencedor;
    boolean animaPizzaGordaExplindoQueLegal = true;
    boolean mudouFacada;
    Pizza inimigo;
    int numeroTrocaSpriteExplosaoGorda = 9;
    int contaFacada = 4;
    public static ArrayList<Pizza> inimigos = new ArrayList();
    ArrayList<PizzaGorda> explosoes = new ArrayList();

    //Sprite gordaSp;
    public jogoNormal(PizzaSaborZumbi game) {
        if (inimigos.isEmpty()) {
            inimigos.add(new Pizza(500, 500, 4, 5, 15, 20, aleatorio.getDirecao(), new Texture("Pizza-Normal.png")));
            inimigos.add(new Pizza(200, 300, 4, 5, 15, 20, aleatorio.getDirecao(), new Texture("Pizza-Normal.png")));
            inimigos.add(new PizzaTank(250, 350, 2, 10, 25, 20, aleatorio.getDirecao(), new Texture("Tank.png")));
            inimigos.add(new PizzaTank(550, 200, 2, 10, 25, 20, aleatorio.getDirecao(), new Texture("Tank.png")));
            inimigos.add(new PizzaGorda(350, 32, 2, 25, 35, 2, aleatorio.getDirecao(), new Texture("PizzaGordaExplodindo-1.png")));
            inimigos.add(new PizzaGorda(700, 32, 2, 25, 35, 2, aleatorio.getDirecao(), new Texture("PizzaGordaExplodindo-1.png")));
            inimigos.add(new Pizza(700, 32, 8, 5, 1, 1, aleatorio.getDirecao(), new Texture("Veloz.png")));
            inimigos.add(new Pizza(450, 450, 8, 5, 1, 1, aleatorio.getDirecao(), new Texture("Veloz.png")));
        }

        this.game = game;

        currentFont = new BitmapFont();
        facada1 = new desenhosAnimados("Personagem.png");
        facada2 = new desenhosAnimados("PersonagemFacada-2.png");
        facada3 = new desenhosAnimados("PersonagemFacada-3.png");
        facada4 = new desenhosAnimados("PersonagemFacada-4.png");
        facada1.setproximo(facada2);
        facada2.setproximo(facada3);
        facada3.setproximo(facada4);
        facada4.setproximo(facada1);
        personagem1 = new desenhosAnimados("Personagem.png");
        personagem2 = new desenhosAnimados("PersonagemAtirando.png");
        personagem3 = new desenhosAnimados("PersonagemAtirando2.png");
        personagem1.setproximo(personagem2);
        personagem2.setproximo(personagem3);
        personagem3.setproximo(personagem1);

        map = new TmxMapLoader().load("TiledIMG/mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        this.personagem = personagem1;
        this.personagemDandoFacada = facada1;
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int key) {
                if ((key == Keys.SPACE) && (atirou == false)) {
                    atirou = true;
                    projetil.xOriginal = spritePersonagem.getX();
                    projetil.yOriginal = spritePersonagem.getY();
                    inicializaDisparo();
                    mudarSprite();
                }
                if ((key == Keys.F) && (mudouFacada == false)) {
                    mudouFacada = true;
                    mudarSpriteFacada();
                }
                return true;
            }

        });
    }

    public void inicializaDisparo() {
        projetil.x = spritePersonagem.getX() + 10;
        projetil.y = spritePersonagem.getY() + 20;
        projetil.rotation = spritePersonagem.getRotation();
    }

    public void mudarSprite() {
        //troca para o personagem atirando
        mudou = true;
        tempo = 0;
        personagem = personagem.getProximo();
        spritePersonagem = new Sprite(personagem);
        spritePersonagem.translate(oldX, oldY);
        spritePersonagem.setRotation(di);
    }

    public void mudarSpriteFacada() {
        //troca para o personagem facada
        mudouFacada = true;
        tempoFacada = 0;
        spritePersonagem.setRotation(di);
        contaFacada--;
    }

    @Override
    public void show() {

        camera.update();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        spritePersonagem = new Sprite(personagem);
        spritePersonagem.setPosition(setar.getPosicaoX(), setar.getPosicaoY());
        spritePersonagem.setRotation(setar.getRotacao());
        renderer.setView(camera);
        cLayer = (TiledMapTileLayer) map.getLayers().get(0);
        tW = cLayer.getTileWidth();
        tH = cLayer.getTileHeight();
    }

    boolean removerPizza;
    static int contador;
    int cont = 2;
    private PizzaTank pizzaTank;
    private PizzaGorda pizzaGorda;

    @Override
    public void render(float delta) {
        tempoFacada += delta;
        // deslocamento dos inimigos...
        contador = 0;
        while (contador < inimigos.size()) {
            inimigo = inimigos.get(contador);
            removerPizza = false;
            inimigo.deslocar(cLayer, tW, tH);

            if (atirou && inimigo.atingido(projetil)) {
                if (inimigo.getVida() > 0) {
                    inimigo.setVida(inimigo.getVida() - projetil.dano);
                } else {
                    removerPizza = true;
                    p.setPontuacao(p.getPontuacao() + inimigo.getPontos());
                }
                atirou = false;

            } else if (inimigo.colisaoPersonagem(spritePersonagem)) {
                if (inimigo instanceof PizzaGorda) {
                    explosoes.add((PizzaGorda) inimigo);

                    inimigos.remove(contador);
                    p.setPontuacao(p.getPontuacao() + inimigo.getPontos());
                }
                tempo2 += delta;
                if (tempo2 > 0.7) {
                    p.setVida((int) (p.getVida() - inimigo.getDano()));
                    if (morreu) {
                        p.setVida(0);
                    }
                    tempo2 = 0;
                }
                if (mudouFacada) {
                    if (inimigo.getVida() > 0) {
                        inimigo.setVida(inimigo.getVida() - 15);
                    } else {
                        removerPizza = true;
                        p.setPontuacao(p.getPontuacao() + inimigo.getPontos());
                    }
                }
            }

            if (removerPizza) {
                inimigos.remove(contador);
            } else {
                if (inimigo instanceof PizzaTank) {
                    pizzaTank = (PizzaTank) inimigo;

                    // verificar se o disparo da pizza tank atingiu o jogador...
                    pizzaTank.deslocaDisparo(cLayer, tW, tH);
                    if (pizzaTank.colisaoDisparo(spritePersonagem)) {
                        p.setVida((int) (p.getVida() - pizzaTank.getProjetil().dano));
                        pizzaTank.inicializaDisparo();
                    }

                    // se o projetil da pizza tank colidiu com as paredes e, em caso afirmativo,
                    // reinicializar o disparo
                    pizzaTank.deslocaDisparo(cLayer, tW, tH);
                }
                contador++;
            }

            // colisão do personagem com pizzas...
            //
        }

        contador = 0;
        while (contador < explosoes.size()) {
            pizzaGorda = explosoes.get(contador);
            contador++;

        }

        // verificação de colisão do personagem com as pizzas
        // verificação de colisão do personagem com o disparo da pizza tank
        // geração dos disparos da pizza tank
        // remover pizzas "mortas" da cena (ArrayList)
        // renderização dos inimigos...
        tempo2 += delta;
        tempo += delta;
        oldX = spritePersonagem.getX();
        oldY = spritePersonagem.getY();
        oldRot = spritePersonagem.getRotation();
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            setar.setPosicaoX(oldX);
            setar.setPosicaoY(oldY);
            setar.setRotacao(oldRot);
            game.setScreen(new OpcoesJogo(game, this));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            spritePersonagem.translateY(p.getVeloc());
            if (spritePersonagem.getRotation() != 0) {
                spritePersonagem.setRotation(0);
                di = 0;
            }
            directY = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            spritePersonagem.translateY(-p.getVeloc());
            if (spritePersonagem.getRotation() != 180) {
                spritePersonagem.setRotation(180);
                di = 180;
            }
            directY = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            spritePersonagem.translateX(p.getVeloc());
            if (spritePersonagem.getRotation() != 270) {
                spritePersonagem.setRotation(270);
                di = 270;
            }
            directX = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            spritePersonagem.translateX(-p.getVeloc());
            if (spritePersonagem.getRotation() != 90) {
                spritePersonagem.setRotation(90);
                di = 90;
            }
            directX = false;
        }
        if (mudou && tempo > 0.2) {
            mudou = false;
            personagem = personagem.getProximo();
            spritePersonagem = new Sprite(personagem);
            spritePersonagem.setRotation(di);
        }
        if (mudouFacada && tempoFacada > 0.1) {
            tempoFacada = 0;
            personagemDandoFacada = personagemDandoFacada.getProximo();
            spritePersonagem = new Sprite(personagemDandoFacada);
            spritePersonagem.setRotation(di);

            if (contaFacada == 0) {
                mudouFacada = false;
                contaFacada = 4;
            } else {
                contaFacada--;
            }
        }

        if (atirou) {
            // deslocar o tiro de acordo com a direção e a velocidade do deslocamento
            // do disparo....
            if (projetil.rotation == 0) {
                projetil.y += projetil.VELOCIDADE;
            } else if (projetil.rotation == 90) {
                projetil.x -= projetil.VELOCIDADE;
            } else if (projetil.rotation == 180) {
                projetil.y -= projetil.VELOCIDADE;
            } else {
                projetil.x += projetil.VELOCIDADE;
            }

            //Aqui coloca quantos pixel a bala do jogador pode percorrer
            if ((projetil.x - projetil.xOriginal) > 300 || (projetil.x - projetil.xOriginal) < -300 || projetil.x < 30 || projetil.x > 1240) {
                atirou = false;
            }
            if ((projetil.y - projetil.yOriginal) > 300 || (projetil.y - projetil.yOriginal) < -300 || projetil.y > 690 || projetil.y < 30) {
                atirou = false;
            }
            //Colisão com a parede ou objetos da bala do jogador
            //ColisaoY(tank.veloc, retTank == 0, cLayer, tW, tH, tank.y, tank.x, tank.PTank.getHeight(), tank.PTank.getWidth());

            if (colisao.ColisaoX(projetil.VELOCIDADE, directX, cLayer, tW, tH, projetil.y, projetil.x, projetil.texture.getHeight(), projetil.texture.getWidth())
                    && colisao.ColisaoY(projetil.VELOCIDADE, directY, cLayer, tW, tH, projetil.y, projetil.x, projetil.texture.getHeight(), projetil.texture.getWidth())) {
                atirou = false;
            }
        }

        if (p.getVida() <= 0) {

            scoreDTO sdto = new scoreDTO();
            vencedor = false;
            try {
                sdto.setModoDeJogo("CAMPANHA");
            } catch (Exception ex) {
                Logger.getLogger(jogoNormal.class.getName()).log(Level.SEVERE, null, ex);
            }
            game.setScreen(new Dados(game, "CAMPANHA", p.getPontuacao(), vencedor));

            p.setVida(100);
            p.setPontuacao(0);

        }

        // verificação de colisão....
        // se colidir com parede
        // se colidir com iminigo
        // se acabou a gasolina
        // atirou = false;
        //a letra 'M' pause
        //colisão
        //top left
        cX = colisao.ColisaoX(1.5f, directX, cLayer, tW, tH, spritePersonagem.getY(), spritePersonagem.getX(), spritePersonagem.getHeight(), spritePersonagem.getWidth());
        cY = colisao.ColisaoY(1.5f, directY, cLayer, tW, tH, spritePersonagem.getY(), spritePersonagem.getX(), spritePersonagem.getHeight(), spritePersonagem.getWidth());

        //end colisão
        if (cX) {
            cX = false;
            spritePersonagem.setPosition(oldX, oldY);
        }
        if (cY) {
            cY = false;
            spritePersonagem.setY(oldY);
        }

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();

        renderer.getBatch().begin();

        // renderização das pizzas que estão vivas...
        for (Pizza inimigo : inimigos) {
            inimigo.renderizar(renderer);
        }
        boolean statuGorda;
        // renderização das pizzas gordas que foram destruídas...
        for (PizzaGorda pizzaG : explosoes) {
            statuGorda = pizzaG.renderizaExplosao(renderer, delta);
            if (statuGorda) {
                explosoes.remove(p);
            }
        }

        currentFont.draw(renderer.getBatch(), "Vida: " + String.valueOf(p.getVida() + "%"), 1050, 700);
        currentFont.draw(renderer.getBatch(), "Pontuação: " + String.valueOf(p.getPontuacao()), 1050, 680);
        spritePersonagem.draw(renderer.getBatch());

        if (atirou) {
            renderer.getBatch().draw(projetil.texture, projetil.x, projetil.y);
        }

        renderer.getBatch().end();

        if (inimigos.size() == 0) {
            vencedor = true;
            scoreDTO sdto = new scoreDTO();
            try {
                sdto.setModoDeJogo("CAMPANHA");
            } catch (Exception ex) {
                Logger.getLogger(jogoNormal.class.getName()).log(Level.SEVERE, null, ex);
            }
            game.setScreen(new Dados(game, "CAMPANHA", p.getPontuacao(), vencedor));

        }

        camera.zoom = 0.97f;
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
        currentFont.dispose();
    }
}
//Autor:Bruno dos Santos, Gabriel Kresin e Werner Kupske
//Orientador: Adriano Pessini
