import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Consulta de contas!");
        String url = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?user=postgres.zuwmidevfxstazryemej&password=j37eg0qww2e";
        Connection c = DriverManager.getConnection(url);
        System.out.println("Conexão ok!");
        String sql = "SELECT * FROM CONTAS";
        PreparedStatement stm = c.prepareStatement(sql);
        ResultSet resultado = stm.executeQuery();
        while (resultado.next()) {
            long nro = resultado.getLong("nro_conta");
            double saldo = resultado.getDouble("saldo");
            System.out.println("Número: " + nro + " - R$ " + saldo);
        }
        c.close();
    }
}