package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.ldap.PagedResultsControl;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static jdk.xml.internal.SecuritySupport.getResourceAsStream;

public class LoginPage extends Page {

    private String username;
    private String password;

    @FindBy(id="username")
    private WebElement usernameField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="pwdLoginBtn")
    private WebElement loginButton;

    @FindBy(id="block-ucll-menu-my-ucll")
    private WebElement myUcllBlock;

    public LoginPage(WebDriver driver) {
        super(driver);
        try (InputStream input = LoginPage.class.getClassLoader().getResourceAsStream("credentials.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        driver.get(getPath()+"/Shibboleth.sso/Login?target=https%3A%2F%2Fintranet.ucll.be%2Fnl%3Fq%3Dshib_login%2Fhome");
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(myUcllBlock));
    }
}