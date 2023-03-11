package ru.home.chernyadieva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.home.chernyadieva.model.Person;
import ru.home.chernyadieva.service.ItemService;
import ru.home.chernyadieva.service.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final ItemService itemService;

    @Autowired
    public PeopleController(PeopleService peopleService, ItemService itemService) {
        this.peopleService = peopleService;
        this.itemService = itemService;
    }

    /**
     * Получим список всех людей из DAO и передадим данные на представление (view)
     *
     * @return
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("peopleList", peopleService.findAll());
        itemService.findByItemName("молоко");
        itemService.findByOwner(peopleService.findAll().get(0));
        peopleService.test();

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
        model.addAttribute("personModel", peopleService.findById(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("personModel") Person person) {
        // аннотация равносильна model.addAttribute("personModel", new Person());
        // нам не нужно считывать поля
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("personModel") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        peopleService.save(person);
        return "redirect:/people"; //redirect-переход на другую страницу(people)
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("personModel", peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("personModel") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        peopleService.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}