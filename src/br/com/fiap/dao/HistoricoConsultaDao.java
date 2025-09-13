package br.com.fiap.dao;

import br.com.fiap.models.Historicoconsulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoConsultaDao {
    private Connection conexao;

    public void cadastrarHistoricoConsulta(Historicoconsulta historicoconsulta) {
        conexao = ConnectionFactory.obterConexao();

        PreparedStatement comandoSQL = null;

        try {
            String sql = "INSERT INTO TBL_HC_HISTORICOS(id_historico, sintomas_historico, diagnostico, observacoes) values(?,?,?,?)";

            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1, historicoconsulta.getId_historico());
            comandoSQL.setString(2, historicoconsulta.getSintomas_historico());
            comandoSQL.setString(3, historicoconsulta.getDiagnostico());
            comandoSQL.setString(4, historicoconsulta.getObservacao());

            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Historicoconsulta> ListarhistoricosConsultas(){
        List<Historicoconsulta> historicoconsultas = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("SELECT * FROM TBL_HC_HISTORICOS order by id_historico");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Historicoconsulta historicoconsulta = new Historicoconsulta();
                historicoconsulta.setId_historico(rs.getInt(1));
                historicoconsulta.setSintomas_historico(rs.getString(2));
                historicoconsulta.setDiagnostico(rs.getString(3));
                historicoconsulta.setObservacao(rs.getString(3));


                historicoconsultas.add(historicoconsulta);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicoconsultas;
    }
    public Historicoconsulta buscarPorIdhistorico(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Historicoconsulta historicoconsulta = new Historicoconsulta();
        try {
            ps = conexao.prepareStatement("SELECT * from TBL_HC_HISTORICOS" +
                    " WHERE id_historico = ?");
            ps.setInt(1, id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                historicoconsulta.setSintomas_historico(rs.getString(1));
                historicoconsulta.setDiagnostico(rs.getString(2));
                historicoconsulta.setObservacao(rs.getString(2));


            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicoconsulta;
    }
    public void upDateHistorico(Historicoconsulta historicoconsulta){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE TBL_HC_HISTORICOS SET  sintomas_historico = ?, diagnostico = ?, observacoes = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString (1, historicoconsulta.getSintomas_historico());
            ps.setString(2, historicoconsulta.getDiagnostico());
            ps.setString (3, historicoconsulta.getObservacao());

            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void excluiHistoricoConsulta(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("delete from " +
                    "TBL_HC_HISTORICOS where id_historico = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
