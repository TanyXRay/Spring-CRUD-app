package ru.home.chernyadieva.dao;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.home.chernyadieva.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс будет взаимодействовать с БД (CRUD)
 */
@Component
public class PersonDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/main_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public List<Person> index() {
        List<Person> peopleList = new ArrayList<>();

        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM Person";
        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()) {
            Person person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

            peopleList.add(person);
        }
        return peopleList;
    }

    @SneakyThrows
    public Person show(int id) {
        var preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));

        return person;
    }

    @SneakyThrows
    public void save(Person person) {
        var preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(1,?,?,?)");

        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.setString(3, person.getEmail());
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public void update(Person personUpdated, int id) {
        var preparedStatement = connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");

        preparedStatement.setString(1, personUpdated.getName());
        preparedStatement.setInt(2, personUpdated.getAge());
        preparedStatement.setString(3, personUpdated.getEmail());
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public void delete(int id) {
        var preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");

        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
