package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Tweet extends Model
{
	@Id
	public long id;
	
	public String comment;
	public String username;
	public Date creationDate;
	
	public static Finder<Long, Tweet> find = new Finder<Long, Tweet>(Long.class, Tweet.class);
	
	public static List<Tweet> findAll()
	{
		return find.all();
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public long getId() {
		return id;
	}
}
