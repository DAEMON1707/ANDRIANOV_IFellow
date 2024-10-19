a id="readme-top"></a>

<!-- Содержание -->
# Содержание
<a id="content"></a>
<ol>
    <li><a href="#introduction">Введение</a></li>
    <li>
        <a href="#home-works">Домашняя работа</a>
        <ul>
            <li><a href="#home-work-2">Домашняя работа №2</a></li>
            <li><a href="#home-work-3">Домашняя работа №3</a></li>
            <li><a href="#home-work-4">Домашняя работа №4</a></li>
            <li>
                <a href="#home-work-5">Домашняя работа №5</a>
                <ul>
                    <li><a href="#home-work-5-task-1">Задача №1</a></li>
                    <li><a href="#home-work-5-task-2">Задача №2</a></li>
                </ul>
            </li>
            <li><a href="#home-work-6">Домашняя работа №6</a></li>
            <li>
                <a href="#final-home-work">Финальная домашняя работа</a>
                <ul>
                    <li><a href="#final-home-work-task-1">Задача №1</a></li>
                    <li><a href="#final-home-work-task-2">Задача №2</a></li>
                </ul>
            </li>
        </ul>
    </li>
</ol>

<!-- Введение -->
# Введение
<a id="introduction"></a>
Данный проект создан в качестве практического задания по курсу обучения автоматизации тестирования [iFellow](https://ifellow.ru).

<!-- Домашняя работа -->
# Домашняя работа
<a id="home-works"></a>
Каждое домашнее задание выполняется в отдельной ветки проекта.

<p align="right"><a href="#readme-top">вверх</a></p>

## Домашняя работа №2
<a id="home-work-2"></a>
Создание проекта.

<p align="right"><a href="#readme-top">вверх</a></p>

## Домашняя работа №3
<a id="home-work-3"></a>
Ветка: [IF_HW_3](https://github.com/DAEMON1707/ANDRIANOV_IFellow/tree/IF_HW_3)

Первая практическая работа по проекту.

По паттерну PageObject реализовано тестирование [Jira](https://edujira.ifellow.ru).

<p align="right"><a href="#readme-top">вверх</a></p>

## Домашняя работа №4
<a id="home-work-4"></a>
Ветка: [IF_HW_4](https://github.com/DAEMON1707/ANDRIANOV_IFellow/tree/IF_HW_4)

Перевод проекта из предыдущего задания в Cucumber.

<p align="right"><a href="#readme-top">вверх</a></p>

## Домашняя работа №5
<a id="home-work-5"></a>
### Задача №1
<a id="home-work-5-task-1"></a>
Ветка: [IF_HW_5_1](https://github.com/DAEMON1707/ANDRIANOV_IFellow/tree/IF_HW_5_1)

Погружение в API.

На [сайте](https://rickandmortyapi.com/documentation/#episode-schema) есть документация по API по сериалу Рик и Морти.

Найти информацию по персонажу Морти Смит. Выбрать из ответа последний эпизод, где появлялся Морти. Получить из списка последнего эпизода последнего персонажа. Получить данные по местонахождению и расе этого персонажа.

Проверить, этот персонаж той же расы и находится там же где и Морти?

<p align="right"><a href="#readme-top">вверх</a></p>

### Задача №2
<a id="home-work-5-task-2"></a>
Ветка: [IF_HW_5_2](https://github.com/DAEMON1707/ANDRIANOV_IFellow/tree/IF_HW_5_2)

Углубление в API.

Написан тест с использованием **Json**!

В тесте проверяется валидность данных в ответе на запрос по документации на [сайте](https://reqres.in). Создать в проекте файл с расширением `.Json { "name": "Potato" }`.

Создан тест-запрос для создания пользователя (CREATE). `Body` в запрос передается из ранее созданного файла, в котором на лету поменяются поле `name` и добавляется поле `job` `{ "name": "Tomato", "job": "Eat maket"}`.

Проверяется ответ 201. Сверяется, что полученный `response` имеет валидные данные по значениям `key` и `value`:
```sh
"name": "Tomato"
"job": "Eat make“
```

<p align="right"><a href="#readme-top">вверх</a></p>

## Домашняя работа №6
<a id="home-work-6"></a>
Ветка: [IF_HW_6](https://github.com/DAEMON1707/ANDRIANOV_IFellow/tree/IF_HW_6)

Добавлен **allure** + **property**!

Возможно осуществить запуск тестов командой:
```sh
mvn clean test
```
Allure собирается по команде:
```sh
mvn allure:"report"
```
```sh
mvn allure:"serve"
```
Есть возможность запустить тесты и собрать Allure, как из консоли, так и из класса/плагина.

Добавлен README!

Исправлен `.gitignor`.

В удаленном репозитории на основном экране ветки отображаются только `src`, `pom.xml`, `readme`, `.gitignor`.

<p align="right"><a href="#readme-top">вверх</a></p>

## Финальная домашняя работа
<a id="final-home-work"></a>

### Задача №1
<a id="final-home-work-task-1"></a>
Ветка: [FHW_UI](https://github.com/DAEMON1707/ANDRIANOV_IFellow/tree/FHW_UI)

Добавлена возможность запуска ChromeDriver из папки по пользовательскому пути.

Доведено до идеала.

<p align="right"><a href="#readme-top">вверх</a></p>

### Задача №2
<a id="final-home-work-task-2"></a>
Ветка: [FHW_API](https://github.com/DAEMON1707/ANDRIANOV_IFellow/tree/FHW_API)

Слиты ветки [IF_HW_5_1](https://github.com/DAEMON1707/ANDRIANOV_IFellow/tree/IF_HW_5_1) и [IF_HW_5_2](https://github.com/DAEMON1707/ANDRIANOV_IFellow/tree/IF_HW_5_2).

Добавлен Cucumber.

Доведено до идеала.

<p align="right"><a href="#readme-top">вверх</a></p>