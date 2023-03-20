/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inimigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class ProjetilTank {

    public final int VELOCIDADE = 5;
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
    public int direcaoDisparo = 0;

    public ProjetilTank(float x, float y) {
        this.x = x;
        this.y = y;
        texture = new Sprite(new Texture("Brocolis.png"));        
    }

}
