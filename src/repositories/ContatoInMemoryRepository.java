package repositories;

import java.util.ArrayList;
import java.util.List;

import domain.ContatoVO;

public class ContatoInMemoryRepository implements IContatoRepository {

    private List<ContatoVO> contatos = new ArrayList<>();

    @Override
    public void salvar(ContatoVO contato) {
        this.contatos.add(contato);
    }

    @Override
    public void atualizar(ContatoVO contato) {
        this.contatos
                .stream()
                .filter(c -> c.getEmail().equals(contato.getEmail()))
                .findFirst()
                .ifPresent(c -> {
                    c.setNome(contato.getNome());
                    c.setTelefone(contato.getTelefone());
                    c.setLinkedin(contato.getLinkedin());
                });

    }

    @Override
    public void excluir(Integer id) {
        this.contatos
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .ifPresent(c -> this.contatos.remove(c));
    }

    @Override
    public List<ContatoVO> buscarTodos() {
        return this.contatos;
    }

    @Override
    public ContatoVO buscarPorEmail(String email) {
        return this.contatos
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

}
