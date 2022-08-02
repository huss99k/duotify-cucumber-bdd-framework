package com.duotify.stepDefintions;

import com.duotify.utilities.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.time.Duration;

public class Hooks {

    @BeforeAll   // runs once before all scenarios
    public void setupDb(){

    }

    @AfterAll   // runs once after all scenarios have completed
    public void close(){

    }

    @Before ("not @db")
    public void setup(){


        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    @Before ("@db")
    public void setupDB(){


        System.out.println("Setting up DB");
    }



    @After ("not @db")
    public void tearDown(Scenario scenario){

        if(scenario.isFailed()){

            byte[] screenshotAs = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotAs,"image/png", "screenshotOfFailure");
        }



        Driver.quitDriver();
    }

    @After ("@db")
    public void tearDownDB(){

        System.out.println("Tearing doen db connection");
    }


}
