package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cache implements PersonDatabase {
    private final HashMap<Integer, Person> idPersonHashMap;
    private final HashMap<String, List<Person>> namePersonHashMap;
    private final HashMap<String, List<Person>> addressPersonHashMap;
    private final HashMap<String, List<Person>> phonePersonHashMap;

    public Cache() {
        idPersonHashMap = new HashMap<>();
        namePersonHashMap = new HashMap<>();
        addressPersonHashMap = new HashMap<>();
        phonePersonHashMap = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        idPersonHashMap.put(person.id(), person);
        List<Person> namesList = namePersonHashMap.getOrDefault(person.name(), new ArrayList<>());
        List<Person> addressesList = addressPersonHashMap.getOrDefault(person.address(), new ArrayList<>());
        List<Person> phonesList = phonePersonHashMap.getOrDefault(person.phoneNumber(), new ArrayList<>());
        namesList.add(person);
        addressesList.add(person);
        phonesList.add(person);

        // if we got List as default we need to put it in HashMap
        if (namesList.size() == 1) {
            namePersonHashMap.put(person.name(), namesList);
        }
        if (addressesList.size() == 1) {
            addressPersonHashMap.put(person.name(), addressesList);
        }
        if (phonesList.size() == 1) {
            phonePersonHashMap.put(person.name(), phonesList);
        }

    }

    @Override
    public synchronized void delete(int id) {
        Person person = idPersonHashMap.get(id);
        if (person == null) {
            return;
        }
        idPersonHashMap.remove(person.id());
        namePersonHashMap.remove(person.name());
        addressPersonHashMap.remove(person.address());
        phonePersonHashMap.remove(person.phoneNumber());
    }

    @Override
    public List<Person> findByName(String name) {
        return namePersonHashMap.get(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return addressPersonHashMap.get(address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return phonePersonHashMap.get(phone);
    }
}
