public class Main {
    public static void main(String[] args) {
        DictionaryManager manager = new DictionaryManager();
        UserInterface ui = new UserInterface(manager);
        ui.run();
    }
}