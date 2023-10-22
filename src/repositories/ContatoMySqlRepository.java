package repositories;

import java.util.List;

import dao.factories.IContatoDAO;
import domain.ContatoVO;

public class ContatoMySqlRepository implements IContatoRepository{

    private IContatoDAO dao;

    public ContatoMySqlRepository(IContatoDAO dao) {
        this.dao = dao;
    }

    @Override
    public void salvar(ContatoVO contato) {
        dao.salvar(contato);
    }

    @Override
    public void atualizar(ContatoVO contato) {
        dao.atualizar(contato);
    }

    @Override
    public void excluir(Integer id) {
        dao.excluir(id);
    }

    @Override
    public List<ContatoVO> buscarTodos() {
        return dao.buscarTodos();
    }

    @Override
    public ContatoVO buscarPorEmail(String email) {
        return dao.buscarPorEmail(email);
    }
    
}
