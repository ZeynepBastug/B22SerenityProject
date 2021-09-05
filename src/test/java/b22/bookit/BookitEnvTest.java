package b22.bookit;

import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

@SerenityTest
public class BookitEnvTest {

    @Test
    public void test1(){

        System.out.println("base.url = " + ConfigurationReader.getProperty("base.url"));
        System.out.println("teacher email = " + ConfigurationReader.getProperty("teacher.username"));
        System.out.println("teacher password = " + ConfigurationReader.getProperty("teacher.password"));

    }
}
