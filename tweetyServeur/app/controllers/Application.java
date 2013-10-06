package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Tweet;

import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	static Form<Tweet> tweetForm = Form.form(Tweet.class);
	
    public static Result index() 
    {
        return ok(index.render());
    }
    
    public static Result listTweets(String username)
    {
    	List<Tweet> tweets = Tweet.all();
    	
    	if(request().accepts("text/html"))
    		return ok(views.html.wall.render(tweets, "bonjour " + username, tweetForm));
    	else if(request().accepts("application/json"))
    		return ok(Json.toJson(tweets));
    	else if (request().accepts("application/rdf+xml"))
    		return ok("this will be RDF XML");
    	return badRequest();
    }
 
    public static Result submitTweet()
    {
    	Tweet tweet = tweetForm.bindFromRequest().get();
    	tweet.save();
    	return redirect(routes.Application.listTweets("visiteur"));
    }
}