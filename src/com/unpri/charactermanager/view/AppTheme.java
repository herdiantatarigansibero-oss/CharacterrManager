package com.unpri.charactermanager.view; // package untuk komponen tampilan

import com.unpri.charactermanager.model.CharacterType;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Pusat gaya tampilan Character Manager.
 * Mengubah tema aplikasi cukup dengan mengedit file ini.
 */
public final class AppTheme {

    public static final int HIGH_LEVEL_THRESHOLD = 5; // level minimum untuk aksen karakter kuat

    // ---------- Palet warna ----------
    public static final Color BG_MAIN = Color.decode("#13131f");
    public static final Color BG_STATUS = Color.decode("#0d0d1a");
    public static final Color BG_INPUT = Color.decode("#1e1e30"); // latar input form
    public static final Color TEXT_MAIN = Color.decode("#dfe6e9");
    public static final Color TEXT_MUTED = Color.decode("#9d9dbf");
    public static final Color TEXT_LIGHT = Color.WHITE;
    public static final Color GOLD = Color.decode("#fdcb6e");
    public static final Color ERROR = Color.decode("#ff7675");   // merah terang: terbaca di latar gelap
    public static final Color SUCCESS = Color.decode("#55efc4"); // hijau terang: terbaca di latar gelap

    // ---------- Font ----------
    public static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 22);
    public static final Font FONT_BODY = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FONT_BODY_BOLD = new Font("SansSerif", Font.BOLD, 14);
    public static final Font FONT_MONO = new Font("Monospaced", Font.PLAIN, 13);

    // ---------- Border ----------
    public static final Border PANEL_BORDER = BorderFactory.createEmptyBorder(16, 16, 16, 16);
    public static final Border CARD_BORDER = BorderFactory.createEmptyBorder(8, 12, 8, 12);

    private AppTheme() { }

    /** Menerjemahkan role (data Model) menjadi warna kartu (urusan View). */
    public static Color colorFor(CharacterType type) {
        switch (type) {
            case WARRIOR: return Color.decode("#d63031");
            case MAGE: return Color.decode("#0984e3");
            case ARCHER: return Color.decode("#00b894");
            default: return Color.GRAY;
        }
    }

    /** Teks tooltip per role. */
    public static String describe(CharacterType type) {
        switch (type) {
            case WARRIOR: return "Warrior: jarak dekat, HP tinggi";
            case MAGE: return "Mage: serangan sihir, HP rendah";
            case ARCHER: return "Archer: jarak jauh, seimbang";
            default: return "Role belum terdaftar";
        }
    }
}