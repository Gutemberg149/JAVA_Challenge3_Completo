package br.com.fiap.dao;

import br.com.fiap.models.ConsultaOnline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ConsultaOnlineDao {
    private Connection conexao;
    public void cadastrarConsultaOnline(ConsultaOnline consultaOnline){
        conexao = ConnectionFactory.obterConexao();

        PreparedStatement comandoSQL = null;

        try{
            String sql = "INSERT INTO TBL_HC_CONSULTA_ONLINE(id_consulta, dataConsulta, status, link) values(?,?,?,?)";

            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1,consultaOnline.getId_consulta());
            comandoSQL.setDate(2, java.sql.Date.valueOf(consultaOnline.getDataConsulta()));
            comandoSQL.setString(3, consultaOnline.getStatus());
            comandoSQL.setString(4, consultaOnline.getLink());

            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        }catch (SQLException e){
            e.printStackTrace();
        };
    }
    public List<ConsultaOnline> ListarConsultasOnline() {
        List<ConsultaOnline> consultas = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("SELECT * FROM TBL_HC_CONSULTA_ONLINE ORDER BY id_consulta");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ConsultaOnline consulta = new ConsultaOnline();
                consulta.setId_consulta(rs.getInt("ID_CONSULTA"));

                java.sql.Date dataSql = rs.getDate("DATACONSULTA");
                if (dataSql != null) {
                    consulta.setDataConsulta(dataSql.toLocalDate());
                }

                consulta.setStatus(rs.getString("STATUS"));
                consulta.setLink(rs.getString("LINK"));

                consultas.add(consulta);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultas;
    }
    public ConsultaOnline buscarPorIdConsultaOnline(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ConsultaOnline consulta = null;
        try {
            String sql = "SELECT * FROM TBL_HC_CONSULTA_ONLINE WHERE id_consulta = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                consulta = new ConsultaOnline();
                consulta.setId_consulta(rs.getInt("ID_CONSULTA"));

                java.sql.Date dataSql = rs.getDate("DATACONSULTA");
                if (dataSql != null) {
                    consulta.setDataConsulta(dataSql.toLocalDate());
                }

                consulta.setStatus(rs.getString("STATUS"));
                consulta.setLink(rs.getString("LINK"));
            }

            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consulta;
    }
    public void upDateConsultaOnline(ConsultaOnline consultaOnline){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE TBL_HC_CONSULTA_ONLINE SET  dataConsulta = ?, status = ?,link = ?";
            ps = conexao.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(consultaOnline.getDataConsulta()));
            ps.setString(2, consultaOnline.getStatus());
            ps.setString(3, consultaOnline.getLink());

            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void excluirConsultaOnline(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("delete from " +
                    "TBL_HC_CONSULTA_ONLINE where id_consulta = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
