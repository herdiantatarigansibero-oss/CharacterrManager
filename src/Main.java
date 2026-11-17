import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Character Manager — Core CRUD Test ===");

        CharacterManager manager = new CharacterManager();
        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4));

        System.out.println("\n--- Uji duplikat dan equals() ---");
        Character arjunaDuplikat = new Character("Arjuna", CharacterType.HEALER, 1);
        System.out.println("equals() untuk nama Arjuna yang sama: "
                + manager.findByName("Arjuna").equals(arjunaDuplikat));
        System.out.println("Penambahan duplikat berhasil: " + manager.addCharacter(arjunaDuplikat));
        System.out.println("Total karakter setelah uji duplikat: " + manager.getTotalCharacters());

        System.out.println("\n--- Uji pencarian ---");
        Character ditemukan = manager.findByName("Merlin");
        System.out.println(ditemukan != null ? "Ditemukan: " + ditemukan : "Merlin tidak ditemukan.");
        System.out.println("Pencarian Zeus menghasilkan null: " + (manager.findByName("Zeus") == null));

        System.out.println("\n--- Mini-Challenge: Statistik dan Pencarian Tipe ---");
        System.out.println("Rata-rata level: " + manager.getAverageLevel());
        ArrayList<Character> warriors = manager.findByType(CharacterType.WARRIOR);
        System.out.println("Jumlah karakter bertipe WARRIOR: " + warriors.size());
        for (Character warrior : warriors) {
            System.out.println(warrior);
        }
        System.out.println("countByType(WARRIOR): "
                + manager.countByType(CharacterType.WARRIOR));
        CharacterManager managerKosong = new CharacterManager();
        System.out.println("Rata-rata level manager kosong: " + managerKosong.getAverageLevel());

        System.out.println("\n--- Uji penghapusan ---");
        System.out.println("Athena berhasil dihapus: " + manager.removeCharacter("Athena"));
        System.out.println("Total setelah penghapusan: " + manager.getTotalCharacters());

        System.out.println("\n--- Uji salinan getAllCharacters() ---");
        ArrayList<Character> salinanList = manager.getAllCharacters();
        salinanList.add(new Character("Hantu", CharacterType.MAGE, 1));
        System.out.println("Total pada manager: " + manager.getTotalCharacters());
        System.out.println("Total pada salinan list: " + salinanList.size());

        System.out.println("\n--- Eksperimen Object Reference ---");
        Character asli = manager.findByName("Arjuna");
        Character duplikatReferensi = asli;
        System.out.println("Level Arjuna sebelum: " + asli.getLevel());
        duplikatReferensi.setLevel(99);
        System.out.println("Level Arjuna sesudah melalui reference lain: " + asli.getLevel());
        System.out.println("asli == duplikatReferensi: " + (asli == duplikatReferensi));
    }
}
