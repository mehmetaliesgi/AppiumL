package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class RegisterScreen extends BaseScreen{
    public RegisterScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    @AndroidFindBy(accessibility = "Ad:")
    public WebElement inputName;

    @AndroidFindBy(accessibility = "Soyad:")
    public WebElement inputLastName;

    @AndroidFindBy(accessibility = "E-posta Adresi:")
    public WebElement inputEmail;

    @AndroidFindBy(accessibility = "Şifre:")
    public WebElement inputPassword;

    @AndroidFindBy(accessibility = "Şifre Tekrarı:")
    public WebElement inputConfirmPassword;

    @AndroidFindBy(accessibility = "Gizlilik ve Güvenlik Politikası'nı okudum, onaylıyorum.")
    public WebElement checkBoxSecretAndSecurityPolicy;

    @AndroidFindBy(accessibility = "Kampanyalardan haberdar olmak için elektronik ileti almak istiyorum.")
    public WebElement checkBoxCampaignPolicy;

    @AndroidFindBy(id = "com.mobisoft.kitapyurdu:id/btnRegister")
    public WebElement btnRegister;



    // Actions
    public RegisterScreen enterName(String name){
        sendContext(inputName, name);
        return this;
    }

    public RegisterScreen enterLastName(String lastName){
        sendContext(inputLastName, lastName);
        return this;
    }

    public RegisterScreen enterEmail(String email){
        sendContext(inputEmail, email);
        return this;
    }

    public RegisterScreen enterPassword(String password){
        sendContext(inputPassword, password);
        return this;
    }

    public RegisterScreen enterConfirmPassword(String password){
        sendContext(inputConfirmPassword, password);
        return this;
    }

    public RegisterScreen clickSecretAndSecurityPolicy(){
        click(checkBoxSecretAndSecurityPolicy);
        return this;
    }

    public RegisterScreen clickCampaignPolicy(){
        click(checkBoxCampaignPolicy);
        return this;
    }

    public MyAccountScreen clickRegister(){
        click(btnRegister);
        return new MyAccountScreen(driver);
    }
}
