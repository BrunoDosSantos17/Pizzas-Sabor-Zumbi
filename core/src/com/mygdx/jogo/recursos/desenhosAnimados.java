package com.mygdx.jogo.recursos;
//Autor:Bruno dos Santos, Gabriel Kresin e Werner Kupske
//Orientador: Adriano Pessini
import com.badlogic.gdx.graphics.Texture;

public final class desenhosAnimados extends Texture{

    public desenhosAnimados getProximo() {
        return proximo;
    }
    
    desenhosAnimados proximo = null;

    public void setproximo(desenhosAnimados proximo) {
        this.proximo = proximo;
    }
    
    
    public desenhosAnimados(String internalPath) {
        super(internalPath);
    }
    
}
