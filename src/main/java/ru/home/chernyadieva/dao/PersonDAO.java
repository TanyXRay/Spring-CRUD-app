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
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT,"Tom"));
        people.add(new Person(++PEOPLE_COUNT,"John"));
        people.add(new Person(++PEOPLE_COUNT,"Silvia"));
        people.add(new Person(++PEOPLE_COUNT,"Jill"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }
}
