package repositories;

import java.util.List;

import domain.ContatoVO;

public interface IContatoRepository {
    void salvar(ContatoVO contato);

    void atualizar(ContatoVO contato);

    void excluir(Integer id);

    List<ContatoVO> buscarTodos();

    ContatoVO buscarPorEmail(String email);
}
