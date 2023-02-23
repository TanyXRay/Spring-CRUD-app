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

        peopleList.add(new Person(++PEOPLE_COUNT, "Tom"));
        peopleList.add(new Person(++PEOPLE_COUNT, "John"));
        peopleList.add(new Person(++PEOPLE_COUNT, "Silvia"));
        peopleList.add(new Person(++PEOPLE_COUNT, "Jill"));
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
    }
}
