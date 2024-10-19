/**
 * Форма "Искать".
 */

package ru.iFellow.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraSearchPage {
    private final SelenideElement aLinkOpenTask = $x("//a[@id='key-val']").as("Гиперссылка на задачу");
    private final SelenideElement aComboBoxSorting = $x("//a[@title='Выберите поле для сортировки запросов.']").as("Выпадающий список \"Сортировать по\"");

    /**
     * Нажатие на пункт выпадающего меню "Сортировать по".
     * @param sortingLabel наименование нажимаемого поля
     */
    private void clickSortingLabel(String sortingLabel) {
        $x("//div[@id='order-by-options-suggestions']//label[@data-descriptor-title='" + sortingLabel + "']").as("Пункт \"" + sortingLabel + "\" выпадающего списка \"Сортировать по\"").click();
    }

    /**
     * Сортировка задач.
     * @param sortingLabel поле, по которому производится сортировка
     */
    public void sortingTask(String sortingLabel) {
        aComboBoxSorting.click();
        clickSortingLabel(sortingLabel);
    }

    /**
     * Нажатие на номер открытой задачи.
     */
    public void clickLinkOpenTask() {
        aLinkOpenTask.click();
    }
}
