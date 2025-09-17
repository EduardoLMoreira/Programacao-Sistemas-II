import java.util.List;

public interface IContaDao {
    boolean criar(Conta c);
    List<Conta> lerTodas() throws Exception;
    Conta buscarPeloNumero(long id) throws Exception;
    boolean atualizar(Conta c);
    boolean apagar(Conta c);
}