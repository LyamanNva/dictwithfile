import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
        private final Map<String, Dictionary> dictionaries = new HashMap<>();
        private final String dictionaryFolder = "dictionaries";

        public DictionaryManager() {
            File folder = new File(dictionaryFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }
        }

        public void createDictionary(String name) {
            if (!dictionaries.containsKey(name)) {
                Dictionary dictionary = new Dictionary(name);
                dictionaries.put(name, dictionary);
                createDictionaryFile(name); // Fayl yaradılır
                System.out.println("Lüğət yaradıldı: " + name);
            } else {
                System.out.println("Bu adla lüğət artıq mövcuddur.");
            }
        }

        private void createDictionaryFile(String name) {
            File file = new File(dictionaryFolder + "/" + name + ".txt");
            try {
                if (file.createNewFile()) {
                    System.out.println("Lüğət faylı yaradıldı: " + file.getName());
                } else {
                    System.out.println("Lüğət faylı artıq mövcuddur.");
                }
            } catch (IOException e) {
                System.out.println("Fayl yaradılarkən xəta baş verdi.");
                e.printStackTrace();
            }
        }

        public Dictionary getDictionary(String name) {
            return dictionaries.get(name);
        }

        public void listDictionaries() {
            File folder = new File(dictionaryFolder);
            File[] files = folder.listFiles((dir, filename) -> filename.endsWith(".txt"));

            if (files != null && files.length > 0) {
                System.out.println("Mövcud lüğətlər:");
                for (File file : files) {
                    System.out.println(file.getName().replace(".txt", ""));
                }
            } else {
                System.out.println("Heç bir lüğət mövcud deyil.");
            }
        }

        public void saveDictionaryToFile(String dictionaryName) {
            Dictionary dictionary = dictionaries.get(dictionaryName);
            if (dictionary != null) {
                File file = new File(dictionaryFolder + "/" + dictionaryName + ".txt");
                try (FileWriter writer = new FileWriter(file)) {
                    for (Map.Entry<String, String> entry : dictionary.getWords().entrySet()) {
                        writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
                    }
                    System.out.println("Lüğət fayla yazıldı: " + dictionaryName);
                } catch (IOException e) {
                    System.out.println("Lüğət fayla yazılarkən xəta baş verdi.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Lüğət tapılmadı.");
            }
        }

    public void loadDictionaryFromFile(String dictionaryName) {
        File file = new File(dictionaryFolder + "/" + dictionaryName + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                Dictionary dictionary = new Dictionary(dictionaryName);
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        dictionary.addWord(parts[0], parts[1]);
                    }
                }
                dictionaries.put(dictionaryName, dictionary);
                System.out.println("Lüğət fayldan yükləndi: " + dictionaryName);
            } catch (IOException e) {
                System.out.println("Lüğət fayldan yüklənərkən xəta baş verdi.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Lüğət faylı tapılmadı.");
        }
    }
}
