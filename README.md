# Character Manager — TP3, TP5, TP8 & TP10 (Java Visual SIH-6406)

Project Java sederhana untuk mengelola karakter menggunakan class dan enum dalam package modular. Program mendemonstrasikan encapsulation, perhitungan statistik berdasarkan level, serta fitur mana dan status hidup karakter.

## TP10 — Tema dan Kartu Karakter

### Screenshot sebelum dan sesudah

![Tampilan sebelum tema TP10](Sebelum_Tp10.png)

![Tampilan sesudah tema TP10](Sesudsh_Tp10.png)

### Pemisahan Tema

Warna disimpan di `AppTheme` agar seluruh tampilan memakai palet yang konsisten dan perubahan tema dapat dilakukan di satu tempat. `Character` adalah bagian Model yang menyimpan data serta aturan karakter, bukan detail presentasi seperti warna layar. Pemisahan ini menjaga Model tetap dapat digunakan tanpa Swing dan mencegah logika tampilan tersebar ke dalam data domain.

### Properti tambahan

- **Tooltip berisi deskripsi role, HP, dan ATK:** memberi detail tambahan saat pengguna mengarahkan kursor tanpa membuat kartu utama lebih padat.
- **Kartu level 1 dinonaktifkan:** menandai karakter pemula yang belum aktif dan membedakannya secara visual dari karakter siap pakai.

### Analisis Bagian D

Dokumen [JavaVisual_TP10_253303621206_HERDIANTA_YUVENTUS_TARIGAN](https://docs.google.com/document/d/1tpQgNeF5r8ub3siiprB1SKBX33Y6sMeUKBOOsReEV6U/edit?tab=t.0) memuat analisis lebih dari 500 kata tentang pola JavaBeans, JLabel opaque, ukuran dan layout Swing, pemisahan warna menurut MVC, serta logical font dan `deriveFont()`.

## TP8 — GUI dan Aliran Data

### Sebelum Perubahan

Berikut tampilan sebelum daftar karakter dinamis dan statistik rata-rata ditambahkan:

![MainWindow sebelum perubahan TP8](Sebelum_Tp8.png)

### Sesudah Perubahan

Screenshot berikut menunjukkan MainWindow dengan lima karakter awal sebelum Naya ditambahkan:

![MainWindow TP8 sebelum penambahan Naya](Sesudah_TP8.png)

### Aliran Data dan MVC

`Main` menyiapkan data karakter melalui `CharacterManager`, yang bertindak sebagai bagian Model untuk mengelola koleksi. `Main` lalu mengambil salinan koleksi dengan `getAllCharacters()` dan meneruskannya ke constructor `MainWindow`. `MainWindow` berperan sebagai View: ia mengiterasi data tersebut untuk menampilkan lima karakter dan menghitung rata-rata level. Dengan demikian, pengelolaan data berada di Model, sedangkan penyajian antarmuka berada di View dan `Main` mengoordinasikan keduanya.

### Karakter Tambahan

- **Brisa — HEALER, level 6:** dipilih sebagai karakter pendukung dengan level lebih tinggi agar variasi tipe dan statistik di daftar lebih terlihat.
- **Raka — WARRIOR, level 2:** dipilih sebagai petarung pemula; levelnya yang rendah memberi kontras dengan karakter level tinggi seperti Brisa.
- **Naya — ARCHER, level 1:** dipilih sebagai pemanah pemula, sekaligus memperlihatkan perlakuan khusus untuk karakter level 1.

### Mini-Challenge dan Properti Tambahan

Karakter dengan level minimal `AppTheme.HIGH_LEVEL_THRESHOLD` (5) ditampilkan dengan teks emas dan tebal; batasnya disimpan sebagai konstanta bernama agar mudah disesuaikan. Kartu juga memiliki dua properti tambahan: tooltip menampilkan deskripsi role beserta HP/ATK agar detail dapat dibaca tanpa memenuhi tampilan utama, dan kartu level 1 dinonaktifkan secara visual untuk menandai karakter pemula yang belum aktif.

### Google Docs Bagian D — TP08

Analisis GUI dan MVC tersedia di [JavaVisual_TP08_253303621206_HERDIANTA_YUVENTUS_TARIGAN](https://docs.google.com/document/d/1lyS4s3MnoMAN-d1N3u7ndP_sj4a1jUguXlR-1vEaxYA/edit?tab=t.0). Dokumen dibagikan dengan akses **Anyone with the link — Viewer**.

## TP5 — Fitur dan Bukti

TP5 menambahkan pengelolaan koleksi karakter dan analitik:

- `Character.equals()` membandingkan nama secara case-sensitive; `hashCode()` mengikuti atribut nama.
- `CharacterManager` menyediakan tambah, cari, hapus, hitung total, dan `getAllCharacters()` yang mengembalikan defensive copy.
- `Main` menguji penolakan nama duplikat, CRUD dasar, rata-rata level (termasuk manager kosong), pencarian berdasarkan tipe, dan eksperimen reference/list salinan.
- `getAverageLevel()` menghindari pembagian nol dan mengembalikan `0` saat koleksi kosong.
- `findByType(CharacterType type)` mengembalikan list baru berisi karakter bertipe yang diminta.

### Bukti Output CRUD dan Eksperimen Reference

Screenshot berikut menunjukkan output program, termasuk penghapusan, statistik, dan eksperimen reference:

![Output TP5 CRUD dan eksperimen reference](Bukti_Tp5.png)

### Fitur Analitik Bagian C

`CharacterManager.countByType(CharacterType type)` menghitung jumlah karakter untuk tipe yang diminta dan hanya mengembalikan `int`, bukan `ArrayList` internal. Ini menjaga encapsulation: pemanggil dapat membaca ringkasan tanpa memperoleh akses untuk mengubah koleksi manager. Method memiliki Javadoc, dan `Main` mencetak jumlah karakter `WARRIOR`.

### Analisis Bagian D

Analisis reference, primitive value, `static`, `equals()`, dan defensive copy tersedia di [Google Docs TP05 — JavaVisual_TP05_253303621206_HERDIANTA_YUVENTUS_TARIGAN](https://docs.google.com/document/d/1PoDj3Y5tiwgDwD7GP85kalqRDI7SEs2B9YtGIYjkgQs/edit?tab=t.0).

## Bukti Output TP3

Screenshot berikut menunjukkan output program TP3 di terminal, termasuk hasil pemeriksaan `isAlive()`:

![Screenshot output terminal Character Manager](screenshots/terminal-output.png)

## Struktur Project

```text
CharacterrManager/
├── screenshots/
│   └── terminal-output.png
├── Bukti_Tp5.png
├── Sesudah_TP8.png
├── Sebelum_Tp10.png
├── Sesudsh_Tp10.png
├── src/
│   ├── Main.java
│   └── com/
│       └── unpri/
│           └── charactermanager/
│               ├── model/
│               │   ├── Character.java
│               │   ├── CharacterManager.java
│               │   └── CharacterType.java
│               └── view/
│                   ├── AppTheme.java
│                   └── MainWindow.java
└── README.md
```

## Identitas

- **Nama:** HERDIANTA YUVENTUS TARIGAN
- **NIM:** 253303621206
- **Google Docs Bagian D TP5:** [JavaVisual_TP05_253303621206_HERDIANTA_YUVENTUS_TARIGAN](https://docs.google.com/document/d/1PoDj3Y5tiwgDwD7GP85kalqRDI7SEs2B9YtGIYjkgQs/edit?tab=t.0)
- **Google Docs Bagian D TP8:** [JavaVisual_TP08_253303621206_HERDIANTA_YUVENTUS_TARIGAN](https://docs.google.com/document/d/1lyS4s3MnoMAN-d1N3u7ndP_sj4a1jUguXlR-1vEaxYA/edit?tab=t.0)
- **Google Docs Bagian D TP10:** [JavaVisual_TP10_253303621206_HERDIANTA_YUVENTUS_TARIGAN](https://docs.google.com/document/d/1tpQgNeF5r8ub3siiprB1SKBX33Y6sMeUKBOOsReEV6U/edit?tab=t.0)
