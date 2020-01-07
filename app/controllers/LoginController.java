package controllers;

import dao.DbConnector;
import dto.*;
import io.ebean.SqlRow;
import models.Attendance;
import models.Person;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;


import javax.inject.Inject;

import play.cache.*;

import java.util.List;

public class LoginController extends Controller {
    public Result index() {
        return ok(views.html.login.render(" "));
    }

    private FormFactory formFactory;
    private SyncCacheApi cache;

    @Inject
    public LoginController(FormFactory formFactory, SyncCacheApi cache) {
        this.formFactory = formFactory;
        this.cache = cache;
    }

    public Result saveLogin(Http.Request request) {
        Form<LoginForm> loginForm = formFactory.form(LoginForm.class).bindFromRequest(request);
        if (loginForm.hasErrors()) {
            BaseResponse response = new BaseResponse();
            response.setStatus(400);
            response.setReason("Please do not leave any field blank");
            return ok(Json.toJson(response));
           // return ok(views.html.login.render(" Kindly enter all details"));
        }
        LoginForm form;
        form = loginForm.get();
        String email = form.getEmail();
        String password = form.getPassword();

        //     List<SqlRow> objects = DbConnector.createSqlQuery("Select email,password from person WHERE email= ('" + email + "') AND password= ('" + password + "')  ").findList();
//               List<Person> objects =DbConnector.find(Person.class).where().eq("email",email).
//                     eq("password",password).findList();
        List<Person> objects = Person.find.query().select("email,password").where().eq("email", email).eq("password", password).findList();
        if (objects.isEmpty()) {
            return ok(views.html.login.render("Invalid email or password"));
        } else {
            //check in cache if the user is already logged in or not
//            CompletionStage<Optional<String>> loggedInEmail =  cache.getOptional("email");
//            String loggedInEmail =  cache.getOptional("email").toString();
//            if(!StringUtils.isNullOrEmpty(loggedInEmail)){
//                cache.set("email", loggedInEmail, 60 * 3);
//            }else{
//                cache.set("email", email, 60 * 3);
//            }
            cache.set("cacheemail", email, 120 * 1);

           Person person=Person.find.query().where().like("email",email).findOne();
            if(person.getRole().getRole_id()==1){
                return redirect("/persondetails").addingToSession(request, "sessionemail", email);
            }else
            {
                BaseResponse response = new BaseResponse();
                response.setStatus(200);
                response.setReason("Login Successful");
                return ok(Json.toJson(response)).addingToSession(request, "sessionemail", email);
           // return redirect("/attendance").addingToSession(request, "sessionemail", email);
        }
    }}

    public Result logout(Http.Request request) {
        cache.remove("cacheemail");
        return ok(views.html.login.render("")).withNewSession();
    }
        public Result forgotpassword (Http.Request request){
            return ok(views.html.forgotpassword.render(""));
        }


        public Result getpassword (Http.Request request){
            Form<PasswordForm> passwordForm = formFactory.form(PasswordForm.class).bindFromRequest(request);
            if (passwordForm.hasErrors()) {
                return ok(views.html.forgotpassword.render(" Kindly enter all details"));
            }
            PasswordForm form;
            form = passwordForm.get();
            Integer fid=form.getPid();
            String fname=form.getName();
            String femail = form.getEmail();
            String fpassword1=form.getPassword1();

            Person person=Person.find.query().where().like("email", femail).setMaxRows(1).findOne();
            if(person.getId()==fid && (person.getName().equals(fname)))
            {
            person.setPassword(fpassword1);
            DbConnector.update(person);
            Logger.info("reached " +femail+ fname);
            return ok(views.html.login.render("Password changed successfully "));}
            else{
                return ok(views.html.forgotpassword.render("Enter Correct details"));
            }
        }
}

