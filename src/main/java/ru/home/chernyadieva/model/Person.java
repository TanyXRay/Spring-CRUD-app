package ru.home.chernyadieva.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;

    @NotBlank(message = "Name wasn't to be empty!")
    @Size(min = 2, max = 20, message = "Name should be between 2-20 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 115, message = "Age should be less than 115")
    private int age;

    @NotBlank(message = "Email wasn't to be empty!")
    @Email(message = "Email should be valid form")
    private String email;

    //Страна, Город, Индекс (обязательно 6 цифр!) - на англ.
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",
            message = "Address is not valid. Address format should be: Country, City, Postal code (6 numbers)")
    private String address;
}
