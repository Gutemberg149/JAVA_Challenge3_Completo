package br.com.fiap.dao;

import br.com.fiap.models.ConsultaOnline;
import br.com.fiap.models.Exame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ConsultaOnlineDao {
    private Connection conexao;

//    public void cadastrarConsultaOnline(ConsultaOnline consultaOnline) {
//            conexao = ConnectionFactory.obterConexao();
//
//            PreparedStatement comandoSQL = null;
//
//            try {
//                String sql = "INSERT INTO TBL_HC_CONSULTA_ONLINE(id_consulta, data_Consulta, status, link, id_exame) values(?,?,?,?,?)";
//
//                comandoSQL = conexao.prepareStatement(sql);
//
//                comandoSQL.setInt(1, consultaOnline.getId_consulta());
//                comandoSQL.setDate(2, java.sql.Date.valueOf(consultaOnline.getDataConsulta()));
//                comandoSQL.setString(3, consultaOnline.getStatus());
//                comandoSQL.setString(4, consultaOnline.getLink());
//
//                if (consultaOnline.getExame() != null) {
//                    comandoSQL.setInt(5, consultaOnline.getExame().getId_exame());
//                } else {
//                    comandoSQL.setNull(5, java.sql.Types.INTEGER);
//                }
//
//                comandoSQL.executeUpdate();
//                comandoSQL.close();
//                conexao.close();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
public void cadastrarConsultaOnline(ConsultaOnline consultaOnline) throws SQLIntegrityConstraintViolationException {
    conexao = ConnectionFactory.obterConexao();
    PreparedStatement comandoSQL = null;

    try {
        String sql = "INSERT INTO TBL_HC_CONSULTA_ONLINE(id_consulta, data_Consulta, status, link, id_exame) values(?,?,?,?,?)";

        comandoSQL = conexao.prepareStatement(sql);

        comandoSQL.setInt(1, consultaOnline.getId_consulta());
        comandoSQL.setDate(2, java.sql.Date.valueOf(consultaOnline.getDataConsulta()));
        comandoSQL.setString(3, consultaOnline.getStatus());
        comandoSQL.setString(4, consultaOnline.getLink());

        if (consultaOnline.getExame() != null) {
            comandoSQL.setInt(5, consultaOnline.getExame().getId_exame());
        } else {
            comandoSQL.setNull(5, java.sql.Types.INTEGER);
        }

        comandoSQL.executeUpdate();
        System.out.println("Consulta online inserida com sucesso. ID: " + consultaOnline.getId_consulta());

    } catch (SQLIntegrityConstraintViolationException e) {
        System.out.println("ERRO: Já existe uma consulta com o ID " + consultaOnline.getId_consulta() +
                ". Por favor, use um ID diferente.");
        throw e; // Re-lançar a exceção para tratamento superior
    } catch (SQLException e) {
        System.out.println("Erro de banco de dados: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (comandoSQL != null) comandoSQL.close();
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public List<ConsultaOnline> ListarConsultasOnline() {
            List<ConsultaOnline> consultas = new ArrayList<>();
            conexao = ConnectionFactory.obterConexao();
            PreparedStatement ps = null;
            ExameDao exameDao = new ExameDao(); // Create ExameDao instance to fetch exams
            try {
                ps = conexao.prepareStatement("SELECT * FROM TBL_HC_CONSULTA_ONLINE ORDER BY id_consulta");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ConsultaOnline consulta = new ConsultaOnline();
                    consulta.setId_consulta(rs.getInt("ID_CONSULTA"));

                    java.sql.Date dataSql = rs.getDate("DATA_CONSULTA");
                    if (dataSql != null) {
                        consulta.setDataConsulta(dataSql.toLocalDate());
                    }

                    consulta.setStatus(rs.getString("STATUS"));
                    consulta.setLink(rs.getString("LINK"));

                    int idExame = rs.getInt("id_exame");
                    if (!rs.wasNull()) {
                        Exame exame = exameDao.buscarPorIdExame(idExame);
                        consulta.setExame(exame);
                    }

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
    ExameDao exameDao = new ExameDao();
    try {
        String sql = "SELECT * FROM TBL_HC_CONSULTA_ONLINE WHERE id_consulta = ?";
        ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            consulta = new ConsultaOnline();
            consulta.setId_consulta(rs.getInt("ID_CONSULTA"));

            java.sql.Date dataSql = rs.getDate("DATA_CONSULTA");
            if (dataSql != null) {
                consulta.setDataConsulta(dataSql.toLocalDate());
            }

            consulta.setStatus(rs.getString("STATUS"));
            consulta.setLink(rs.getString("LINK"));


            int codigoExame = rs.getInt("id_exame");
            if (!rs.wasNull()) {
                Exame exame = exameDao.buscarPorIdExame(codigoExame);
                if (exame != null) {
                    consulta.setExame(exame);
                } else {
                    System.out.println("DEBUG: Exame com ID " + codigoExame + " não encontrado na tabela TBL_HC_EXAME");
                }
            }
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
            String sql = "UPDATE TBL_HC_CONSULTA_ONLINE SET  data_Consulta = ?, status = ?,link = ?";
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
