package ru.home.chernyadieva.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.home.chernyadieva.model.Person;

import java.util.List;

/**
 * Данный класс будет взаимодействовать с БД (CRUD)
 */
@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p FROM Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(Person personUpdated, int id) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);
        personToBeUpdated.setName(personUpdated.getName());
        personToBeUpdated.setAge(personUpdated.getAge());
        personToBeUpdated.setEmail(personUpdated.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));
    }
}
