package POM_var1.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import POM_var1.site.stellarburgersnomoreparties.resources.BaseURL;
import org.apache.http.HttpStatus;
import POM_var1.site.stellarburgersnomoreparties.data.tests.GetUserData;
import POM_var1.site.stellarburgersnomoreparties.pages.AuthorizationPage;
import POM_var1.site.stellarburgersnomoreparties.request.model.User;
import POM_var1.site.stellarburgersnomoreparties.request.user.DeleteUser;
import POM_var1.site.stellarburgersnomoreparties.request.user.PostRegister;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Сьют на авторизацию пользователя")
public class AuthorizationTest extends BaseTest {

    private String token;
    private final GetUserData data = new GetUserData();
    private String email = data.getEmailUser();
    private String password = data.getPasswordUser(7);
    private String name = data.getNameUser();
    private AuthorizationPage authorizationPage = page(AuthorizationPage.class);

    @BeforeEach
    @DisplayName("Создаем пользователя для тестов")
    public void startTest() {
        token = new PostRegister().registerUser(
                new User().builder()
                        .email(email)
                        .password(password)
                        .name(name)
                        .build())
                .statusCode(HttpStatus.SC_OK)
                .extract().path("accessToken");
    }

    @AfterEach
    @DisplayName("Удаляем тестового пользователя")
    public void endTests() {
        new DeleteUser().deleteUser(token)
                .statusCode(HttpStatus.SC_ACCEPTED);
    }

    @Test
    @DisplayName("Проверка авторизации со стартом с кнопки Войти в аккаунт на главной")
    public void authorizationButtonEntryToAccount() {
        authorizationPage
                .open(BaseURL.MAIN_URL)
                .clickEntryToMainePage()
                .setValueEmail(email)
                .setValuePassword(password)
                .clickLogInToTheSite()
                .checkSuccessfulAuthorization();
    }

    @Test
    @DisplayName("Проверка авторизации со стартом с кнопки Личный кабинет на главной")
    public void authorizationButtonPersonalAccount() {
        authorizationPage
                .open(BaseURL.MAIN_URL)
                .clickPersonalAccount(AuthorizationPage.class)
                .setValueEmail(email)
                .setValuePassword(password)
                .clickLogInToTheSite()
                .checkSuccessfulAuthorization();
    }

    @Test
    @DisplayName("Проверка авторизации со стартом с кнопки Войти на странице регистрации")
    public void authorizationButtonToRegisterPage() {
        authorizationPage
                .open(BaseURL.REGISTRATION_URl)
                .clickEntryToRegisterPage()
                .setValueEmail(email)
                .setValuePassword(password)
                .clickLogInToTheSite()
                .checkSuccessfulAuthorization();
    }

    @Test
    @DisplayName("Проверка авторизации со стартом с кнопки Войти на странице восстановить пароль")
    public void authorizationButtonToRecoveryPasswordPage() {
        authorizationPage
                .open(BaseURL.FORGOT_PASSWORD_URL)
                .clickEntryToRecoveryPasswordPage()
                .setValueEmail(email)
                .setValuePassword(password)
                .clickLogInToTheSite()
                .checkSuccessfulAuthorization();
    }
}
