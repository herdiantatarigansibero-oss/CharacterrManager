package com.unpri.charactermanager.model;

import java.util.Objects;

/**
 * Merepresentasikan satu karakter dalam aplikasi Character Manager.
 * Menerapkan prinsip Encapsulation: semua atribut private,
 * akses hanya melalui getter/setter. Semua aturan domain dijaga di sini,
 * sehingga tetap berlaku walaupun View dilewati.
 */
public class Character {

    // ===== KONSTANTA ATURAN (satu sumber kebenaran) =====
    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 99;
    public static final int MAX_MAGE_LEVEL = 60;

    // ===== ATRIBUT =====
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    // ===== CONSTRUCTOR 1: karakter BARU dari form =====
    public Character(String name, CharacterType type, int level) {
        validateName(name);
        validateType(type);
        validateLevel(level);
        validateTypeAndLevel(type, level);   // aturan lintas-field

        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    // ===== CONSTRUCTOR 2: MEMUAT karakter dari database =====
    // Sengaja tidak divalidasi agar satu baris DB lama yang tidak valid
    // tidak menggagalkan pemuatan seluruh daftar.
    public Character(int id, String name, CharacterType type, int level, int hitPoints, int attackPower) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
    }

    // ===== VALIDASI (private static: dipakai konstruktor dan setter) =====
    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
    }

    private static void validateType(CharacterType type) {
        if (type == null) {
            throw new IllegalArgumentException("Role wajib dipilih");
        }
    }

    private static void validateLevel(int level) {
        if (level < MIN_LEVEL || level > MAX_LEVEL) {
            throw new IllegalArgumentException("Level harus " + MIN_LEVEL + "-" + MAX_LEVEL);
        }
    }

    // Aturan yang bergantung pada dua field sekaligus
    private static void validateTypeAndLevel(CharacterType type, int level) {
        if (type == CharacterType.MAGE && level > MAX_MAGE_LEVEL) {
            throw new IllegalArgumentException(
                "Karakter MAGE tidak boleh melebihi level " + MAX_MAGE_LEVEL + ".");
        }
    }

    // ===== HELPER PRIVATE =====
    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    // ===== GETTER =====
    public int getId() { return id; }
    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHitPoints() { return hitPoints; }
    public int getAttackPower() { return attackPower; }
    public int getMana() { return mana; }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    // ===== SETTER dengan VALIDASI =====
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name.trim();
    }

    public void setType(CharacterType type) {
        validateType(type);
        validateTypeAndLevel(type, this.level);   // cegah ganti ke MAGE saat level > 60
        this.type = type;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh negatif");
        }
        this.mana = mana;
    }

    public void setLevel(int level) {
        validateLevel(level);
        validateTypeAndLevel(this.type, level);   // cegah MAGE naik di atas 60
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    // ===== toString / equals / hashCode =====
    @Override
    public String toString() {
        return "Character{id=" + id +
               ", name='" + name + '\'' +
               ", type=" + type +
               ", level=" + level +
               ", hp=" + hitPoints +
               ", atk=" + attackPower +
               ", mana=" + mana + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Character)) {
            return false;
        }
        Character other = (Character) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}