package helper;

import net.serenitybdd.core.Serenity;
import org.assertj.core.api.SoftAssertions;
import org.springframework.stereotype.Component;
import java.util.Map;


/***
 * This class contains method required for customAssertions
 */

@Component
public class CustomSoftAssertions {

    private SoftAssertions softAssert;

    public SoftAssertions getSoftAssert() {
        return softAssert;
    }

    /***
     * This method is used to log all the assertions error and print
     */

    public void assertAll() {
        softAssert.assertAll();
        softAssert=null;
    }

    private SoftAssertions initialize() {
        if (softAssert == null) {
            softAssert = new SoftAssertions();
        }
        return softAssert;
    }

    /***
     * This method is used for softAssertions for actual and expected String values
     */

    public void softAssertEquals(String key, String expected, String actual) {
        softAssert = initialize();
        softAssert.assertThat(expected).isEqualTo(actual);
        Serenity.recordReportData().withTitle("********* Assertions **********").
                andContents("key: " + key + " | " + "Expected: " + expected + " | " + "Actual: " + actual);

    }

    /***
     * This method is used for softAssertions for expected boolean values
     */

    public void softAssertEquals(boolean expected) {
        softAssert = initialize();
        softAssert.assertThat(expected);
        Serenity.recordReportData().withTitle("********* Assertions **********").
                andContents(String.valueOf(expected));

    }

    /***
     * This method is used for softAssertions for actual and expected int values
     */

    public void softAssertEquals(String key, int expected, int actual) {
        softAssert = initialize();
        softAssert.assertThat(Integer.valueOf(expected)).isEqualTo(Integer.valueOf(actual));
        Serenity.recordReportData().withTitle("********* Assertions **********").
                andContents("key: " + key + " | " + "Expected: " + expected + " | " + "Actual: " + actual);

    }

    /***
     * This method is used for softAssertions for actual and expected for Map of values
     */

    public void softAssertEquals(Map<String, String> map) {
        softAssert = initialize();
        softAssert.assertThat(map.keySet()).isEqualTo((map.values()));
        Serenity.recordReportData().withTitle("********* Assertions **********").
                andContents("Expected: " + map.keySet() + " | " + "Actual: " + map.values());

    }

    /***
     * This method is used for softAssertions for actual and expected for float values
     */

    public void softAssertEquals(String key, float expected, float actual) {
        softAssert = initialize();
        softAssert.assertThat(expected).isEqualTo(actual);
        Serenity.recordReportData().withTitle("********* Assertions **********").
                andContents("key: " + key + " | " + "Expected: " + expected + " | " + "Actual: " + actual);

    }
}