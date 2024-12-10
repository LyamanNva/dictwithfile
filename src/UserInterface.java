import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final DictionaryManager manager;

    public UserInterface(DictionaryManager manager) {
        this.manager = manager;
    }

    public void run() {
        while (true) {
            System.out.println("\n1. Admin daxil ol");
            System.out.println("2. İstifadəçi daxil ol");
            System.out.println("3. Çıxış");
            System.out.print("Seçim edin: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> userMenu();
                case 3 -> {
                    System.out.println("Proqramdan çıxılır.");
                    return;
                }
                default -> System.out.println("Yanlış seçim!");
            }
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menyusu:");
            System.out.println("1. Yeni lüğət yarat");
            System.out.println("2. Lüğətə söz əlavə et");
            System.out.println("3. Lüğətdən söz sil");
            System.out.println("4. Lüğətdə sözü dəyiş");
            System.out.println("5. Lüğəti fayla yaz");
            System.out.println("6. Lüğəti fayldan yüklə");
            System.out.println("7. Əsas menyuya qayıt");
            System.out.print("Seçim edin: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Yeni lüğətin adı: ");
                    String name = scanner.nextLine();
                    manager.createDictionary(name);
                }
                case 2 -> {
                    System.out.print("Lüğətin adı: ");
                    String dictName = scanner.nextLine();
                    Dictionary dictionary = manager.getDictionary(dictName);
                    if (dictionary != null) {
                        System.out.print("Söz (açar): ");
                        String key = scanner.nextLine();
                        System.out.print("Tərcümə (qiymət): ");
                        String value = scanner.nextLine();
                        dictionary.addWord(key, value);
                        System.out.println("Söz əlavə edildi.");
                    } else {
                        System.out.println("Lüğət tapılmadı.");
                    }
                }
                case 3 -> {
                    System.out.print("Lüğətin adı: ");
                    String dictName = scanner.nextLine();
                    Dictionary dictionary = manager.getDictionary(dictName);
                    if (dictionary != null) {
                        System.out.print("Silmək üçün söz: ");
                        String key = scanner.nextLine();
                        dictionary.removeWord(key);
                        System.out.println("Söz silindi.");
                    } else {
                        System.out.println("Lüğət tapılmadı.");
                    }
                }
                case 4 -> {
                    System.out.print("Lüğətin adı: ");
                    String dictName = scanner.nextLine();
                    Dictionary dictionary = manager.getDictionary(dictName);
                    if (dictionary != null) {
                        System.out.print("Dəyişmək üçün söz: ");
                        String key = scanner.nextLine();
                        System.out.print("Yeni tərcümə: ");
                        String newValue = scanner.nextLine();
                        dictionary.updateWord(key, newValue);
                        System.out.println("Söz dəyişdirildi.");
                    } else {
                        System.out.println("Lüğət tapılmadı.");
                    }
                }
                case 5 -> {
                    System.out.print("Lüğətin adı: ");
                    String dictName = scanner.nextLine();
                    manager.saveDictionaryToFile(dictName);
                }
                case 6 -> {
                    System.out.print("Lüğətin adı: ");
                    String dictName = scanner.nextLine();
                    manager.loadDictionaryFromFile(dictName);
                }
                case 7 -> {
                    System.out.println("Əsas menyuya qayıdılır.");
                    return;
                }
                default -> System.out.println("Yanlış seçim!");
            }
        }
    }

    private void userMenu() {
        while (true) {
            System.out.println("\nİstifadəçi Menyusu:");
            System.out.println("1. Cari lüğətləri gör");
            System.out.println("2. Lüğətə görə söz axtar");
            System.out.println("3. Əsas menyuya qayıt");
            System.out.print("Seçim edin: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manager.listDictionaries();
                case 2 -> {
                    System.out.print("Lüğətin adı: ");
                    String dictName = scanner.nextLine();
                    Dictionary dictionary = manager.getDictionary(dictName);
                    if (dictionary != null) {
                        System.out.print("Axtarış üçün söz: ");
                        String key = scanner.nextLine();
                        System.out.println("Tərcümə: " + dictionary.searchWord(key));
                    } else {
                        System.out.println("Lüğət tapılmadı.");
                    }
                }
                case 3 -> {
                    System.out.println("Əsas menyuya qayıdılır.");
                    return;
                }
                default -> System.out.println("Yanlış seçim!");
            }
        }
    }
}

