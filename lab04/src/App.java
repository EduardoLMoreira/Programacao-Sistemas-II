import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db.abbcoxsnxzknxolbtssx.supabase.co:5432/postgres?user=postgres&password=59edEt9xopfDXk5o";

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contas");

            while (rs.next()) {
                System.out.println(rs.getInt("nro_conta") + " - " +
                                   rs.getString("nome") + " - " +
                                   rs.getDouble("saldo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}