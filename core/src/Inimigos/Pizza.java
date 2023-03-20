package Inimigos;

import Jogador.Projetil;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.jogo.recursos.Aleatorio;
import com.mygdx.jogo.recursos.colisao;

/**
 *
 * @author informatica
 */
public class Pizza {

    int x;
    int y;
    int velocidade;
    int dano;
    int vida;

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    public int getPassos() {
        return passos;
    }

    public void setPassos(int passos) {
        this.passos = passos;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean isColidiu() {
        return colidiu;
    }

    public void setColidiu(boolean colidiu) {
        this.colidiu = colidiu;
    }
    int pontos;
    int direcao;
    int passos = 0;
    Texture texture;

    public Pizza(int x, int y, int velocidade, int dano, int vida, int valor, int direcao, Texture texture) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        this.dano = dano;
        this.vida = vida;
        this.pontos = valor;
        this.direcao = direcao;
        this.texture = texture;
    }

    private boolean colidiu;

    public void deslocar(TiledMapTileLayer cLayer, float tW, float tH) {
        if (passos == 35 ||colisao.ColisaoX(velocidade, direcao == 3, cLayer, tW, tH, y, x, texture.getHeight(), texture.getWidth()) 
                || colisao.ColisaoY(velocidade, direcao == 0, cLayer, tW, tH, y, x, texture.getHeight(), texture.getWidth())) {
            direcao = Aleatorio.getDirecao();
            passos = 0;
        } else {
            switch (direcao) {
                case 0:
                    y += velocidade;
                    break;
                case 1:
                    y -= velocidade;
                    break;
                case 2:
                    x -= velocidade;
                    break;
                case 3:
                    x += velocidade;
                    break;
            }
            passos++;
        }
    }
    
    public void renderizar(OrthogonalTiledMapRenderer renderer) {
        renderer.getBatch().draw(texture, x, y);
    }

    public int getDano() {
        return dano;
    }

    public int getVida() {
        return vida;
    }

    public int getPontos() {
        return pontos;
    }
    
    public boolean atingido(Projetil projetil){
        return colisao.Colisao(x, y, projetil.x, projetil.y, texture.getWidth(), texture.getHeight(), projetil.texture.getWidth(), projetil.texture.getHeight());
    }
    
    public boolean colisaoPersonagem(Sprite player){
        return colisao.Colisao(x, y, player.getX(), player.getY(), texture.getWidth(), texture.getHeight(), player.getWidth(), player.getHeight());
    }    
}
