package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;


public class Phonebook {
    private Map<String, PhoneNumber> phonebook = new TreeMap<>();
    private List<Pattern> patterns = new ArrayList<>();
    private int count = 0;

    public Map<String, PhoneNumber> getPhonebook() {
        return phonebook;
    }



    public List<Pattern> getPatterns() {
        return patterns;
    }



    public int getCount() {
        return count;
    }


    public Phonebook() {
        patterns.add(Pattern.compile("(\\+359)(87|88|89)([2-9])([0-9]{6})"));
        patterns.add(Pattern.compile("(0)(87|88|89)([2-9])([0-9]{6})"));
        patterns.add(Pattern.compile("(00359)(87|88|89)([2-9])([0-9]{6})"));
    }

    public boolean addNewPair(String name, String phone) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(phone);
            if (matcher.matches()) {
                if (!matcher.group(1).equals("+359")) {
                    phone = phone.replaceFirst(matcher.group(1), "+359");
                }
                phonebook.put(name, new PhoneNumber(phone));
                return true;
            }
        }
        return false;
    }

    public boolean deletePair(String name) {
        if (phonebook.containsKey(name)) {
            phonebook.remove(name);
            return true;
        } else {
            System.out.println("There is no match with  "+ name +"in the phonebook");
            return false;
        }
    }

    public String accessPhoneNumber(String name) {
        PhoneNumber phoneNumber = phonebook.get(name);
        if (phoneNumber == null) {
            return null;
        }
        phoneNumber.setCount(phoneNumber.getCount() + 1);
        count++;
        return phoneNumber.getPhoneNumber();
    }

    public void printAllSorted() {
        for (Map.Entry<String, PhoneNumber> entry : phonebook.entrySet()) {
            System.out.println("Name: " + entry.getKey()
                    + " Phone: " + entry.getValue().getPhoneNumber());
        }
        System.out.println("All outgoing calls: " + count);
    }

    public void readFromFile(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] pair = line.split(", ");
                    if (pair.length == 2) {
                        addNewPair(pair[0], pair[1]);
                    }else{
                        System.out.println("There is incorrect record!");
                    }
                } catch (PatternSyntaxException e) {
                    System.out.println("There is incorrect record!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTopFive() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>(phonebook.values());
        phoneNumbers.sort(PhoneNumber::compareTo);

        List<PhoneNumber> topFive = phoneNumbers.subList(Math.max(phoneNumbers.size() - 5, 0), phoneNumbers.size());
        System.out.println("\nFive pairs with biggest count of outgoing calls:\n");
        for (PhoneNumber phoneNumber : topFive) {
            System.out.printf("Name: %20s || Phone: %s || Count: %d\n" ,phonebook.entrySet()
                    .stream()
                    .filter(entry -> Objects.equals(entry.getValue(), phoneNumber))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList()).get(0) ,
                    phoneNumber.getPhoneNumber() , phoneNumber.getCount());
        }
    }
}