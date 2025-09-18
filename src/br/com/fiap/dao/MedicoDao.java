package br.com.fiap.dao;

import br.com.fiap.models.ConsultaOnline;
import br.com.fiap.models.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDao {
    private Connection conexao;

    public void cadastrarMedico(Medico medico) {
        conexao = ConnectionFactory.obterConexao();

        PreparedStatement comandoSQL = null;

        try {


            String sql = "INSERT INTO TBL_HC_MEDICOS(id_medico, especialidade, crm,id_consulta) values(?,?,?,?)";

            comandoSQL = conexao.prepareStatement(sql);

            comandoSQL.setInt(1, medico.getId_medico());
            comandoSQL.setString(2, medico.getEspecialidade());
            comandoSQL.setInt(3, medico.getCrm());

            if (medico.getConsultaOnline() != null) {
                comandoSQL.setInt(4, medico.getConsultaOnline().getId_consulta());
            } else {

                comandoSQL.setNull(4, java.sql.Types.INTEGER);
            }

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
        ConsultaOnlineDao consultaOnlineDao = new ConsultaOnlineDao();
        try {
            ps = conexao.prepareStatement("SELECT * from TBL_HC_MEDICOS" +
                    " WHERE id_medico = ?");
            ps.setInt(1, id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                medico.setId_medico(rs.getInt(1));
                medico.setEspecialidade(rs.getString(2));
                medico.setCrm(rs.getInt(3));
                int codigoConsulta = rs.getInt("id_consulta");

                if (codigoConsulta != 0) { // presumindo que zero ou null indica ausência
                    ConsultaOnline consulta = consultaOnlineDao.buscarPorIdConsultaOnline(codigoConsulta);
                    medico.setConsultaOnline(consulta); // não esquecer de definir
                }

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
}
