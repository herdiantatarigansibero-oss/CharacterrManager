# Character Manager — TP3 & TP5 (Java Visual SIH-6406)

Project Java sederhana untuk mengelola karakter menggunakan class dan enum dalam package modular. Program mendemonstrasikan encapsulation, perhitungan statistik berdasarkan level, serta fitur mana dan status hidup karakter.

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
│                   └── MainWindow.java
└── README.md
```

## Identitas

- **Nama:** HERDIANTA YUVENTUS TARIGAN
- **NIM:** 253303621206
- **Google Docs Bagian D:** [JavaVisual_TP05_253303621206_HERDIANTA_YUVENTUS_TARIGAN](https://docs.google.com/document/d/1PoDj3Y5tiwgDwD7GP85kalqRDI7SEs2B9YtGIYjkgQs/edit?tab=t.0)
