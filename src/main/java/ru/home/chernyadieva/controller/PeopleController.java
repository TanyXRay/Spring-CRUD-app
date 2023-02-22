package ru.home.chernyadieva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.home.chernyadieva.dao.PersonDAO;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * Получим список всех людей из DAO и передадим данные на представление (view)
     *
     * @return
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }

    /**
     * Получим данные одного человека по id и передадим эти данные на представление (view)
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }
}
