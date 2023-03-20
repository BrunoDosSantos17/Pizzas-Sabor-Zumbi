package BancoDeDados;

public class scoreDTO {
    
    private String nome;
    private  int pont;
    private String senha;
    private String modoDeJogo ;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        if(!senha.isEmpty()){
        this.senha = senha;
        } else{
             throw new Exception("Senha inválida.");
        }
    }

    public String getModoDeJogo() {
        return modoDeJogo;
    }

    public void setModoDeJogo(String modoDeJogo) throws Exception {
        if(!modoDeJogo.isEmpty()){
        this.modoDeJogo = modoDeJogo;
        } else{
             throw new Exception("Modo de jogo inválido.");
        }
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        if(!nome.isEmpty()){
        this.nome = nome;
        } else{
             throw new Exception("Nome inválido.");
        }
    }

    public int getPont() {
        return pont;
    }

    public void setPont(int pont) {
        this.pont = pont;
    }
    
 
    

}
