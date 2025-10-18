public class Produk {
    int id;
    String nama;
    int stok;
    double harga;

    public Produk(int id, String nama, int stok, double harga) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    public String toString() {
        return "ID: " + id + " | " + nama + " | Stok: " + stok + " | Harga: Rp " + harga;
    }
}