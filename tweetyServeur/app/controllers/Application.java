package controllers;

import play.data.Form;

import models.Tweet;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	final static Form<Tweet> tweetForm = Form.form(Tweet.class);

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result allTweet()
    {
    	return ok(views.html.wall.render(Tweet.findAll(), tweetForm));
    }

}
