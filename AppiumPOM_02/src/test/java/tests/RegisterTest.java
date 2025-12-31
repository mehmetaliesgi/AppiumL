package tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import screens.MainScreen;
import screens.MyAccountScreen;
import screens.RegisterScreen;
import utils.constants.AlertMessages;
import utils.helpers.LogHelper;

import static utils.constants.TestGroups.*;

public class RegisterTest extends BaseTest {

    public AlertMessages alertMessages = new AlertMessages();
    private static final Logger logger = LogHelper.getLogger(RegisterTest.class);

    @Test(groups = {REGISTER, SMOKE})
    public void missMatchPasswordRegisterTest() {
        LogHelper.logTestStart(logger, "missMatchPasswordRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());
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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "missMatchPasswordRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void alreadyExistsUserTest() {
        LogHelper.logTestStart(logger, "alreadyExistsUserTest");


        MainScreen mainScreen = new MainScreen(getDriver());

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

        registerScreen.clickConfirm();
        registerScreen.clickBackAfterInvalidRegister();
        registerScreen.clickBackAfterInvalidRegister();


    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void invalidPasswordRegisterTest() {
        LogHelper.logTestStart(logger, "invalidPasswordRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "invalidPasswordRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void invalidNameRegisterTest() {
        LogHelper.logTestStart(logger, "invalidNameRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "invalidNameRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void invalidLastNameRegisterTest() {
        LogHelper.logTestStart(logger, "invalidLastNameRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "invalidLastNameRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void invalidEmailRegisterTest() {
        LogHelper.logTestStart(logger, "invalidEmailRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "invalidEmailRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void withoutNameRegisterTest() {
        LogHelper.logTestStart(logger, "withoutNameRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "withoutNameRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void withoutLastNameRegisterTest() {
        LogHelper.logTestStart(logger, "withoutLastNameRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "withoutLastNameRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void withoutEmailRegisterTest() {
        LogHelper.logTestStart(logger, "withoutEmailRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "withoutEmailRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void withoutPasswordRegisterTest() {
        LogHelper.logTestStart(logger, "withoutPasswordRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "withoutPasswordRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void withoutConfirmPasswordRegisterTest() {
        LogHelper.logTestStart(logger, "withoutConfirmPasswordRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "withoutConfirmPasswordRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, INVALID})
    public void withoutSecretAndSecurityPolicyRegisterTest() {
        LogHelper.logTestStart(logger, "withoutSecretAndSecurityPolicyRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

            RegisterScreen registerScreen = (RegisterScreen) mainScreen.clickMyAccount()
                    .clickRegister()
                    .enterName("@12")
                    .enterLastName("1512")
                    .enterEmail("mehmetaliesgi60@gmail.com")
                    .enterPassword("1123456790")
                    .enterConfirmPassword("1123456790")
                    .clickRegister();

            registerScreen.getAlertMissMatchPasswordText(alertMessages.ALERT_UNCHECKED_SECURITY_POLICY);

            registerScreen.clickConfirm();
            registerScreen.clickBackAfterInvalidRegister();
            registerScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "withoutSecretAndSecurityPolicyRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {REGISTER, SMOKE, SUCCESSFULLY})
    public void withoutCampaignPolicyRegisterTest() {
        LogHelper.logTestStart(logger, "withoutCampaignPolicyRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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

            mainScreen.clickConfirm();
            mainScreen.clickBackAfterInvalidRegister();
            mainScreen.clickBackAfterInvalidRegister();
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "withoutCampaignPolicyRegisterTest", false);
            throw exception;
        }
    }

    @Test(groups = {SMOKE, REGISTER, SUCCESSFULLY})
    public void successfullyRegisterTest() {
        LogHelper.logTestStart(logger, "successfullyRegisterTest");

        try {
            MainScreen mainScreen = new MainScreen(getDriver());

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
        } catch (Exception exception) {
            logger.error("Test sırasında hata oluştu", exception);
            LogHelper.logTestEnd(logger, "successfullyRegisterTest", false);
            throw exception;
        }
    }
}
