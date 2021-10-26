package ui.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
    WebDriver driver;
    String path = "https://intranet.ucll.be";

    public Page (WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle () {
        return driver.getTitle();
    }
}
