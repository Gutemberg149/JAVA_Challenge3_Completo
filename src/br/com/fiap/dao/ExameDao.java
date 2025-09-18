package br.com.fiap.dao;

import br.com.fiap.models.Exame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExameDao {
    private Connection conexao;

    public void cadastrarExame(Exame exame) {
        conexao = ConnectionFactory.obterConexao();

        PreparedStatement comandoSQL = null;

        try {
            String sql = "INSERT INTO TBL_HC_EXAME(id_exame, nome_exame, resultado_exame) values(?,?,?)";

            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1, exame.getId_exame());
            comandoSQL.setString(2, exame.getNome_exame());
            comandoSQL.setString(3, exame.getResultado_exame());

            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> ListarExamesComResultado() {
        List<String> examesComStatus = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement("SELECT * FROM TBL_HC_EXAME ORDER BY id_exame");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exame exame = new Exame();
                exame.setId_exame(rs.getInt(1));
                exame.setNome_exame(rs.getString(2));
                exame.setResultado_exame(rs.getString(3));

                // Use verificarResultado() method here
                String status = exame.verificarResultado();
                String descricao = "ID: " + exame.getId_exame() +
                        ", Nome: " + exame.getNome_exame() +
                        ", Resultado: " + exame.getResultado_exame() +
                        ", Status: " + status;
                examesComStatus.add(descricao);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return examesComStatus;
    }


    public Exame buscarPorIdExame(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Exame exame = null; // Changed from new Exame() to null
        try {
            ps = conexao.prepareStatement("SELECT * from TBL_HC_EXAME WHERE id_exame = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                exame = new Exame(); // Create new instance only when record exists
                exame.setId_exame(rs.getInt("id_exame")); // Fixed: set the ID first
                exame.setNome_exame(rs.getString("nome_exame"));
                exame.setResultado_exame(rs.getString("resultado_exame"));
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exame;
    }

    public void upDateExame(Exame exame){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE TBL_HC_EXAME SET  nome_exame = ?, resultado_exame = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString (1, exame.getNome_exame());
            ps.setString(2, exame.getResultado_exame());

            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirExame(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("delete from " +
                    "TBL_HC_EXAME where id_exame = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
