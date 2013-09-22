package controllers;


import java.util.Date;

import play.data.Form;
import models.Tweet;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import play.data.Form;

public class Application extends Controller {
	
	final static Form<Tweet> tweetForm = Form.form(Tweet.class);

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result allTweet()
    {
    	if (request().accepts("text/html")) return ok(views.html.wall.render(Tweet.findAll(), tweetForm));
    	else if(request().accepts("application/json")) return ok(Json.toJson(Tweet.findAll()));
    	return badRequest();
    }

    public static Result createTweet()
    {
    	Form<Tweet> form = Form.form(Tweet.class).bindFromRequest();
    	Tweet tweet = new Tweet();
    	tweet.setComment(form.field("comment").value());
    	tweet.setUsername(form.field("username").value());
    	tweet.setCreationDate(new Date());
    	Tweet.create(tweet);
    	return redirect(routes.Application.allTweet());
    }
}
