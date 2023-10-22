package services;

import java.util.List;
import java.util.Objects;

import domain.ContatoVO;
import repositories.IContatoRepository;

public class ContatoService implements IContadoService {

    private final IContatoRepository repository;

    public ContatoService(IContatoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvar(ContatoVO contato) {
        // Validar objeto conforme a entidade contatos
        if (Objects.isNull(contato.getNome()) || contato.getNome().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório!");
        }

        if (Objects.isNull(contato.getEmail()) || contato.getEmail().isEmpty()) {
            throw new RuntimeException("É-mail é obrigatório!");
        }

        repository.salvar(contato);
    }

    @Override
    public void alterar(ContatoVO contato) {
    // Validações
    if (Objects.isNull(contato)) {
        throw new IllegalArgumentException("Contato não pode ser nulo.");
    }

    if (Objects.isNull(contato.getNome()) || contato.getNome().isEmpty()) {
        throw new IllegalArgumentException("Nome é obrigatório.");
    }

    if (Objects.isNull(contato.getEmail()) || contato.getEmail().isEmpty()) {
        throw new IllegalArgumentException("E-mail é obrigatório.");
    }

    // Consultar e recuperar o contato
    ContatoVO contatoExistente = repository.buscarPorEmail(contato.getEmail());

    if (Objects.isNull(contatoExistente)) {
        throw new IllegalArgumentException("Contato não encontrado.");
    }

    contatoExistente.setNome(contato.getNome());
    contatoExistente.setTelefone(contato.getTelefone());
    contatoExistente.setLinkedin(contato.getLinkedin());

    repository.atualizar(contatoExistente);
    }

    @Override
    public ContatoVO buscarPorEmail(String email) {
    if (Objects.isNull(email) || email.isEmpty()) {
        throw new IllegalArgumentException("E-mail é obrigatório.");
    }

    // Localizar o contato por e-mail
    ContatoVO contato = repository.buscarPorEmail(email);

    if (Objects.isNull(contato)) {
        throw new IllegalArgumentException("Contato não encontrado.");
    }

    return contato;
    }

    @Override
    public void excluir(Integer id) {
    if (Objects.isNull(id) || id <= 0) {
        throw new IllegalArgumentException("ID inválido.");
    }

    // Localizar o contato por ID
    ContatoVO contato = repository.buscarPorId(id);

    if (Objects.isNull(contato)) {
        throw new IllegalArgumentException("Contato não encontrado.");
    }

    // Chamar o método de exclusão no repositório
    repository.excluir(id);
    }

    @Override
    public List<ContatoVO> buscarTodos() {
    List<ContatoVO> contatos = repository.buscarTodos();

    if (contatos == null || contatos.isEmpty()) {
        throw new RuntimeException("Nenhum contato cadastrado encontrado.");
    }

    return contatos;
    
    }

}
