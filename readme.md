# Применение CRUD в Spring-web-app с взаимодействием с БД

##### В данном проекте реализован пример CRUD - приложения с применением паттерна DAO.

## В ветке master:

1. *реализована функция READ из CRUD (при GET - запросе перехода на URL /people показываются данные - список людей со
   ссылкой на конкретного человека на отдельную страницу html, где отражены имя и id).*
2. *реализована функция POST из CRUD (при GET - запросе перехода на URL /people/new выходит окно для добавления
   данных пользователем (добавление имени человека), после ввода данных с помощью редиректа пользователя возвращает на
   показ всего списка людей (/people).*
3. *реализована функция PATCH из CRUD (при POST - запросе перехода на URL /people/1.../edit выходит окно для обновления
   данных пользователем (изменение имени человека), после ввода данных с помощью редиректа пользователя возвращает на
   показ всего списка людей, уже обновленного (/people). В спринг реализована функция фильтрации для поиска скрытых
   методов, так как HTML 5 не поддерживает самостоятельные запросы PATCH,DELETE,PUT, такие запросы делаются скрытыми
   методами в конфигурационных файлах (web.xml) или классе*
4. *реализована функция DELETE из CRUD (при POST - запросе перехода на URL /people/1... выходит окно для удаления
   данных пользователем из списка людей, после ввода данных с помощью редиректа пользователя возвращает на
   показ всего списка людей, уже обновленного (/people). На страницу /people также была добавлена функция "Create new
   person" со ссылкой на /people/1.../edit(страница обновления данных) для добавления новой персоны*
5. *добавлена необходимая валидация данных. В pom.xml указана зависимость hibernate-validator для использования аннотаций
   для валидации данных при POST, PATCH запросах (@Valid).*