import java.sql.*;

public class Pegawai {
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
            sql = "SELECT id_pegawai, nama_pegawai, jabatan, no_telepon, alamat FROM pegawai";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id_pegawai = rs.getInt("id_pegawai");
                String nama_pegawai = rs.getString("nama_pegawai");
                String jabatan = rs.getString("jabatan");
                String no_telepon = rs.getString("no_telepon");
                String alamat = rs.getString("alamat");
                System.out.print("ID Pegawai: " + id_pegawai);
                System.out.print(", Nama Pegawai: " + nama_pegawai);
                System.out.print(", Jabatan: " + jabatan);
                System.out.print(", No Telepon: " + no_telepon);
                System.out.println(", Alamat: " + alamat);
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
