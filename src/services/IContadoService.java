package services;

import java.util.List;

import domain.ContatoVO;

public interface IContadoService {
    void salvar(ContatoVO contato);

    void alterar(ContatoVO contato);

    ContatoVO buscarPorEmail(String email);

    void excluir(Integer id);

    List<ContatoVO> buscarTodos();
}
