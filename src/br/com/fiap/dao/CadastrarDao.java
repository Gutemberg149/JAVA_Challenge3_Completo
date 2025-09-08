package br.com.fiap.dao;

import br.com.fiap.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CadastrarDao {
    private Connection conexao;

    public void cadastrarPacientefunc(Paciente paciente){
        conexao = ConnectionFactory.obterConexao();

        PreparedStatement comandoSQL = null;

        try{
            String sql = "INSERT INTO TBL_HC_PACIENTE(id_paciente, nome_paciente, cpf_paciente) values(?,?,?)";

            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1,paciente.getId());
            comandoSQL.setString(2, paciente.getNome());
            comandoSQL.setInt(3, paciente.getCpf());
            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();
        }catch (SQLException e){
            e.printStackTrace();
        };
    }
    public List<Paciente> listarPacientes(){
        List<Paciente> pacientes = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("SELECT * FROM TBL_HC_PACIENTE order by nome_paciente ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt(1));
                paciente.setNome(rs.getString(2));
                paciente.setCpf(rs.getInt(3));
                pacientes.add(paciente);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacientes;
    }
    public Paciente buscarPorIdPaciente(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Paciente paciente = new Paciente();
        try {
            ps = conexao.prepareStatement("SELECT * from TBL_HC_PACIENTE  " +
                    "WHERE id_paciente = ?");
            ps.setInt(1, id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                paciente.setId(rs.getInt(1));
                paciente.setNome(rs.getString(2));
                paciente.setCpf(rs.getInt(3));

            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }
    public void upDatePaciente(Paciente paciente){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE TBL_HC_PACIENTE SET  nome_paciente = ?, cpf_paciente = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, paciente.getNome());
            ps.setInt(2, paciente.getCpf());

            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void excluirPaciente(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("delete from " +
                    "TBL_HC_PACIENTE where id_paciente = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void cadastrarDocumentoPaciente(DoumentoPaciente doumentoPaciente){
        conexao = ConnectionFactory.obterConexao();

        PreparedStatement comandoSQL = null;

        try{
            String sql = "INSERT INTO TBL_HC_DOCUMENT_PACIENTE(id_documneto, valido, type_documento) values(?,?,?)";

            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1,doumentoPaciente.getId_documneto());
            comandoSQL.setInt(2, doumentoPaciente.isValido());
            comandoSQL.setString(3, doumentoPaciente.getType_documento());

            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        }catch (SQLException e){
            e.printStackTrace();
        };
    }
    public List<DoumentoPaciente> ListarDocumentos(){
        List<DoumentoPaciente> documentos = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("SELECT * FROM TBL_HC_DOCUMENT_PACIENTE order by id_documneto ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                DoumentoPaciente doc = new DoumentoPaciente();
                doc.setId_documneto(rs.getInt(1));
                doc.setValido(rs.getInt(2));
                doc.setType_documento(rs.getString(3));

                documentos.add(doc);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return documentos;
    }
    public DoumentoPaciente buscarPorIdDocPaciente(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        DoumentoPaciente doc = new DoumentoPaciente();
        try {
            ps = conexao.prepareStatement("SELECT * from TBL_HC_DOCUMENT_PACIENTE" +
                    " WHERE id_documneto = ?");
            ps.setInt(1, id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                doc.setId_documneto(rs.getInt(1));
                doc.setValido(rs.getInt(2));
                doc.setType_documento(rs.getString(3));

            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doc;
    }
    public void upDateDocPaciente(DoumentoPaciente docpaciente){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE TBL_HC_DOCUMENT_PACIENTE SET  valido = ?, type_documento = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, docpaciente.isValido());
            ps.setString(2, docpaciente.getType_documento());

            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void excluirDocPaciente(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("delete from " +
                    "TBL_HC_DOCUMENT_PACIENTE where id_documneto = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




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



    public void cadastrarMedico(Medico medico) {
        conexao = ConnectionFactory.obterConexao();

        PreparedStatement comandoSQL = null;

        try {
            String sql = "INSERT INTO TBL_HC_MEDICOS(id_medico, especialidade, crm) values(?,?,?)";

            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1, medico.getId_medico());
            comandoSQL.setString(2, medico.getEspecialidade());
            comandoSQL.setInt(3, medico.getCrm());

            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Medico> ListarMedicos(){
        List<Medico> medicos = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("SELECT * FROM TBL_HC_MEDICOS order by id_medico");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Medico medico = new Medico();
                medico.setId_medico(rs.getInt(1));
                medico.setEspecialidade(rs.getString(2));
                medico.setCrm(rs.getInt(3));

                medicos.add(medico);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicos;
    }
    public Medico buscarPorIdMedico(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Medico medico = new Medico();
        try {
            ps = conexao.prepareStatement("SELECT * from TBL_HC_MEDICOS" +
                    " WHERE id_medico = ?");
            ps.setInt(1, id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                medico.setId_medico(rs.getInt(1));
                medico.setEspecialidade(rs.getString(2));
                medico.setCrm(rs.getInt(3));

            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }
    public void upDateMedico(Medico medico){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE TBL_HC_MEDICOS SET  especialidade = ?, crm = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, medico.getEspecialidade());
            ps.setInt(2, medico.getCrm());

            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void excluirMedico(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("delete from " +
                    "TBL_HC_MEDICOS where id_medico = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void cadastrarConfirmacaoConsulta(ConfirmacaoConsulta confirmacaoConsulta) {
        conexao = ConnectionFactory.obterConexao();

        PreparedStatement comandoSQL = null;

        try {
            String sql = "INSERT INTO TBL_HC_CONFIRMACAO_CONSULTA(id_confirmacao, codigoConfirmacao, metodoconfirmacao) values(?,?,?)";

            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1, confirmacaoConsulta.getId_confirmacao());
            comandoSQL.setInt(2, confirmacaoConsulta.getCodigoConfirmacao());
            comandoSQL.setString(3, confirmacaoConsulta.getMetodoconfirmacao());

            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<ConfirmacaoConsulta> ListarConfirmacaoDeconsulta(){
        List<ConfirmacaoConsulta> confirmacaoConsultas = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("SELECT * FROM TBL_HC_CONFIRMACAO_CONSULTA order by id_confirmacao");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ConfirmacaoConsulta confirmacaoConsulta = new ConfirmacaoConsulta();
                confirmacaoConsulta.setId_confirmacao(rs.getInt(1));
                confirmacaoConsulta.setCodigoConfirmacao(rs.getInt(2));
                confirmacaoConsulta.setMetodoconfirmacao(rs.getString(3));


                confirmacaoConsultas.add(confirmacaoConsulta);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return confirmacaoConsultas;
    }
    public ConfirmacaoConsulta buscarPorIdConfirmacaoConsulta(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ConfirmacaoConsulta confirmacaoConsulta = new ConfirmacaoConsulta();
        try {
            ps = conexao.prepareStatement("SELECT * from TBL_HC_CONFIRMACAO_CONSULTA" +
                    " WHERE id_confirmacao = ?");
            ps.setInt(1, id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                confirmacaoConsulta.setCodigoConfirmacao(rs.getInt(1));
                confirmacaoConsulta.setMetodoconfirmacao(rs.getString(2));


            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return confirmacaoConsulta;
    }
    public void upDateconfirmacaoConsulta(ConfirmacaoConsulta confirmacaoConsulta){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE TBL_HC_CONFIRMACAO_CONSULTA SET  codigoConfirmacao = ?, metodoconfirmacao = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt (1, confirmacaoConsulta.getCodigoConfirmacao());
            ps.setString(2, confirmacaoConsulta.getMetodoconfirmacao());

            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void excluiConfirmacaoConsulta(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("delete from " +
                    "TBL_HC_CONFIRMACAO_CONSULTA where id_confirmacao = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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



