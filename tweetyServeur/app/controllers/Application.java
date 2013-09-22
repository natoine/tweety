package controllers;

import models.Tweet;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result allTweet()
    {
    	return ok(views.html.wall.render(Tweet.findAll()));
    }

}
