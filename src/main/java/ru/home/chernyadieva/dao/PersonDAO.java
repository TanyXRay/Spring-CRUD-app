package ru.home.chernyadieva.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.home.chernyadieva.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Данный класс будет взаимодействовать с БД (CRUD)
 */
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElseThrow();
    }

    /**
     * Перегруженный метод для валидатора PersonValidator
     * @param email
     * @return
     */
    public Optional<Person> show(String email) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?",
                        new Object[]{email},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, age, email) VALUES(?,?,?)",
                person.getName(),
                person.getAge(),
                person.getEmail());
    }

    public void update(Person personUpdated, int id) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                personUpdated.getName(),
                personUpdated.getAge(),
                personUpdated.getEmail(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
