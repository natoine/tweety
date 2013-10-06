package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Tweet extends Model
{
	@Id
	public long id ;
	public String commentaire ;
	public String username ;
	public Date creationDate ;
	
}
