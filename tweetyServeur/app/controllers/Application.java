package controllers;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import models.Tweet;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	static Form<Tweet> tweetForm = Form.form(Tweet.class);
	
	public static final String NS_FOAF = "http://xmlns.com/foaf/0.1/";
	
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
    	{
    		Model model = ModelFactory.createDefaultModel();
    		String personURI    = "http://www.natoine.fr#me";
    		String strgivenName    = "Antoine";
    		String strfamilyName   = "Seilles";
    		String strnickName 	= "Natoine";
    		String strfullName     = strgivenName + " " + strfamilyName;
    		
    		//ajout d un namespace
    		model.setNsPrefix("foaf", NS_FOAF);
    		//creation des proprietes
    		Property firstname = model.createProperty( NS_FOAF + "firstName" );
    		Property familyName = model.createProperty( NS_FOAF + "familyName" );
    		Property nick = model.createProperty( NS_FOAF + "nick" );
    		Property name = model.createProperty( NS_FOAF + "name" );
    		
    		//TODO
    		//need to create a foaf:Person not a generic resource
    		Resource person = model.createResource(personURI);
    		
    		person.addProperty(firstname, strgivenName);
    		person.addProperty(familyName, strfamilyName);
    		person.addProperty(nick, strnickName);
    		person.addProperty(name, strfullName);
    		
    		OutputStream out = new ByteArrayOutputStream();
			
			model.write(out, "RDF/XML-ABBREV");
	    		
	    	return ok(out.toString());
    	}
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
    	System.out.println(request().getHeader(ACCEPT));
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