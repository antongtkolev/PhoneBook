import model.Phonebook;

public class Main {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.readFromFile("C:\\Users\\anton\\Desktop\\phoneBook.txt");
        phonebook.getTopFive();
    }


}
