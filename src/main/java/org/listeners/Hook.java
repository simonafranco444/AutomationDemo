package org.listeners;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;




import java.time.Duration;

public class Hook {
    public static WebDriver browserDriver;
    private String browser;
    private String url = "https://the-internet.herokuapp.com/";
    private long timeout = 5;

    @Before
    public void startBrowser() {
        this.browser = System.getProperty("browser");
        switch (browser) {
            case -> firefox();
            case -> chrome();


        }
    }


    protected void firefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");

        browserDriver = new FirefoxDriver();
        browserDriver.manage().window().maximize();
        browserDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        browserDriver.get(url);
        System.out.println("firefoxDriver started");


    }

    @After
    public void closeBrowser(Scenario scenarios) {
        if (scenarios.isFailed()) {
            System.out.println(scenarios.getName());
        }
        browserDriver.quit();
    }
}
