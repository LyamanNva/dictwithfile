import java.util.*;

public class Dictionary {
    private final Map<String, String> words = new HashMap<>();
    private final String name;

    public Dictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addWord(String key, String value) {
        words.put(key, value);
    }

    public void removeWord(String key) {
        words.remove(key);
    }

    public void updateWord(String key, String newValue) {
        if (words.containsKey(key)) {
            words.put(key, newValue);
        } else {
            System.out.println("Söz tapılmadı!");
        }
    }

    public String searchWord(String key) {
        return words.getOrDefault(key, "Söz tapılmadı!");
    }

    public void displayWords() {
        if (words.isEmpty()) {
            System.out.println("Lüğət boşdur.");
        } else {
            words.forEach((key, value) -> System.out.println(key + " -> " + value));
        }
    }

    public Map<String, String> getWords() {
        return words;
    }
}



