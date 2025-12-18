package tests;

import org.testng.annotations.Test;
import screens.MainScreen;
import screens.MyAccountScreen;
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
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("Mehmet Ali")
                .enterLastName("Esgi")
                .enterEmail("mehmetaliesgi60@gmail.com")
                .enterPassword("1")
                .enterConfirmPassword("1")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_INVALID_PASSWORD);
    }

    @Test
    public void invalidNameRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("@")
                .enterLastName("Esgi")
                .enterEmail("mehmetaliesgi60@gmail.com")
                .enterPassword("1123456790")
                .enterConfirmPassword("1123456790")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_INVALID_NAME);
    }

    @Test
    public void invalidLastNameRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("@12")
                .enterLastName("E")
                .enterEmail("mehmetaliesgi60@gmail.com")
                .enterPassword("1123456790")
                .enterConfirmPassword("1123456790")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_INVALID_LASTNAME);
    }

    @Test
    public void invalidEmailRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("@12")
                .enterLastName("E2")
                .enterEmail("mehmetaliesgi60gmail.com")
                .enterPassword("1123456790")
                .enterConfirmPassword("1123456790")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_INVALID_EMAIL);
    }

    @Test
    public void withoutNameRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("")
                .enterLastName("Esgi")
                .enterEmail("mehmetaliesgi60@gmail.com")
                .enterPassword("1123456790")
                .enterConfirmPassword("1123456790")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_INVALID_NAME);
    }

    @Test
    public void withoutLastNameRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("@12")
                .enterLastName("")
                .enterEmail("mehmetaliesgi60@gmail.com")
                .enterPassword("1123456790")
                .enterConfirmPassword("1123456790")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_INVALID_LASTNAME);
    }

    @Test
    public void withoutEmailRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("@12")
                .enterLastName("1512")
                .enterEmail("")
                .enterPassword("1123456790")
                .enterConfirmPassword("1123456790")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_EMPTY_EMAIL);
    }

    @Test
    public void withoutPasswordRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("@12")
                .enterLastName("1512")
                .enterEmail("mehmetaliesgi60@gmail.com")
                .enterPassword("")
                .enterConfirmPassword("1123456790")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_EMPTY_PASSWORD);
    }

    @Test
    public void withoutConfirmPasswordRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        takeScreenshot("RegisterTest", "beforeWithoutConfirmPassword");

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("@12")
                .enterLastName("1512")
                .enterEmail("mehmetaliesgi60@gmail.com")
                .enterPassword("1123456790")
                .enterConfirmPassword("")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_MISS_MATCH_PASSWORD_MESSAGE);

        takeScreenshot("RegisterTest", "AfterWithoutConfirmPassword");
    }

    @Test
    public void withoutSecretAndSecurityPolicyRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("@12")
                .enterLastName("1512")
                .enterEmail("mehmetaliesgi60@gmail.com")
                .enterPassword("1123456790")
                .enterConfirmPassword("1123456790")
                .clickRegister();

        registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_UNCHECKED_SECURITY_POLICY);
    }

    @Test
    public void withoutCampaignPolicyRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        MyAccountScreen myAccountScreen = (MyAccountScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("Test")
                .enterLastName("test")
                .enterEmail("YOUR_MAIL")
                .enterPassword("YOUR_PASSWORD")
                .enterConfirmPassword("YOUR_PASSWORD")
                .clickSecretAndSecurityPolicy()
                .clickRegister();

        myAccountScreen.verifyMyAccountScreen("YOUR_MAIL");
    }

    @Test
    public void successfullyRegisterTest(){
        MainScreen mainScreen = new MainScreen(driver);

        MyAccountScreen myAccountScreen = (MyAccountScreen) mainScreen.clickMyAccount()
                .clickRegister()
                .enterName("Test")
                .enterLastName("test")
                .enterEmail("YOUR_MAIL")
                .enterPassword("YOUR_PASSWORD")
                .enterConfirmPassword("YOUR_PASSWORD")
                .clickSecretAndSecurityPolicy()
                .clickCampaignPolicy()
                .clickRegister();

        myAccountScreen.verifyMyAccountScreen("YOUR_MAIL");
    }
}
