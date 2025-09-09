import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Listar contas");
            System.out.println("2 - Criar conta");
            System.out.println("3 - Atualizar conta");
            System.out.println("4 - Apagar conta");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> read();
                case 2 -> create();
                case 3 -> update();
                case 4 -> delete();
                case 0 -> {
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public static void read() throws SQLException {
        String url = System.getenv("URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        Connection c = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM contas";
        PreparedStatement stm = c.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            long nro = rs.getLong("nro_conta");
            BigDecimal saldo = rs.getBigDecimal("saldo");
            String nome = rs.getString("nome");
            System.out.println("Conta número: " + nro + " | Titular: " + nome + " | Saldo: R$ " + saldo);
        }

        c.close();
    }

    public static void create() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número da nova conta: ");
        long nro = Long.parseLong(sc.nextLine());

        System.out.print("Nome do titular: ");
        String nome = sc.nextLine();

        System.out.print("Saldo inicial: ");
        BigDecimal saldo = new BigDecimal(sc.nextLine());

        String url = System.getenv("URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        Connection c = DriverManager.getConnection(url, username, password);
        String sql = "INSERT INTO contas (nro_conta, nome, saldo) VALUES (?, ?, ?)";
        PreparedStatement prepstm = c.prepareStatement(sql);
        prepstm.setLong(1, nro);
        prepstm.setString(2, nome);
        prepstm.setBigDecimal(3, saldo);

        int ret = prepstm.executeUpdate();
        System.out.println("Número de registros inseridos: " + ret);

        c.close();
    }

    public static void update() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número da conta existente: ");
        long nro = Long.parseLong(sc.nextLine());

        System.out.print("Novo saldo: ");
        BigDecimal saldo = new BigDecimal(sc.nextLine());

        String url = System.getenv("URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        Connection c = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE contas SET saldo = ? WHERE nro_conta = ?";
        PreparedStatement prepstm = c.prepareStatement(sql);
        prepstm.setBigDecimal(1, saldo);
        prepstm.setLong(2, nro);

        int ret = prepstm.executeUpdate();
        System.out.println("Número de registros alterados: " + ret);

        c.close();
    }

    public static void delete() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número da conta a apagar: ");
        long nro = Long.parseLong(sc.nextLine());

        String url = System.getenv("URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        Connection c = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM contas WHERE nro_conta = ?";
        PreparedStatement prepstm = c.prepareStatement(sql);
        prepstm.setLong(1, nro);

        int ret = prepstm.executeUpdate();
        System.out.println("Número de registros apagados: " + ret);

        c.close();
    }
}