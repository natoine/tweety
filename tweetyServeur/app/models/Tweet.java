package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.PagingList;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Tweet extends Model
{
	@Id
	public long id ;
	public String commentaire ;
	public String username ;
	public Date creationDate ;
	
	public static Finder<Long, Tweet> find = new Finder<Long, Tweet>(Long.class, Tweet.class);
	
	public static List<Tweet> all()
	{
		//return find.all();
		return find.setMaxRows(2).findList();
	}
	
	public static List<Tweet> findByUsername(String username)
	{
		return find.where().eq("username", username).findList();
	}
	
	public static Tweet create(Tweet tweet)
	{
		tweet.creationDate = new Date();
    	tweet.save();
    	return tweet;
	}
	
	public static List<Tweet> findNext(int from, int nb)
	{
		return find.setFirstRow(from).setMaxRows(nb).findList();
	}
}
