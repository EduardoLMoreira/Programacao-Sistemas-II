import java.sql.*;

public class UpdateConta {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db.abbcoxsnxzknxolbtssx.supabase.co:5432/postgres?user=postgres&password=59edEt9xopfDXk5o";

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "UPDATE contas SET saldo = ? WHERE nro_conta = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, 5000.00);
            stmt.setInt(2, 127);
            stmt.executeUpdate();
            System.out.println("Conta atualizada!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}