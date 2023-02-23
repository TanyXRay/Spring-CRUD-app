package ru.home.chernyadieva.dao;

import org.springframework.stereotype.Component;
import ru.home.chernyadieva.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс будет взаимодействовать с БД (CRUD)
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> peopleList;

    {
        peopleList = new ArrayList<>();

        peopleList.add(new Person(++PEOPLE_COUNT,"Tom", 25, "tom_bros@gmail.com"));
        peopleList.add(new Person(++PEOPLE_COUNT, "Silvia", 28, "selesta123@gmail.com"));
        peopleList.add(new Person(++PEOPLE_COUNT, "John", 35, "johnDoe@gmail.com"));
        peopleList.add(new Person(++PEOPLE_COUNT, "Jill", 33, "jilly-jiji@gmail.com"));
    }

    public List<Person> index() {
        return peopleList;
    }

    public Person show(int id) {
        return peopleList.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        peopleList.add(person);
    }

    public void update(Person personUpdated, int id) {
        Person personForUpdated = show(id);

        personForUpdated.setName(personUpdated.getName());
        personForUpdated.setAge(personUpdated.getAge());
        personForUpdated.setEmail(personUpdated.getEmail());
    }

    public void delete(int id) {
        peopleList.removeIf(p -> p.getId() == id);
    }
}
