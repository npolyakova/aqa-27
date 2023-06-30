package ru.apteka.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Objects;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class WebTest {

    @BeforeAll
    public static void setDriver() throws MalformedURLException {
        String isRemote = System.getenv("IS_REMOTE");
        if (Objects.equals(isRemote, "true")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setCapability("enableVNC:", true);
            WebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
            setWebDriver(driver);
        } else {
            Configuration.browser = "firefox";
        }
    }
}
