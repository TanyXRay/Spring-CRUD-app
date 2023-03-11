package ru.home.chernyadieva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.chernyadieva.model.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {

    List<Person> findByName(String name);

    List<Person> findByNameOrderByAge(String name);

    List<Person> findByEmail(String email);

    List<Person> findByNameStartingWith(String startWith);

    List<Person> findByNameOrEmail(String name, String email);
}
