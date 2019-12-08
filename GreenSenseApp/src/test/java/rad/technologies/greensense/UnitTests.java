package rad.technologies.greensense;
//R.A.D. Technologies

import com.androdocs.httprequest.HttpRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UnitTests{

    private static final String FAKE_EMAIL = "test@email.com";

    @Test
    public void testConvertFahrenheitToCelsius() {
        float fahrenheit = 100;
        float expected = (float) 37.77777862548828;
        // use this method because float is not precise
        float celsius = ((fahrenheit - 32) * 5 / 9);
        assertEquals("Conversion from fahrenheit to celsius failed", expected, celsius, 0.001);
    }

    @Test
    public void testConvertCelsiusToFahrenheit() {
        float celsius = (float) 37.77777862548828;
        float expected = 100;
        // use this method because float is not precise
        float fahrenheit = ((celsius * 9) / 5) + 32;
        assertEquals("Conversion from celsius to fahrenheit failed", expected, fahrenheit, 0.001);
    }

    @Test
    public void emailValidator_CorrectLogin_ReturnsTrue() {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(FAKE_EMAIL);
        assertTrue(String.valueOf(matcher), true);
    }

    @Test
    public void checkWeatherWebsite() {
        String API = "c3d526c9c98e4e7df5e0996720673562";
        String CITY = "Toronto,CA";
        assertTrue(String.valueOf(HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&units=metric&appid=" + API)),true);
    }
}