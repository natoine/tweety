package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

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
    	List<Tweet> tweets ;
    	if(username.equals("visiteur"))
    		tweets = Tweet.all();
    	else 
    		tweets = Tweet.findByUsername(username);
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
    	Tweet.create(tweet);
    	return redirect(routes.Application.listTweets("visiteur"));
    }
    
    public static Result listTweetsFromTo()
    {
    	if(request().accepts("application/json"))
    	{
    		JsonNode body = request().body().asJson();
    		System.out.println(body);
    		int from = body.get("from").asInt();
        	int to =  body.get("to").asInt();
        	List<Tweet> tweets = Tweet.findNext(from, to);
        	System.out.println(Json.toJson(tweets));
        	return ok(Json.toJson(tweets));
    	}
    	return badRequest();
    }
}