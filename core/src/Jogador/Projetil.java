package Jogador;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Projetil {

    public static final int VELOCIDADE = 6;
    public static final int DEFAULT_Y = 40;
    public static final int LARGURA = 3;
    public static final int ALTURA = 12;
    public  Sprite texture;
    public float x;
    public float y;
    public float xOriginal = 0;
    public float yOriginal = 0;
    public boolean remove = false;
    public int dano = 5;
    public float rotation;

    public Projetil(float x, float y) {
        this.x = x;
        this.y = y;
        texture = new Sprite(new Texture("projetil.png"));
        
    }

}
