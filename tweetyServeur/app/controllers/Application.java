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
    	//TODO remove test and link to model
    	//Just for the test
    	List<Tweet> tweets = new ArrayList<Tweet>();
    	Tweet tweet1 = new Tweet();
    	tweet1.commentaire = "bla bla bla" ;
    	tweet1.username = "toto" ;
    	tweet1.creationDate = new Date();
    	tweets.add(tweet1);
    	
    	Tweet tweet2 = new Tweet();
    	tweet2.commentaire = "again bla bla bla" ;
    	tweet2.username = "lulu" ;
    	tweet2.creationDate = new Date();
    	tweets.add(tweet2);
    	
    	if(request().accepts("text/html"))
    		return ok(views.html.wall.render(tweets, "bonjour " + username));
    	else if(request().accepts("application/json"))
    		return ok(Json.toJson(tweets));
    	else if (request().accepts("application/rdf+xml"))
    		return ok("this will be RDF XML");
    	return badRequest();
    }
    
}