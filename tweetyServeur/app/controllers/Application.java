package controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;

import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    
    public static Result listTweets(String username)
    {
    	List<Tweet> tweets = new ArrayList<Tweet>();
    	
    	if(request().accepts("text/html"))
    		return ok(views.html.wall.render(tweets, "bonjour " + username));
    	else if(request().accepts("application/json"))
    		return ok(Json.toJson(tweets));
    	else if (request().accepts("application/rdf+xml"))
    		return ok("this will be RDF XML");
    	return badRequest();
    }
    
}