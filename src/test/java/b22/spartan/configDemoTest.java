package b22.spartan;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Disabled
public class configDemoTest {

    @Test
    public void test1() throws IOException {
        System.out.println(ConfigurationReader.getProperty("serenity.project.name"));





    }
}
