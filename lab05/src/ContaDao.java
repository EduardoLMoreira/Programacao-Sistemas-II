import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ContaDao implements IContaDao {
    private final PreparedStatement pstmCreate;
    private final PreparedStatement pstmRead;
    private final PreparedStatement pstmReadByNumber;
    private final PreparedStatement pstmUpdate;
    private final PreparedStatement pstmDelete;

    public ContaDao(Connection c) throws Exception {
        // Ajuste do SQL de insert, especificando as colunas
        pstmCreate = c.prepareStatement(
            "INSERT INTO CONTAS (nro_conta, saldo) VALUES (?, ?)"
        );
        pstmRead = c.prepareStatement("SELECT * FROM CONTAS");
        pstmReadByNumber = c.prepareStatement(
            "SELECT * FROM CONTAS WHERE nro_conta = ?"
        );
        pstmUpdate = c.prepareStatement(
            "UPDATE CONTAS SET saldo = ? WHERE nro_conta = ?"
        );
        pstmDelete = c.prepareStatement(
            "DELETE FROM CONTAS WHERE nro_conta = ?"
        );
    }

    @Override
    public boolean criar(Conta c) {
        try {
            pstmCreate.setLong(1, c.getNumero());
            pstmCreate.setBigDecimal(2, c.getSaldo());
            return pstmCreate.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Conta> lerTodas() throws Exception {
        List<Conta> contas = new ArrayList<>();
        try (ResultSet resultados = pstmRead.executeQuery()) {
            while (resultados.next()) {
                long n = resultados.getLong("nro_conta");
                BigDecimal s = resultados.getBigDecimal("saldo");
                contas.add(new Conta(n, s));
            }
        }
        return contas;
    }

    @Override
    public Conta buscarPeloNumero(long id) throws Exception {
        Conta c = null;
        pstmReadByNumber.setLong(1, id);
        try (ResultSet resultado = pstmReadByNumber.executeQuery()) {
            if (resultado.next()) {
                long n = resultado.getLong("nro_conta");
                BigDecimal s = resultado.getBigDecimal("saldo");
                c = new Conta(n, s);
            }
        }
        return c;
    }

    @Override
    public boolean atualizar(Conta c) {
        try {
            pstmUpdate.setBigDecimal(1, c.getSaldo());
            pstmUpdate.setLong(2, c.getNumero());
            return pstmUpdate.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean apagar(Conta c) {
        try {
            pstmDelete.setLong(1, c.getNumero());
            return pstmDelete.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}