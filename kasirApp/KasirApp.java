import java.util.ArrayList;
import java.util.Scanner;

public class KasirApp {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Produk> daftarProduk = new ArrayList<>();
    private ArrayList<Transaksi> riwayatTransaksi = new ArrayList<>();
    private int nextId = 1;

    private final String username = "admin";
    private final String password = "pass";

    public void start() {
        tambahProdukDummy();
        login();

        int pilihan;
        do {
            tampilMenuUtama();
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> kelolaProduk();
                case 2 -> transaksiPenjualan();
                case 3 -> lihatRiwayatTransaksi();
                case 4 -> System.out.println("Terima kasih! Program selesai.");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 4);
    }

    // ============ DATA DUMMY PRODUK ============
    private void tambahProdukDummy() {
        daftarProduk.add(new Produk(nextId++, "Sabun Mandi", 20, 5000));
        daftarProduk.add(new Produk(nextId++, "Shampoo", 15, 10000));
        daftarProduk.add(new Produk(nextId++, "Pasta Gigi", 25, 8000));
        daftarProduk.add(new Produk(nextId++, "Tissue", 30, 6000));
        daftarProduk.add(new Produk(nextId++, "Sikat Gigi", 10, 4000));
    }

    // ============ LOGIN (bisa diulang sampai benar) ============
    private void login() {
        boolean berhasil = false;
        while (!berhasil) {
            System.out.println("=== LOGIN ===");
            System.out.print("Username: ");
            String user = input.nextLine();
            System.out.print("Password: ");
            String pass = input.nextLine();

            if (user.equals(username) && pass.equals(password)) {
                System.out.println("Login berhasil!\n");
                berhasil = true;
            } else {
                System.out.println("Username atau password salah! Silakan coba lagi.\n");
            }
        }
    }

    // ============ MENU UTAMA ============
    private void tampilMenuUtama() {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Kelola Data Produk");
        System.out.println("2. Proses Transaksi Penjualan");
        System.out.println("3. Lihat Riwayat Transaksi");
        System.out.println("4. Keluar");
    }

    // ==================== KELOLA PRODUK ====================
    private void kelolaProduk() {
        int pilihan;
        do {
            System.out.println("\n=== KELOLA DATA PRODUK ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Lihat Produk");
            System.out.println("3. Tambah Stok Produk");
            System.out.println("4. Hapus Produk");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> tambahProduk();
                case 2 -> lihatProduk();
                case 3 -> tambahStok();
                case 4 -> hapusProduk();
                case 5 -> System.out.println("Kembali ke menu utama...");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    private void tambahProduk() {
        System.out.print("Nama produk: ");
        String nama = input.nextLine();
        System.out.print("Stok: ");
        int stok = input.nextInt();
        System.out.print("Harga: ");
        double harga = input.nextDouble();
        input.nextLine();

        daftarProduk.add(new Produk(nextId++, nama, stok, harga));
        System.out.println("Produk berhasil ditambahkan!");
    }

    private void lihatProduk() {
        System.out.println("\n=== DAFTAR PRODUK ===");
        if (daftarProduk.isEmpty()) {
            System.out.println("Belum ada produk.");
        } else {
            for (Produk p : daftarProduk) {
                System.out.println(p.toString());
            }
        }
    }

    private void tambahStok() {
        lihatProduk();
        if (daftarProduk.isEmpty()) return;

        System.out.print("Masukkan ID produk: ");
        int id = input.nextInt();
        Produk p = cariProdukById(id);

        if (p != null) {
            System.out.print("Tambah stok: ");
            int tambah = input.nextInt();
            p.stok += tambah;
            System.out.println("Stok berhasil ditambahkan!");
        } else {
            System.out.println("Produk tidak ditemukan!");
        }
    }

    private void hapusProduk() {
        lihatProduk();
        if (daftarProduk.isEmpty()) return;

        System.out.print("Masukkan ID produk yang akan dihapus: ");
        int id = input.nextInt();
        Produk p = cariProdukById(id);

        if (p != null) {
            daftarProduk.remove(p);
            System.out.println("Produk berhasil dihapus!");
        } else {
            System.out.println("Produk tidak ditemukan!");
        }
    }

    private Produk cariProdukById(int id) {
        for (Produk p : daftarProduk) {
            if (p.id == id) return p;
        }
        return null;
    }

    // ==================== TRANSAKSI ====================
    private void transaksiPenjualan() {
        if (daftarProduk.isEmpty()) {
            System.out.println("Belum ada produk!");
            return;
        }

        Transaksi trx = new Transaksi();

        // tampilkan daftar produk hanya sekali
        System.out.println("\n=== DAFTAR PRODUK ===");
        for (Produk p : daftarProduk) {
            System.out.println(p.toString());
        }

        while (true) {
            System.out.print("\nMasukkan ID produk yang ingin dibeli (0 untuk selesai): ");
            int id = input.nextInt();
            if (id == 0) break;

            Produk p = cariProdukById(id);
            if (p == null) {
                System.out.println("Produk tidak ditemukan!");
                continue;
            }

            System.out.print("Jumlah beli: ");
            int jumlah = input.nextInt();

            if (jumlah > p.stok) {
                System.out.println("Stok tidak cukup!");
            } else {
                p.stok -= jumlah;
                trx.tambahItem(p, jumlah);
                System.out.println(p.nama + " berhasil ditambahkan ke transaksi!");
            }
        }

        if (trx.totalHarga == 0) {
            System.out.println("Tidak ada produk yang dibeli.");
            return;
        }

        System.out.println("\nTotal belanja: Rp " + trx.totalHarga);
        System.out.print("Masukkan uang bayar: ");
        double bayar = input.nextDouble();

        if (bayar < trx.totalHarga) {
            System.out.println("Uang tidak cukup! Transaksi dibatalkan.");
            return;
        }

        trx.setPembayaran(bayar);
        riwayatTransaksi.add(trx);
        trx.tampilkanNota();
    }

    // ==================== RIWAYAT TRANSAKSI ====================
    private void lihatRiwayatTransaksi() {
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");
        if (riwayatTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            int i = 1;
            for (Transaksi t : riwayatTransaksi) {
                System.out.println("Transaksi #" + (i++));
                t.tampilkanNota();
            }
        }
    }
}