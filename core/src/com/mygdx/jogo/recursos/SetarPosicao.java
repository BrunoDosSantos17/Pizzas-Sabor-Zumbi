package com.mygdx.jogo.recursos;


public class SetarPosicao{
    
     public static float PosicaoX  = 450;
     public static float PosicaoY = 100;
     public static float PosicaoRotacao = 0;
     public static float rotacao = 0;


    public  float getRotacao() {
        return rotacao;
    }

    public  void setRotacao(float rotacao) {
        SetarPosicao.rotacao = rotacao;
    }


    public float getPosicaoRotacao() {
        return PosicaoRotacao;
    }

    public void setPosicaoRotacao(float PosicaoRotacao) {
        SetarPosicao.PosicaoRotacao = PosicaoRotacao;
    }

    public float getPosicaoX() {      
        return PosicaoX ;
    }

    public void setPosicaoX(float PosicaoX) {
        SetarPosicao.PosicaoX = PosicaoX;
    }

    public float getPosicaoY() {
        return PosicaoY;
    }

    public void setPosicaoY(float PosicaoY) {
        SetarPosicao.PosicaoY = PosicaoY;
    }
    
}
