import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://db.abbcoxsnxzknxolbtssx.supabase.co:5432/postgres?user=postgres&password=59edEt9xopfDXk5o";

        try (Connection conn = ConnectionFactory.getConnection(url);
             Scanner sc = new Scanner(System.in)) {

            if (conn == null) {
                System.out.println("Não foi possível conectar ao banco.");
                return;
            }

            ContaDao dao = new ContaDao(conn);
            int opcao;

            do {
                System.out.println("\n===== MENU =====");
                System.out.println("1 - Listar todas as contas");
                System.out.println("2 - Buscar conta por número");
                System.out.println("3 - Criar nova conta");
                System.out.println("4 - Alterar saldo de uma conta");
                System.out.println("5 - Apagar uma conta");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");

                while (!sc.hasNextInt()) {
                    System.out.print("Digite um número válido: ");
                    sc.next();
                }
                opcao = sc.nextInt();
                sc.nextLine(); // consome quebra de linha

                switch (opcao) {
                    case 1 -> listarContas(dao);
                    case 2 -> buscarConta(dao, sc);
                    case 3 -> criarConta(dao, sc);
                    case 4 -> alterarSaldo(dao, sc);
                    case 5 -> apagarConta(dao, sc);
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            } while (opcao != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listarContas(ContaDao dao) throws Exception {
        List<Conta> contas = dao.lerTodas();
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada.");
        } else {
            for (Conta c : contas) {
                System.out.println("Número: " + c.getNumero() + " | Saldo: " + c.getSaldo());
            }
        }
    }

    private static void buscarConta(ContaDao dao, Scanner sc) throws Exception {
        System.out.print("Digite o número da conta: ");
        long numero = sc.nextLong();
        Conta c = dao.buscarPeloNumero(numero);
        if (c == null) {
            System.out.println("Conta não encontrada.");
        } else {
            System.out.println("Número: " + c.getNumero() + " | Saldo: " + c.getSaldo());
        }
    }

    private static void criarConta(ContaDao dao, Scanner sc) {
        System.out.print("Número da nova conta: ");
        long numero = sc.nextLong();
        System.out.print("Saldo inicial: ");
        BigDecimal saldo = sc.nextBigDecimal();
        boolean ok = dao.criar(new Conta(numero, saldo));
        System.out.println(ok ? "Conta criada com sucesso." : "Falha ao criar conta.");
    }

    private static void alterarSaldo(ContaDao dao, Scanner sc) {
        System.out.print("Número da conta: ");
        long numero = sc.nextLong();
        System.out.print("Novo saldo: ");
        BigDecimal saldo = sc.nextBigDecimal();
        boolean ok = dao.atualizar(new Conta(numero, saldo));
        System.out.println(ok ? "Saldo atualizado." : "Falha ao atualizar saldo.");
    }

    private static void apagarConta(ContaDao dao, Scanner sc) {
        System.out.print("Número da conta: ");
        long numero = sc.nextLong();
        boolean ok = dao.apagar(new Conta(numero, BigDecimal.ZERO));
        System.out.println(ok ? "Conta apagada." : "Falha ao apagar conta.");
    }
}