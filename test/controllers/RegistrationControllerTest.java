package controllers;

import dto.RegistrationForm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegistrationControllerTest
{
    @Test
    public void RegistrationValidate() {
        RegistrationForm f = new RegistrationForm();
        f.setEmail("Lal@gmail.com");
        f.setName("Lal");
        f.setPassword1("680");
        f.setPassword2("680");
        assertEquals(f.validate(), null);
        f.setEmail("");
        f.setName("Lal");
        f.setPassword1("680");
        f.setPassword2("680");
        assertEquals(f.validate(), "Invalid Details");
        f.setEmail("Lal@gmail.com");
        f.setName("");
        f.setPassword1("680");
        f.setPassword2("680");
        assertEquals(f.validate(), "Invalid Details");
        f.setEmail("Lal@gmail.com");
        f.setName("Lal");
        f.setPassword1("");
        f.setPassword2("680");
        assertEquals(f.validate(), "Invalid Details");
        f.setEmail("Lal@gmail.com");
        f.setName("Lal");
        f.setPassword1("680");
        f.setPassword2("");
        assertEquals(f.validate(), "Invalid Details");
        f.setEmail("");
        f.setName("");
        f.setPassword1("");
        f.setPassword2("");
        assertEquals(f.validate(), "Invalid Details");
    }
}
