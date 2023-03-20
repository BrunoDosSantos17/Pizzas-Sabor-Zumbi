package com.mygdx.jogo.recursos;

import java.util.Random;

/**
 *
 * @author informatica
 */
public class Aleatorio {

    private static Random m = new Random();
    
    public static int getDirecao() {        
        return m.nextInt(4);
    }
}
