package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class scoreDAO {

    private static final String STRING_CONEXAO = "jdbc:mysql://localhost/forum?"
            + "user=root&password=alunoifc";
    public boolean result = false;
    scoreDTO sd = new scoreDTO();

    public void insereJogador(String nome, String senha) {
        int id = -1;
        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection conn = DriverManager.getConnection(STRING_CONEXAO);
            String sql = "insert into jogador(nickname, senha) values(?, ?)";
            String sql2 = "id_jogador";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, nome);
            p.setString(2, senha);
            p.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Erro ao enviar jogador.");
        }

    }

    public void insereJogada(String nome, String senha, int pont, String modoDeJogo) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection conn = DriverManager.getConnection(STRING_CONEXAO);
            String sql = "insert into jogada(id_jogador, score , modo_jogo) "
                    + "values( (Select id_jogador from jogador where nickname = '" + nome + "' and senha = '" + senha + "'), ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1, pont);
            p.setString(2, modoDeJogo);

            p.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao enviar jogada.");
            System.out.println(e);
        }
    }

    public boolean verificaJogador(String nome, String senha) throws SQLException {
        String sql = "";
        Connection conn = DriverManager.getConnection(STRING_CONEXAO);
        sql += "select nickname, senha from jogador ";
        sql += "where nickname = '" + nome + "'";
        sql += "and senha = '" + senha + "'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException ex) {

        }
        return result;
    }

}
