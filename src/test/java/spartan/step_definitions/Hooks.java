package spartan.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import spartan.utilities.ConfigurationReader;
import spartan.utilities.DBUtils;
import spartan.utilities.Driver;

import java.util.concurrent.TimeUnit;

public class Hooks {

   @Before
    public void setUp(){
        Driver.get().get(ConfigurationReader.get("url"));
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @After
    public  void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot=((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }
        Driver.closeDriver();
    }

    @Before("@db")
    public void setUpdb(){
        System.out.println("\tConnecting to database...");
        DBUtils.createConnection();
    }

    @After("@db")
    public void closeDb(){
        System.out.println("\tDisconnecting to database...");
        DBUtils.destroy();
    }

}
