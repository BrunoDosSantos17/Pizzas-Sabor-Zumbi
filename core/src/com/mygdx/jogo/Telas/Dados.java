package com.mygdx.jogo.Telas;

import BancoDeDados.scoreDAO;
import BancoDeDados.scoreDTO;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.PizzaSaborZumbi;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.sql.SQLException;
import static com.mygdx.modos.jogoNormal.inimigos;
import java.util.ArrayList;
import static com.mygdx.jogo.recursos.SetarPosicao.PosicaoX;
import static com.mygdx.jogo.recursos.SetarPosicao.PosicaoY;

/**
 *
 * @author bruno
 */
public class Dados implements Screen {

    TextField texto;
    private PizzaSaborZumbi game;
    private Stage stage;
    private TextField nome;
    private TextField senha;
    private String modoDejogo;
    private int pontuacao;
    private boolean venceu;
    private BitmapFont currentFont;
    private OrthogonalTiledMapRenderer renderer;

    private void verificao() {
        scoreDAO sd = new scoreDAO();
        scoreDTO sdt = new scoreDTO();
        try {
            if (sd.verificaJogador(nome.getText(), senha.getText())) {
                sd.insereJogada(nome.getText(), senha.getText(), pontuacao, modoDejogo);
            } else {
                sd.insereJogador(nome.getText(), senha.getText());           
                sd.insereJogada(nome.getText(), senha.getText(), pontuacao, modoDejogo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        inimigos = new ArrayList();
        PosicaoX = 450;
        PosicaoY = 100;
    }

    public Dados(final PizzaSaborZumbi game, String ModoDeJogo, int pontuacao, boolean vencedor) {
        this.game = game;
        this.modoDejogo = ModoDeJogo;
        this.pontuacao = pontuacao;
        this.venceu = vencedor;
        this.currentFont = new BitmapFont(Gdx.files.internal("text.atlas.fnt"), false);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton btnLogin = new TextButton("Salvar Pontuacao", skin);
        btnLogin.setPosition(500, 35);
        btnLogin.setSize(200, 50);
        btnLogin.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                verificao();
                if (venceu) {
                    game.setScreen(new TelaMenu(game));
                } else {
                    game.setScreen(new GameOver(game));
                }
            }

        });
        nome = new TextField("", skin);

        nome.setPosition(500, 600);
        nome.setSize(300, 40);
        stage.addActor(nome);
        senha = new TextField("", skin);
        senha.setPosition(500, 500);
        senha.setSize(300, 40);
        stage.addActor(senha);
        stage.addActor(btnLogin);

        if (btnLogin.isPressed()) {
            System.out.println("printou");
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
        game.batch.begin();
        currentFont.draw(game.batch, "Nome Jogador: ", 400, 430);
        currentFont.draw(game.batch, "Senha Jogador: ", 400, 362);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
