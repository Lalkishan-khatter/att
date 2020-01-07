package controllers;

import dto.LoginForm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest {
    @Test
    public void testLoginvalidate()
    {
        LoginForm l=new LoginForm();
        l.setEmail("Lal@gmail.com");
        l.setPassword("680");
        assertEquals(l.validate(),null);
        l.setEmail("");
        l.setPassword("680");
        assertEquals(l.validate(),"Invalid email or password");
        l.setEmail("Lal@gmail.com");
        l.setPassword("");
        assertEquals(l.validate(),"Invalid email or password");
        l.setEmail("");
        l.setPassword("");
        assertEquals(l.validate(),"Invalid email or password");
    }
}
