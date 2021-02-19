package tugas2.models;

public class Buku {
	private long id_buku;
	private String judulBuku;
	private String pengarang;

	public long getId() {
		return id_buku;
	}

	public void setId(long id) {
		this.id_buku = id;
	}

	public String getJudulBuku() {
		return judulBuku;
	}

	public void setJudulBuku(String judulBuku) {
		this.judulBuku = judulBuku;
	}

	public String getPengarang() {
		return pengarang;
	}

	public void setPengarang(String pengarang) {
		this.pengarang = pengarang;
	}
}
