package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCache implements PersonDatabase {
    private final long tryLockMillisecondsCount = 150;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Map<Integer, Person> idPersonMap;
    private final Map<String, List<Person>> namePersonMap;
    private final Map<String, List<Person>> addressPersonMap;
    private final Map<String, List<Person>> phonePersonMap;

    public ReadWriteCache() {
        idPersonMap = new HashMap<>();
        namePersonMap = new HashMap<>();
        addressPersonMap = new HashMap<>();
        phonePersonMap = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        try {
            lock.writeLock().tryLock(tryLockMillisecondsCount, TimeUnit.MILLISECONDS);
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = idPersonMap.get(id);
            if (person == null) {
                return;
            }
            idPersonMap.remove(person.id());
            namePersonMap.remove(person.name());
            addressPersonMap.remove(person.address());
            phonePersonMap.remove(person.phoneNumber());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return namePersonMap.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        try {
            lock.readLock().tryLock(tryLockMillisecondsCount, TimeUnit.MILLISECONDS);
            return addressPersonMap.get(address);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phonePersonMap.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
