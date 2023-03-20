package com.mygdx.jogo.recursos;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


/*
 * @author Bruno
 */
public class colisao {

    static boolean cX = false;
    static boolean cY = false;

    public static boolean ColisaoX(float velocidade, boolean moverDireita, TiledMapTileLayer cLayer, float tW, float tH, float y, float x, float altura, float largura) {

        if (moverDireita) {
            //top right
            cX = cLayer.getCell((int) ((x + velocidade + largura) / tW), (int) ((y + altura) / tH)).getTile().getProperties().containsKey("solid");
            //meio right
            if (!cX) {
                cX = cLayer.getCell((int) ((x + velocidade  + largura) / tW), (int) ((y + largura / 4) / tH)).getTile().getProperties().containsKey("solid");
            }
            //baixo right
            if (!cX) {
                cX = cLayer.getCell((int) ((x + velocidade  + largura) / tW), (int) ((y) / tW)).getTile().getProperties().containsKey("solid");
            }
        } else {
            cX = cLayer.getCell((int) ((x-velocidade) / tW), (int) ((y + altura) / tH)).getTile().getProperties().containsKey("solid");

            //meio left
            if (!cX) {
                cX = cLayer.getCell((int) ((x-velocidade) / tW), (int) ((y + altura) / tH)).getTile().getProperties().containsKey("solid");
            }
            //baixo left
            if (!cX) {
                cX = cLayer.getCell((int) ((x-velocidade) / tW), (int) (y / tH)).getTile().getProperties().containsKey("solid");
            }
        }

        return cX;
    }

    public static boolean ColisaoY(float velocidade, boolean moverAcima, TiledMapTileLayer cLayer, float tW, float tH, float y, float x, float altura, float largura) {
        if (moverAcima) {
            cY = cLayer.getCell((int) (x / tW), (int) (((y+velocidade) + altura) / tH)).getTile().getProperties().containsKey("solid");

            if (!cY) {
                cY = cLayer.getCell((int) ((x + largura / 5) / tW), (int) (((y+velocidade) + altura) / tH)).getTile().getProperties().containsKey("solid");
            }
            if (!cY) {
                cY = cLayer.getCell((int) ((x + largura) / tW), (int) (((y+velocidade) + altura) / tH)).getTile().getProperties().containsKey("solid");
            }
        } else {
            cY = cLayer.getCell((int) (x / tW), (int) ((y-velocidade) / tH)).getTile().getProperties().containsKey("solid");
            if (!cY) {
                cY = cLayer.getCell((int) ((x + largura / 3) / tW), (int) ((y-velocidade) / tH)).getTile().getProperties().containsKey("solid");
            }

            if (!cY) {
                cY = cLayer.getCell((int) ((x + largura) / tW), (int) ((y-velocidade) / tH)).getTile().getProperties().containsKey("solid");
            }
        }
        return cY;
    }

    public static boolean Colisao(float nX, float nY, float pX, float pY, float largura, float altura,float larguraP,float alturaP) {
        boolean cB = false;
        boolean a1 = nX < pX + larguraP;
        boolean a2 = nY < pY + alturaP;
        boolean a3 = (nX + largura > pX );
        boolean a4 = (nY + altura > pY  );
        if (a1 && a2 && a3 && a4) {
            cB = true;
        }

        return cB;
    }

}
