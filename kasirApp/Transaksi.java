import java.util.ArrayList;

public class Transaksi {
    ArrayList<Produk> produkDibeli = new ArrayList<>();
    ArrayList<Integer> jumlahBeli = new ArrayList<>();
    double totalHarga;
    double uangBayar;
    double kembalian;

    public void tambahItem(Produk p, int jumlah) {
        produkDibeli.add(p);
        jumlahBeli.add(jumlah);
        totalHarga += p.harga * jumlah;
    }

    public void setPembayaran(double uangBayar) {
        this.uangBayar = uangBayar;
        this.kembalian = uangBayar - totalHarga;
    }

    public void tampilkanNota() {
        System.out.println("\n========== STRUK PEMBAYARAN ==========");
        for (int i = 0; i < produkDibeli.size(); i++) {
            Produk p = produkDibeli.get(i);
            int jumlah = jumlahBeli.get(i);
            double subtotal = p.harga * jumlah;
            System.out.println(p.nama + " x" + jumlah + " = Rp " + subtotal);
        }
        System.out.println("--------------------------------------");
        System.out.println("Total Harga   : Rp " + totalHarga);
        System.out.println("Uang Bayar    : Rp " + uangBayar);
        System.out.println("Kembalian     : Rp " + kembalian);
        System.out.println("======================================");
        System.out.println("  Terima kasih telah berbelanja!");
        System.out.println("======================================\n");
    }
}