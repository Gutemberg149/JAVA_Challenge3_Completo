package br.com.fiap.dao;

import br.com.fiap.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {
    private Connection conexao;


    public void cadastrarPacientefunc(Paciente paciente){
        if (!paciente.isCpfValido()) {
            throw new IllegalArgumentException("CPF inválido: deve ter exatamente 11 dígitos");
        }

        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;

        try{
            String sql = "INSERT INTO TBL_HC_PACIENTE(id_paciente, nome_paciente, cpf_paciente, id_consulta) VALUES (?, ?, ?, ?)";
            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1, paciente.getId());
            comandoSQL.setString(2, paciente.getNome());
            comandoSQL.setString(3, paciente.getCpf());


            if (paciente.getConsultaOnline() != null) {
                comandoSQL.setInt(4, paciente.getConsultaOnline().getId_consulta());
            } else {

                comandoSQL.setNull(4, java.sql.Types.INTEGER);
                System.out.println("O médico a tem uma consulta vinculada a esse ID");
            }

            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
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
                paciente.setCpf(rs.getString(3));
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
    ConsultaOnlineDao consultaOnlineDao = new ConsultaOnlineDao();
    try {
        ps = conexao.prepareStatement("SELECT * from TBL_HC_PACIENTE WHERE id_paciente = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            paciente.setId(rs.getInt("id_paciente"));           // Use column names for clarity
            paciente.setNome(rs.getString("nome_paciente"));
            paciente.setCpf(rs.getString("cpf_paciente"));
            int codigoConsulta = rs.getInt("id_consulta");


            if (codigoConsulta != 0) { // presumindo que zero ou null indica ausência
                ConsultaOnline consulta = consultaOnlineDao.buscarPorIdConsultaOnline(codigoConsulta);
                paciente.setConsultaOnline(consulta); // não esquecer de definir
            }
        }
        rs.close();
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
            ps.setString(2, paciente.getCpf());

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


}



