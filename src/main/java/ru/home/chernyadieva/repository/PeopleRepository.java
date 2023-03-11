package ru.home.chernyadieva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.chernyadieva.model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
}
