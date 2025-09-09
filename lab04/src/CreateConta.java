import java.sql.*;

public class CreateConta {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db.abbcoxsnxzknxolbtssx.supabase.co:5432/postgres?user=postgres&password=59edEt9xopfDXk5o";

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO contas (nro_conta, nome, saldo) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 127);
            stmt.setString(2, "Carlos Pereira");
            stmt.setDouble(3, 1500.00);
            stmt.executeUpdate();
            System.out.println("Conta criada!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}