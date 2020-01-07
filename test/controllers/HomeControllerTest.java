package controllers;

import dto.ApplicationForm;
import dto.LoginForm;
import dto.RegistrationForm;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals( 303,result.status());
    }


    @Test
    public void ApplicationValidate()
    {
        ApplicationForm a=new ApplicationForm();
        a.setEmail("Lal@gmail.com");
        assertEquals(a.validate(), null);
        a.setEmail("");
        assertEquals(a.validate(),"Invalid email");
    }
}