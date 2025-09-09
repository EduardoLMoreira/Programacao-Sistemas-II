import java.sql.*;

public class DeleteConta {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db.abbcoxsnxzknxolbtssx.supabase.co:5432/postgres?user=postgres&password=59edEt9xopfDXk5o";

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "DELETE FROM contas WHERE nro_conta = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 127);
            stmt.executeUpdate();
            System.out.println("Conta removida!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}