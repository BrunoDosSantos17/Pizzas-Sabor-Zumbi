package Jogador;

public class Player {
    
    private static int vida = 100;
    private float veloc = 1.5f;
    private int dmg = 5;
    private int ammo = 40;
    private static int pontuacao = 0;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public float getVeloc() {
        return veloc;
    }

    public void setVeloc(int veloc) {
        this.veloc = veloc;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    
    public Player p(int vida, int veloc, int dmg, int ammo){
        this.ammo = ammo;
        this.dmg = dmg;
        this.veloc = veloc;
        this.vida = vida;
        return null;
    }
    
}
