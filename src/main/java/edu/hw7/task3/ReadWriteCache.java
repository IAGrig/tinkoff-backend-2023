package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCache implements PersonDatabase {
    private final long tryLockMillisecondsCount = 150;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final HashMap<Integer, Person> idPersonHashMap;
    private final HashMap<String, List<Person>> namePersonHashMap;
    private final HashMap<String, List<Person>> addressPersonHashMap;
    private final HashMap<String, List<Person>> phonePersonHashMap;

    public ReadWriteCache() {
        idPersonHashMap = new HashMap<>();
        namePersonHashMap = new HashMap<>();
        addressPersonHashMap = new HashMap<>();
        phonePersonHashMap = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        try {
            lock.writeLock().tryLock(tryLockMillisecondsCount, TimeUnit.MILLISECONDS);
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public synchronized void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = idPersonHashMap.get(id);
            if (person == null) {
                return;
            }
            idPersonHashMap.remove(person.id());
            namePersonHashMap.remove(person.name());
            addressPersonHashMap.remove(person.address());
            phonePersonHashMap.remove(person.phoneNumber());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return namePersonHashMap.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        try {
            lock.readLock().tryLock(tryLockMillisecondsCount, TimeUnit.MILLISECONDS);
            return addressPersonHashMap.get(address);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phonePersonHashMap.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
