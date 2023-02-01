package dealermobil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Kendaraan {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/dealermobil";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    
    public static void main(String[] args) {

        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Mobil ");
        System.out.println("2. Show Mobil");
        System.out.println("3. Edit Mobil");
        System.out.println("4. Delete Mobil");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertMobil();
                    break;
                case 2:
                    showMobil();
                    break;
                case 3:
                    updateMobil();
                    break;
                case 4:
                    deleteMobil();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showMobil() {
        String sql = "SELECT * FROM kendaraan";

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("+--------------------------------+");
            System.out.println("|          DATA KENDARAAN        |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                int kode_mobil    = rs.getInt("kode_mobil");
                String merk_mobil = rs.getString("merk_mobil");
                String jumlah_mobil = rs.getString("jumlah_mobil");
                String tahun_keluar = rs.getString("tahun_keluar");
                String keterangan = rs.getString("keterangan");

                
                System.out.println(String.format("%d, %s, %s, %s, %s)", kode_mobil, merk_mobil, jumlah_mobil,tahun_keluar, keterangan));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void insertMobil() {
        try {
            // ambil input dari user
            System.out.print("kode_mobil: ");
            String kode_mobil  = input.readLine().trim();
            System.out.print("merk_mobil: ");
            String merk_mobil = input.readLine().trim();
            System.out.print("jumlah_mobil: ");
            String jumlah_mobil = input.readLine().trim();
            System.out.print("tahun_keluar: ");
            String tahun_keluar = input.readLine().trim();
            System.out.print("keterangan: ");
            String keterangan = input.readLine().trim();
 
            // query simpan
            String sql = "INSERT INTO kendaraan (kode_mobil, merk_mobil, tahun_keluar, jumlah_mobil, keterangan) VALUE('%s', '%s', '%s', '%s', '%s')";
            sql = String.format(sql, kode_mobil, merk_mobil, jumlah_mobil, tahun_keluar, keterangan);

            // simpan buku
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void updateMobil() {
        try {
            
            // ambil input dari user
            System.out.print("Kode Mobil yang mau di edit: ");
            int kodeMobil = Integer.parseInt(input.readLine());
            System.out.print("kode_mobil: ");
            String kode_mobil = input.readLine().trim();
            System.out.print("merk_mobil: ");
            String merk_mobil = input.readLine().trim();
            System.out.print("jumlah_mobil: ");
            String jumlah_mobil = input.readLine().trim();
            System.out.print("tahun_keluar: ");
            String tahun_keluar = input.readLine().trim();
            System.out.print("keterangan: ");
            String keterangan = input.readLine().trim();

            // query update
            String sql = "UPDATE kendaraan  SET merk_mobil='%s', jumlah_mobil='%s', tahun_keluar='%s', keterangan='%s' WHERE kode_mobil=%d";
            sql = String.format(sql, merk_mobil, jumlah_mobil, tahun_keluar, keterangan, kodeMobil);

            // update data mobil
            stmt.execute(sql); 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteMobil() {
        try {
            
            // ambil input dari user
            System.out.print("Kode Mobil yang mau dihapus: ");
            int kodeMobil = Integer.parseInt(input.readLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM kendaraan WHERE kode_mobil=%d", kodeMobil);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}