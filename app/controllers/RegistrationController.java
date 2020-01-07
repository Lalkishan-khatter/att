//package controllers;
//
//import dao.DbConnector;
//import dto.RegistrationForm;
//import io.ebean.*;
//import models.Attendance;
//import models.Person;
//import models.Role;
//import play.Logger;
//import play.data.Form;
//import play.data.FormFactory;
//import play.db.DBComponents;
//import play.mvc.Controller;
//import play.mvc.Http;
//import play.mvc.Result;
//import play.mvc.Security;
//
//import javax.inject.Inject;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//
////@Security.Authenticated
//public class RegistrationController extends Controller {
//    public Result home(){
//        return ok(views.html.home.render());
//    }
//    public Result index() {
//        return ok(views.html.register.render(""));
//        ////////////////////////////////////////////////////////////////////////////////////////
////        if(attendance != null && attendance.size()>0){
////            for(Attendance attendance_ : attendance){
////                name = name.concat(attendance_.getEmail()).concat(", ");
////         }
////        }
////        return ok(views.html.register.render(name));
//    }
//
//    private FormFactory formFactory;
//
//    @Inject
//    public RegistrationController(FormFactory formFactory) {
//        this.formFactory = formFactory;
//    }
//
//    public Result saveRegistration(Http.Request request) {
//        Form<RegistrationForm> registrationForm = formFactory.form(RegistrationForm.class).bindFromRequest(request);
//        if (registrationForm.hasErrors()) {
//            return ok(views.html.register.render(" Kindly enter all details"));
//        }
//        RegistrationForm form = registrationForm.get();
//        String email = form.getEmail();
//        String name = form.getName();
//        String password1 = form.getPassword1();
//
//
//        List<Person> objects = DbConnector.find(Person.class).select("email,id").where().eq("email", email).findList();
//        if (objects.isEmpty()) {
//
//            Person person = new Person();
//            Role role= Role.find.query().where().like("role_id","2").findOne();
//            person.setName(name);
//            person.setEmail(email);
//            person.setPassword(password1);
//            person.setCreatedOn(LocalDateTime.now());
//            person.setRole(role);
//            DbConnector.save(person);
//
//            return ok(views.html.login.render("Registration Successful"));
//        } else {
//            return ok(views.html.login.render("Email already exists, kindly login here"));
//
//        }
//    }
//}
package controllers;

import dao.*;
import dto.*;
import io.ebean.*;
import models.*;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.db.DBComponents;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class RegistrationController extends Controller {
    public Result home() {
        return ok(views.html.home.render());
    }

    public Result index() {
        return ok(views.html.register.render(""));
    }

    private FormFactory formFactory;

    @Inject
    public RegistrationController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result saveRegistration(Http.Request request) {
        Form<RegistrationForm> registrationForm = formFactory.form(RegistrationForm.class).bindFromRequest(request);
        if (registrationForm.hasErrors()) {

            BaseResponse response = new BaseResponse();
            response.setStatus(400);
            response.setReason("Please do not leave any field blank");
            return ok(Json.toJson(response));

//            return ok(views.html.register.render(" Kindly enter all details"));
        }
        RegistrationForm form = registrationForm.get();
        String email = form.getEmail();
        String name = form.getName();
        String password1 = form.getPassword1();


        List<Person> objects = DbConnector.find(Person.class).select("email,id").where().eq("email", email).findList();
        if (objects.isEmpty()) {

            Person person = new Person();
            Role role = Role.find.query().where().like("role_id", "2").findOne();
            person.setName(name);
            person.setEmail(email);
            person.setPassword(password1);
            person.setCreatedOn(LocalDateTime.now());
            person.setRole(role);
            DbConnector.save(person);

            BaseResponse response = new BaseResponse();
            response.setStatus(200);
            response.setReason("successfully saved");
            return ok(Json.toJson(response));

//            return ok(views.html.login.render("Registration Successful"));
        } else {

            BaseResponse response = new BaseResponse();
            response.setStatus(200);
            response.setReason("Email already exists, kindly login here");
            return ok(Json.toJson(response));
//            return ok(views.html.login.render("Email already exists, kindly login here"));

        }
    }
}