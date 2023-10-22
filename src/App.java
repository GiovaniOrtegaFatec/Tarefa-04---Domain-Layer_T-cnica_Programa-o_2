import java.sql.Connection;

import dao.factories.ConexaoFactory;
import dao.factories.ContatoMySqlDAO;
import dao.factories.IContatoDAO;
import domain.ContatoVO;
import repositories.ContatoMySqlRepository;
import repositories.IContatoRepository;
import services.ContatoService;
import services.IContadoService;

public class App {
    public static void main(String[] args) throws Exception {

        /*
         * Este conjunto de instruções inicializaram as dependencias 
         * para o uso do serviço de contatos utilizando o repositório
         * com o SGBD MySQL.
         */
        Connection conexao = ConexaoFactory.getConexao();
        IContatoDAO dao = new ContatoMySqlDAO(conexao);
        IContatoRepository repository = new ContatoMySqlRepository(dao);

        IContadoService service = new ContatoService(repository);

        // Criar objeto ContatoVO e realizar chamada do metodo salvar do service
        ContatoVO c1 = new ContatoVO(
            null,
            "João Pedro Ferreira",
            "jpferreira@gmail.com",
            "19999997878",
            "jpferreira_li");

        service.salvar(c1);

        ContatoVO c2 = new ContatoVO(
            null,
            "Maria Silva",
            "maria.silva@example.com",
            "19999998888",
            "mariasilva_li");

        ContatoVO c3 = new ContatoVO(
            null,
            "Carlos Pereira",
            "carlos.pereira@example.com",
            "19999997777",
            "carlospereira_li");

        service.salvar(c2);
        service.salvar(c3);

        System.out.println("Contatos Cadastrados:");
        List<ContatoVO> contatosCadastrados = service.buscarTodos();
        for (ContatoVO contato : contatosCadastrados) {
            System.out.println("ID: " + contato.getId());
            System.out.println("Nome: " + contato.getNome());
            System.out.println("E-mail: " + contato.getEmail());
            System.out.println("Telefone: " + contato.getTelefone());
            System.out.println("LinkedIn: " + contato.getLinkedin());
            System.out.println();
        }

        service.excluir(c1.getId());

        ContatoVO contatoC2 = service.buscarPorEmail("maria.silva@example.com");
        if (contatoC2 != null) {
            System.out.println("Contato encontrado com base no e-mail:");
            System.out.println("ID: " + contatoC2.getId());
            System.out.println("Nome: " + contatoC2.getNome());
            System.out.println("E-mail: " + contatoC2.getEmail());
            System.out.println("Telefone: " + contatoC2.getTelefone());
            System.out.println("LinkedIn: " + contatoC2.getLinkedin());
        } else {
            System.out.println("Nenhum contato encontrado com o e-mail fornecido.");
        }

        System.out.println("Contatos Cadastrados Após Remoção:");
        contatosCadastrados = service.buscarTodos();
        for (ContatoVO contato : contatosCadastrados) {
            System.out.println("ID: " + contato.getId());
            System.out.println("Nome: " + contato.getNome());
            System.out.println("E-mail: " + contato.getEmail());
            System.out.println("Telefone: " + contato.getTelefone());
            System.out.println("LinkedIn: " + contato.getLinkedin());
            System.out.println();
        }

        IContatoRepository inMemoryRepository = new ContatoInMemoryRepository();

        IContadoService inMemoryService = new ContatoService(inMemoryRepository);

    }
}

