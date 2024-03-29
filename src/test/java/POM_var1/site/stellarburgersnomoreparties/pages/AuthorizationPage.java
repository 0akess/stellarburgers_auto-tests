package POM_var1.site.stellarburgersnomoreparties.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;


public class AuthorizationPage extends BaseMethodPage<AuthorizationPage> implements Personal{

    @FindBy(how = How.XPATH, using = "//*[text()='Уже зарегистрированы?']/child::*")
    private SelenideElement buttonEntryToRegister;

    @FindBy(how = How.XPATH, using = "//*[text()='Войти в аккаунт']")
    private SelenideElement buttonEntryToMaine;

    @FindBy(how = How.XPATH, using = "//*[text()='Email']/following-sibling::*")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = "//*[text()='Пароль']/following-sibling::*")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = "//*[text()='Войти']")
    private SelenideElement buttonLogIn;

    @FindBy(how = How.XPATH, using = "//*[text()='Оформить заказ']")
    private SelenideElement buttonPlaceAnOrder;

    @Step("нажатие на кнопку Войти в аккаунт")
    public AuthorizationPage clickLogInToTheSite() {
        buttonLogIn.click();
        return this;
    }

    @Step("нажатие на кнопку Войти в аккаунт")
    public AuthorizationPage clickEntryToRegisterPage() {
        buttonEntryToRegister.click();
        return this;
    }

    @Step("нажатие на кнопку Войти")
    public AuthorizationPage clickEntryToMainePage() {
        buttonEntryToMaine.click();
        return this;
    }

    @Step("нажатие на кнопку Войти")
    public AuthorizationPage clickEntryToRecoveryPasswordPage() {
        buttonLogIn.click();
        return this;
    }

    @Step("ввод значение в поле Email")
    public AuthorizationPage setValueEmail(String email) {
        inputEmail.setValue(email);
        return this;
    }

    @Step("ввод значение в поле Password")
    public AuthorizationPage setValuePassword(String password) {
        inputPassword.setValue(password);
        return this;
    }

    @Step("проверка успешной авторизации")
    public AuthorizationPage checkSuccessfulAuthorization() {
        buttonPlaceAnOrder.shouldBe(Condition.visible);
        return this;
    }

    @Step("проверка успешного выхода из аккаунта")
    public AuthorizationPage checkItIsLoginPage(){

        for (int i = 0; i < 10; i++){
            Selenide.sleep(100);

            if(buttonLogIn.isDisplayed()) break;

            if(i == 9){
                throw new NullPointerException("Вы не вышли из аккаунта");
            }
        }
        return this;
    }
}
