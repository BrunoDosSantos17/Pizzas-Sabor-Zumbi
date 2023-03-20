/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inimigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.jogo.recursos.desenhosAnimados;

/**
 *
 * @author informatica
 */
public class PizzaGorda extends Pizza {

    desenhosAnimados PizzaGorda1 = new desenhosAnimados("PizzaGordaExplodindo-1.png");
    desenhosAnimados PizzaGorda2 = new desenhosAnimados("PizzaGordaExplodindo-2.png");
    desenhosAnimados PizzaGorda3 = new desenhosAnimados("PizzaGordaExplodindo-3.png");
    desenhosAnimados PizzaGorda4 = new desenhosAnimados("PizzaGordaExplodindo-4.png");
    desenhosAnimados PizzaGorda5 = new desenhosAnimados("PizzaGordaExplodindo-5.png");
    desenhosAnimados PizzaGorda6 = new desenhosAnimados("PizzaGordaExplodindo-6.png");
    desenhosAnimados PizzaGorda7  = new desenhosAnimados("PizzaGordaExplodindo-7.png");
    desenhosAnimados PizzaGorda8 = new desenhosAnimados("PizzaGordaExplodindo-8.png");;
    desenhosAnimados  PizzaGorda9  = new desenhosAnimados("PizzaGordaExplodindo-9.png");
    int contador;
    float tempo;
    desenhosAnimados kabum;

    public PizzaGorda(int x, int y, int velocidade, int dano, int vida, int valor, int direcao, Texture texture) {
        super(x, y, velocidade, dano, vida, valor, direcao, texture);
        
        PizzaGorda1.setproximo(PizzaGorda2);
        PizzaGorda2.setproximo(PizzaGorda3);
        PizzaGorda3.setproximo(PizzaGorda4);
        PizzaGorda4.setproximo(PizzaGorda5);
        PizzaGorda5.setproximo(PizzaGorda6);
        PizzaGorda6.setproximo(PizzaGorda7);
        PizzaGorda7.setproximo(PizzaGorda8);
        PizzaGorda8.setproximo(PizzaGorda9);
        this.kabum = PizzaGorda1;
        // carregar todas as texturas e inicializar o contador de tempo para
        // animação das explosões.
    }

    @Override
    public void renderizar(OrthogonalTiledMapRenderer renderer) {
        super.renderizar(renderer);

    }

    public boolean renderizaExplosao(OrthogonalTiledMapRenderer renderer, float Tempo) {
        tempo += Tempo;
        if (contador != 8) {
            renderer.getBatch().draw(kabum, x, y);
            kabum = kabum.getProximo();
            contador++;
            return false;
        }
        return true;
    }
}
