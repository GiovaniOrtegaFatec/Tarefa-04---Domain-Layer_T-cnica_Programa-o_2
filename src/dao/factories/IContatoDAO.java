package dao.factories;

import java.util.List;

import domain.ContatoVO;

public interface IContatoDAO {
    void salvar(ContatoVO contato);

    void atualizar(ContatoVO contato);

    void excluir(Integer id);

    List<ContatoVO> buscarTodos();

    ContatoVO buscarPorEmail(String email);
}
