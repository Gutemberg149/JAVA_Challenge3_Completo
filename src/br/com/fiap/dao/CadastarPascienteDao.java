package br.com.fiap.dao;

import br.com.fiap.models.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastarPascienteDao {
    private Connection conexao;

    public void cadastrarPacientefunc(Paciente paciente){
        conexao = ConnectionFactory.obterConexao();

        PreparedStatement comandoSQL = null;

        try{
            String sql = "INSERT INTO TBL_HC_PACIENTE values(id_paciente, nome_paciente, cpf_paciente) values(?,?,?)";

            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1,paciente.getId());
            comandoSQL.setString(2, paciente.getNome());
            comandoSQL.setInt(3, paciente.getCpf());

            comandoSQL.close();
            conexao.close();
        }catch (SQLException e){
            e.printStackTrace();
        };
    }
}
