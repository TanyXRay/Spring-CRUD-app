package ru.home.chernyadieva.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name wasn't to be empty!")
    @Size(min = 2, max = 20, message = "Name should be between 2-20 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 115, message = "Age should be less than 115")
    @Column(name = "age")
    private int age;

    @NotBlank(message = "Email wasn't to be empty!")
    @Email(message = "Email should be valid form")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<Item> itemList;

    // //Страна, Город, Индекс (обязательно 6 цифр!) - на англ.
    // @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",
    //         message = "Address is not valid. Address format should be: Country, City, Postal code (6 numbers)")
    // @Column(name = "address")
    // private String address;


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}