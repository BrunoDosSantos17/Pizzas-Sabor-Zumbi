package Inimigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.jogo.recursos.colisao;

/**
 *
 * @author informatica
 */
public class PizzaTank extends Pizza {

    int projTankX;
    int projTankY;
    public Sprite projImg;
    int projDano;
    private ProjetilTank projetilTank;

    public PizzaTank(int x, int y, int velocidade, int dano, int vida, int valor, int direcao, Texture texture) {
        super(x, y, velocidade, dano, vida, valor, direcao, texture);
        projetilTank = new ProjetilTank(x, y);
        projImg = new Sprite(new Texture("Brocolis.png"));
        inicializaDisparo();
    }

    @Override
    public void deslocar(TiledMapTileLayer cLayer, float tW, float tH) {
        super.deslocar(cLayer, tW, tH);
    }

    public void inicializaDisparo() {
        passos = 0;
        projetilTank.direcaoDisparo = direcao;
        projetilTank.x = this.x + 10;
        projetilTank.y = this.y + 20;
        projetilTank.xOriginal = projetilTank.x + 10;
        projetilTank.yOriginal = projetilTank.y + 20;
    }

    public boolean colisaoDisparo(Sprite player) {
        return colisao.Colisao(projetilTank.x, projetilTank.y, player.getX(), player.getY(),
                projetilTank.texture.getWidth(), projetilTank.texture.getHeight(), player.getWidth(), player.getHeight());
    }

    public void deslocaDisparo(TiledMapTileLayer cLayer, float tW, float tH) {
        if (passos == 100 || 
                colisao.ColisaoX(projetilTank.VELOCIDADE, projetilTank.direcaoDisparo == 3, cLayer, tW, tH, projetilTank.y, projetilTank.x, projetilTank.texture.getHeight(), projetilTank.texture.getWidth()) || 
                colisao.ColisaoY(projetilTank.VELOCIDADE, projetilTank.direcaoDisparo == 0, cLayer, tW, tH, projetilTank.y, projetilTank.x, projetilTank.texture.getHeight(), projetilTank.texture.getWidth())) {
            inicializaDisparo();
        } else {
            switch (projetilTank.direcaoDisparo) {
                case 0:
                    projetilTank.y += projetilTank.VELOCIDADE;
                    break;
                case 1:
                    projetilTank.y -= projetilTank.VELOCIDADE;
                    break;
                case 2:
                    projetilTank.x -= projetilTank.VELOCIDADE;
                    break;
                case 3:
                    projetilTank.x += projetilTank.VELOCIDADE;
                    break;
            }
            passos++;
        }
    }

    public ProjetilTank getProjetil() {
        return projetilTank;
    }

    @Override
    public void renderizar(OrthogonalTiledMapRenderer renderer) {
        super.renderizar(renderer);
        renderer.getBatch().draw(projetilTank.texture, projetilTank.x, projetilTank.y);
    }

}
