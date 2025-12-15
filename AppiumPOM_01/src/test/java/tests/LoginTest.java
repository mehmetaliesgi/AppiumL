package tests;

import org.testng.annotations.Test;
import screens.DashboardScreen;
import screens.LoginScreen;
import screens.MyAccount;
import utils.PopupUtils;

public class LoginTest extends BaseTest{

    @Test
    public void failLoginTest(){
        DashboardScreen dashboardScreen = new DashboardScreen(driver);

        dashboardScreen.notificationAlert()
                .todaysDeals()
                        .myAccount()
                                .login()
                                        .enterUsername("mehmetaliesgi@gmail.com")
                                        .clickLoginButton()
                                        .enterPassword("mehmetaliesgi")
                                        .clickLoginButton()
                                        .enterErrorMessage("E-posta adresi veya şifre hatalı, kontrol edebilir misin?");
    }
}
