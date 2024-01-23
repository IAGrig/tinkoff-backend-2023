package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache implements PersonDatabase {
    private final Map<Integer, Person> idPersonMap;
    private final Map<String, List<Person>> namePersonMap;
    private final Map<String, List<Person>> addressPersonMap;
    private final Map<String, List<Person>> phonePersonMap;

    public Cache() {
        idPersonMap = new HashMap<>();
        namePersonMap = new HashMap<>();
        addressPersonMap = new HashMap<>();
        phonePersonMap = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        idPersonMap.put(person.id(), person);
        List<Person> namesList = namePersonMap.getOrDefault(person.name(), new ArrayList<>());
        List<Person> addressesList = addressPersonMap.getOrDefault(person.address(), new ArrayList<>());
        List<Person> phonesList = phonePersonMap.getOrDefault(person.phoneNumber(), new ArrayList<>());
        namesList.add(person);
        addressesList.add(person);
        phonesList.add(person);

        // if we got List as default we need to put it in HashMap
        if (namesList.size() == 1) {
            namePersonMap.put(person.name(), namesList);
        }
        if (addressesList.size() == 1) {
            addressPersonMap.put(person.name(), addressesList);
        }
        if (phonesList.size() == 1) {
            phonePersonMap.put(person.name(), phonesList);
        }

    }

    @Override
    public synchronized void delete(int id) {
        Person person = idPersonMap.get(id);
        if (person == null) {
            return;
        }
        idPersonMap.remove(person.id());
        namePersonMap.remove(person.name());
        addressPersonMap.remove(person.address());
        phonePersonMap.remove(person.phoneNumber());
    }

    @Override
    public List<Person> findByName(String name) {
        return namePersonMap.get(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return addressPersonMap.get(address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return phonePersonMap.get(phone);
    }
}
