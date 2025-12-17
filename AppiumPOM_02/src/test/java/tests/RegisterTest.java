package tests;

import org.testng.annotations.Test;
import screens.MainScreen;
import screens.RegisterScreen;
import utils.AlertMessages;

public class RegisterTest extends BaseTest{

    public AlertMessages alertMessages = new AlertMessages();

    @Test
    public void missMatchPasswordRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("Mehmet Ali")
                .enterLastName("Esgi")
                .enterEmail("mehmetaliesgi60@gmail.com")
                .enterPassword("YourPassword1.")
                .enterConfirmPassword("YourPassword2.")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_MISS_MATCH_PASSWORD_MESSAGE);
    }

    @Test
    public void alreadyExistsUserTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("Mehmet Ali")
                .enterLastName("Esgi")
                .enterEmail("railsmail1993@gmail.com")
                .enterPassword("YourPassword1.")
                .enterConfirmPassword("YourPassword1.")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_EMAIL_ALREADY_REGISTERED);
    }

    @Test
    public void invalidPasswordRegisterTest(){
    }

    @Test
    public void invalidNameRegisterTest(){
    }

    @Test
    public void invalidLastNameRegisterTest(){
    }

    @Test
    public void invalidEmailRegisterTest(){
    }

    @Test
    public void withoutNameRegisterTest(){
    }

    @Test
    public void withoutLastNameRegisterTest(){
    }

    @Test
    public void withoutEmailRegisterTest(){
    }

    @Test
    public void withoutPasswordRegisterTest(){
    }

    @Test
    public void withoutConfirmPasswordRegisterTest(){
    }

    @Test
    public void withoutSecretAndSecurityPolicyRegisterTest(){
    }

    @Test
    public void withoutCampaignPolicyRegisterTest(){
    }

    @Test
    public void successfullyRegisterTest(){
    }
}
