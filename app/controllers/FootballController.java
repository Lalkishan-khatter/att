package controllers;
import dao.DbConnector;
import dto.FootballForm;
import dto.foot1;
import dto.foot2;
import io.ebean.SqlRow;
import models.Football;
import play.cache.SyncCacheApi;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class FootballController extends Controller {
    private FormFactory formFactory;
    private SyncCacheApi cache;

    @Inject
    public  FootballController(FormFactory formFactory, SyncCacheApi cache) {
        this.formFactory = formFactory;
        this.cache = cache;
    }
//
//    public Result showPage(Http.Request request){
//
//        List<Football> fulldata = Football.finds.query().where().like("home_team","Scotland").findList();
//        List<FootballForm> football = new ArrayList<>();
//        for(Football d : fulldata){
//            FootballForm foot=new FootballForm();
//            foot.setCity(d.getCity());
//            foot.setCountry(d.getCountry());
//            foot.setId(d.getId());
//            foot.setAway_score(d.getAway_score());
//            foot.setHome_score(d.getHome_score());
//            football.add(foot);
//        }
//
//        if (football!= null && fulldata.size() > 0 ) {
//            return ok(views.html.Football.render(football));
//        }
//
//        else{return TODO();}
//    }

    public Result showPage(Http.Request request){

//        List<Football> fulldata = Football.finds.query().where().like("home_team","Scotland").findList();
        List<SqlRow> data = DbConnector.createSqlQuery("select away_team,count(away_team),sum(away_score) as away_score from data.results where (away_score > home_score) group by away_team order by away_score desc limit 10").findList();
        List<foot1> dataList = new ArrayList<>();
        for (SqlRow row : data) {
            foot1 fo= new foot1();
            fo.setName(row.getString("away_team"));
            fo.setValue(row.getInteger("away_score"));

            dataList.add(fo);
        }

        foot2 foo = new foot2();
        for(foot1 store : dataList){
            foo.getName().add(store.getName());
            foo.getValue().add(store.getValue());
        }
        return ok(Json.toJson(foo));
    }
    public Result showPage1(Http.Request request){

//        List<Football> fulldata = Football.finds.query().where().like("home_team","Scotland").findList();
        List<SqlRow> data1 = DbConnector.createSqlQuery("select home_team,count(home_team),sum(home_score) as home_score from data.results where (home_score > away_score) group by home_team order by home_score desc limit 10;").findList();
        List<foot1> dataList1 = new ArrayList<>();
        for (SqlRow row : data1) {
            foot1 fo = new foot1();
            fo.setName(row.getString("home_team"));
            fo.setValue(row.getInteger("home_score"));

            dataList1.add(fo);
        }

        foot2 foo = new foot2();
        for(foot1 store : dataList1){
            foo.getName().add(store.getName());
            foo.getValue().add(store.getValue());
        }
        return ok(Json.toJson(foo));
    }
    public Result showPage2(Http.Request request){

//        List<Football> fulldata = Football.finds.query().where().like("home_team","Scotland").findList();
        List<SqlRow> data2 = DbConnector.createSqlQuery("SELECT home_team, away_team, tournament, count(*) as count from data.results where home_team = 'England' or away_team = 'England' group by tournament order by count(*) desc;").findList();
        List<foot1> dataList2= new ArrayList<>();
        for (SqlRow row : data2) {
            foot1 fo = new foot1();
            fo.setName(row.getString("tournament"));
            fo.setValue(row.getInteger("count"));

            dataList2.add(fo);
        }

        foot2 foo = new foot2();
        for(foot1 store : dataList2){
            foo.getName().add(store.getName());
            foo.getValue().add(store.getValue());
        }
        return ok(Json.toJson(foo));
    }
    public Result showPage3(Http.Request request){

//        List<Football> fulldata = Football.finds.query().where().like("home_team","Scotland").findList();
        List<SqlRow> data3 = DbConnector.createSqlQuery("SELECT home_team, away_team, tournament, count(*) as count from data.results where home_team = 'Scotland' or away_team = 'Scotland' group by tournament order by count(*) desc;").findList();
        List<foot1> dataList3= new ArrayList<>();
        for (SqlRow row : data3) {
            foot1 fo = new foot1();
            fo.setName(row.getString("tournament"));
            fo.setValue(row.getInteger("count"));

            dataList3.add(fo);
        }

        foot2 foo = new foot2();
        for(foot1 store : dataList3){
            foo.getName().add(store.getName());
            foo.getValue().add(store.getValue());
        }
        return ok(Json.toJson(foo));
    }


    public Result data(Http.Request request){
        return ok(views.html.data.render());
    }
}
