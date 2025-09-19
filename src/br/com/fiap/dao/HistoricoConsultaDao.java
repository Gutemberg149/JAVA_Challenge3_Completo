package br.com.fiap.dao;

import br.com.fiap.models.ConsultaOnline;
import br.com.fiap.models.HistoricoConsulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoConsultaDao {
    private Connection conexao;

//    public void cadastrarHistoricoConsulta(HistoricoConsulta historicoconsulta) {
//        conexao = ConnectionFactory.obterConexao();
//
//        PreparedStatement comandoSQL = null;
//
//        try {
//            String sql = "INSERT INTO TBL_HC_HISTORICOS(id_historico, sintomas_historico, diagnostico, observacoes,id_consulta) values(?,?,?,?,?)";
//
//            comandoSQL = conexao.prepareStatement(sql);
//
//            comandoSQL.setInt(1, historicoconsulta.getId_historico());
//            comandoSQL.setString(2, historicoconsulta.getSintomas_historico());
//            comandoSQL.setString(3, historicoconsulta.getDiagnostico());
//            comandoSQL.setString(4, historicoconsulta.getObservacao());
//
//            if (historicoconsulta.getConsultaOnline() != null) {
//                comandoSQL.setInt(5, historicoconsulta.getConsultaOnline().getId_consulta());
//            } else {
//
//                comandoSQL.setNull(5, java.sql.Types.INTEGER);
//            }
//
//
//            comandoSQL.executeUpdate();
//            comandoSQL.close();
//            conexao.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
public void cadastrarHistoricoConsulta(HistoricoConsulta historicoconsulta) {
    Connection conexao = null;
    PreparedStatement comandoSQL = null;

    try {
        conexao = ConnectionFactory.obterConexao();
        String sql = "INSERT INTO TBL_HC_HISTORICOS(id_historico, sintomas_historico, diagnostico, observacoes, id_consulta) values(?,?,?,?,?)";

        comandoSQL = conexao.prepareStatement(sql);
        comandoSQL.setInt(1, historicoconsulta.getId_historico());
        comandoSQL.setString(2, historicoconsulta.getSintomas_historico());
        comandoSQL.setString(3, historicoconsulta.getDiagnostico());
        comandoSQL.setString(4, historicoconsulta.getObservacao());

        if (historicoconsulta.getConsultaOnline() != null) {
            comandoSQL.setInt(5, historicoconsulta.getConsultaOnline().getId_consulta());
        } else {
            comandoSQL.setNull(5, java.sql.Types.INTEGER);
        }

        comandoSQL.executeUpdate();
        System.out.println("Histórico cadastrado com sucesso! ID: " + historicoconsulta.getId_historico());

    } catch (SQLIntegrityConstraintViolationException e) {
        System.err.println("ERRO: Já existe um histórico com o ID " + historicoconsulta.getId_historico());
        throw new RuntimeException("ID duplicado: " + historicoconsulta.getId_historico(), e);
    } catch (SQLException e) {
        System.err.println("Erro ao cadastrar histórico: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Erro de banco de dados", e);
    } finally {
        try {
            if (comandoSQL != null) comandoSQL.close();
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}

public List<HistoricoConsulta> listarTodosHistoricos() {
    List<HistoricoConsulta> historicos = new ArrayList<>();
    Connection conexao = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conexao = ConnectionFactory.obterConexao();
        ps = conexao.prepareStatement("SELECT * FROM TBL_HC_HISTORICOS ORDER BY id_historico");
        rs = ps.executeQuery();

        while (rs.next()) {
            HistoricoConsulta historicoconsulta = new HistoricoConsulta();
            historicoconsulta.setId_historico(rs.getInt("id_historico"));
            historicoconsulta.setSintomas_historico(rs.getString("sintomas_historico"));
            historicoconsulta.setDiagnostico(rs.getString("diagnostico"));
            historicoconsulta.setObservacao(rs.getString("observacoes"));

            // Buscar consulta online associada se existir
            int idConsulta = rs.getInt("id_consulta");
            if (!rs.wasNull()) {
                ConsultaOnlineDao consultaDao = new ConsultaOnlineDao();
                ConsultaOnline consulta = consultaDao.buscarPorIdConsultaOnline(idConsulta);
                historicoconsulta.setConsultaOnline(consulta);
            }

            historicos.add(historicoconsulta);
        }

    } catch (SQLException e) {
        System.err.println("Erro ao listar históricos: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
    return historicos;
}


public HistoricoConsulta buscarPorIdhistorico(int id){
    conexao = ConnectionFactory.obterConexao();
    PreparedStatement ps = null;
    HistoricoConsulta historicoconsulta = null; // Inicializar como null

    try {
        ps = conexao.prepareStatement("SELECT * from TBL_HC_HISTORICOS WHERE id_historico = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            historicoconsulta = new HistoricoConsulta(); // Criar nova instância
            historicoconsulta.setId_historico(rs.getInt("id_historico")); // Corrigir índices
            historicoconsulta.setSintomas_historico(rs.getString("sintomas_historico"));
            historicoconsulta.setDiagnostico(rs.getString("diagnostico"));
            historicoconsulta.setObservacao(rs.getString("observacoes"));

            // Verificar se há id_consulta e buscar a consulta online se existir
            int idConsulta = rs.getInt("id_consulta");
            if (!rs.wasNull()) {
                ConsultaOnlineDao consultaDao = new ConsultaOnlineDao();
                ConsultaOnline consulta = consultaDao.buscarPorIdConsultaOnline(idConsulta);
                historicoconsulta.setConsultaOnline(consulta);
            }
        }

        ps.close();
        conexao.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return historicoconsulta;
}


public void upDateHistorico(HistoricoConsulta historicoconsulta){
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
