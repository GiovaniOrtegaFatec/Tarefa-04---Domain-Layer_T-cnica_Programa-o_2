package dao.factories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.ContatoVO;

public class ContatoMySqlDAO implements IContatoDAO {

    private final Connection connection;
    private final Logger logger;

    public ContatoMySqlDAO(Connection connection) {
        this.connection = connection;
        this.logger = Logger.getLogger(this.getClass().getSimpleName());
    }

    @Override
    public void salvar(ContatoVO contato) {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO contatos (")
                .append("nome, email, telefone, linkedin) ")
                .append("values('%s', '%s', '%s', '%s')");

        String query = String.format(builder.toString(),
                contato.getNome(),
                contato.getEmail(),
                contato.getTelefone(),
                contato.getLinkedin());

        try (Statement stm = connection.createStatement()) {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void atualizar(ContatoVO contato) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE contatos SET ")
                .append("nome = '%s', email = '%s', telefone = '%s', linkedin = '%s' ")
                .append("WHERE id = %d");

    String query = String.format(builder.toString(),
            contato.getNome(),
            contato.getEmail(),
            contato.getTelefone(),
            contato.getLinkedin(),
            contato.getId());

    try (Statement stm = connection.createStatement()) {
        stm.execute(query);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    public void excluir(Integer id) {
    String query = "DELETE FROM contatos WHERE id = " + id;

    try (Statement stm = connection.createStatement()) {
        stm.execute(query);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public List<ContatoVO> buscarTodos() {
        // Declarar lista de contatos
        List<ContatoVO> contatos = new ArrayList<>();
        String query = "SELECT id, nome, email, telefone, linkedin FROM contatos;";
        try (Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query)) {
            // Percorrer o ResulSet e preencher a lista de contatos
            while(rst.next()) {
                ContatoVO contato = new ContatoVO(
                    rst.getInt("id"), 
                    rst.getString("nome"), 
                    rst.getString("email"), 
                    rst.getString("telefone"), 
                    rst.getString("linkedin"));
                // Adicionar o objeto criado com base na consulta para a lista
                contatos.add(contato);
            }
        } catch (Exception e) {
            // Adicionar LOG
            logger.log(Level.SEVERE, "Falha ao conultar contatos.", e);
        }

        // Retornar a lista de contatos
        return contatos;
    }

    @Override
    public ContatoVO buscarPorEmail(String email) {
    String query = "SELECT id, nome, email, telefone, linkedin FROM contatos WHERE email = '" + email + "'";

    try (Statement stm = connection.createStatement();
         ResultSet rst = stm.executeQuery(query)) {
        if (rst.next()) {
            return new ContatoVO(
                rst.getInt("id"), 
                rst.getString("nome"), 
                rst.getString("email"), 
                rst.getString("telefone"), 
                rst.getString("linkedin"));
        }
    } catch (Exception e) {
        logger.log(Level.SEVERE, "Falha ao consultar contato por e-mail.", e);
    }

    return null; // Retorna null se o contato não for encontrado.
}

}
