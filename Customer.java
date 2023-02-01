import java.sql.*;

public class Customer {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/dealermobil";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT NIK_Customer, Nama_Customer, Kode_Mobil, No_Telepon, Alamat, id_pegawai FROM customer";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int NIK_Customer = rs.getInt("NIK_Customer");
                String Nama_Customer = rs.getString("Nama_Customer");
                int Kode_Mobil = rs.getInt("Kode_Mobil");
                String No_Telepon = rs.getString("No_Telepon");
                String Alamat = rs.getString("Alamat");
                int id_pegawai = rs.getInt("id_pegawai");
                System.out.print("NIK Customer: " + NIK_Customer);
                System.out.print(", Nama Customer: " + Nama_Customer);
                System.out.print(", Kode Mobil: " + Kode_Mobil);
                System.out.print(", No Telepon: " + No_Telepon);
                System.out.print(", Alamat: " + Alamat);
                System.out.println(", ID Pegawai: " + id_pegawai);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
******************