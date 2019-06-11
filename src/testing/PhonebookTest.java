package testing;

import model.Phonebook;

import static org.junit.jupiter.api.Assertions.*;

class PhonebookTest {

    Phonebook phonebook = new Phonebook();

    @org.junit.jupiter.api.Test
    void addNewPair() {
       assertTrue(phonebook.addNewPair("Mariqn Koichev","0897557889"),
               "The input number is incorrect!");

    }

    @org.junit.jupiter.api.Test
    void deletePair() {
        phonebook.addNewPair("Anton Kolev","00359887676869");
        assertTrue(phonebook.deletePair("Anton Kolev"),
                "Can not delete number!");
    }

    @org.junit.jupiter.api.Test
    void accessPhoneNumber() {
        phonebook.addNewPair("Marin","0887667434");
        assertNotEquals(phonebook.accessPhoneNumber("Marin"),null,
                "There is no match with this name in the phonebook !");
    }

    @org.junit.jupiter.api.Test
    void printAllSorted() {

    }

    @org.junit.jupiter.api.Test
    void readFromFile() {
        phonebook.readFromFile("PhoneBookTest.txt");
    }

    @org.junit.jupiter.api.Test
    void getTopFive() {


    }
}