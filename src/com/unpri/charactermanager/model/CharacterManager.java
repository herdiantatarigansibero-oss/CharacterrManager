package com.unpri.charactermanager.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Mengelola koleksi karakter dan menyediakan operasi CRUD dasar.
 */
public class CharacterManager {

    /** Batas maksimal jumlah karakter dalam daftar (satu sumber kebenaran). */
    public static final int MAX_CHARACTERS = 20;

    private final ArrayList<Character> characterList = new ArrayList<>();

    /**
     * Menambahkan karakter jika daftar belum penuh dan belum ada karakter
     * dengan nama yang sama (tanpa membedakan huruf besar/kecil).
     *
     * @param character karakter yang akan ditambahkan
     * @return true jika berhasil ditambahkan, false jika namanya duplikat
     * @throws NullPointerException     jika character null
     * @throws IllegalArgumentException jika daftar sudah mencapai MAX_CHARACTERS
     */
    public boolean addCharacter(Character character) {
        Objects.requireNonNull(character, "Karakter tidak boleh null");

        if (isFull()) {
            throw new IllegalArgumentException(
                "Daftar penuh: maksimal " + MAX_CHARACTERS + " karakter.");
        }

        if (isNameTaken(character.getName())) {
            System.out.println("Gagal: karakter dengan nama '" + character.getName() + "' sudah ada.");
            return false;
        }

        characterList.add(character);
        System.out.println("Karakter '" + character.getName() + "' berhasil ditambahkan.");
        return true;
    }

    /**
     * Memeriksa apakah daftar sudah mencapai batas maksimal.
     *
     * @return true jika jumlah karakter sudah MAX_CHARACTERS
     */
    public boolean isFull() {
        return characterList.size() >= MAX_CHARACTERS;
    }

    /**
     * Memeriksa apakah sudah ada karakter dengan nama yang sama
     * (tanpa membedakan huruf besar/kecil, sehingga "arjuna" dianggap sama dengan "Arjuna").
     *
     * @param name nama yang akan diperiksa
     * @return true jika nama sudah dipakai, false jika belum atau name null
     */
    public boolean isNameTaken(String name) {
        if (name == null) {
            return false;
        }
        for (Character character : characterList) {
            if (name.equalsIgnoreCase(character.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Mencari karakter berdasarkan nama secara case-sensitive.
     *
     * @param name nama yang dicari
     * @return karakter yang cocok, atau null jika tidak ditemukan
     */
    public Character findByName(String name) {
        for (Character character : characterList) {
            if (Objects.equals(character.getName(), name)) {
                return character;
            }
        }
        return null;
    }

    /**
     * Menghapus karakter berdasarkan nama.
     *
     * @param name nama karakter yang akan dihapus
     * @return true jika karakter ditemukan dan dihapus, false jika tidak ditemukan
     */
    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target == null) {
            return false;
        }
        return characterList.remove(target);
    }

    /**
     * Mengembalikan jumlah karakter yang tersimpan.
     *
     * @return jumlah karakter
     */
    public int getTotalCharacters() {
        return characterList.size();
    }

    /**
     * Menghitung rata-rata level karakter yang dikelola.
     *
     * @return rata-rata level, atau 0 jika belum ada karakter
     */
    public double getAverageLevel() {
        if (characterList.isEmpty()) {
            return 0;
        }

        int totalLevel = 0;
        for (Character character : characterList) {
            totalLevel += character.getLevel();
        }
        return (double) totalLevel / characterList.size();
    }

    /**
     * Mengembalikan karakter yang memiliki tipe tertentu.
     *
     * @param type tipe karakter yang dicari
     * @return list baru berisi semua karakter dengan tipe tersebut
     */
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> matchingCharacters = new ArrayList<>();
        for (Character character : characterList) {
            if (character.getType() == type) {
                matchingCharacters.add(character);
            }
        }
        return matchingCharacters;
    }

    /**
     * Menghitung karakter dengan tipe tertentu tanpa mengekspos list internal.
     *
     * @param type tipe karakter yang dihitung
     * @return jumlah karakter dengan tipe tersebut
     */
    public int countByType(CharacterType type) {
        int count = 0;
        for (Character character : characterList) {
            if (character.getType() == type) {
                count++;
            }
        }
        return count;
    }

    /**
     * Mengembalikan salinan list agar pemanggil tidak dapat mengubah struktur
     * koleksi internal secara langsung.
     *
     * @return salinan list karakter
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }
}