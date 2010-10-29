package com.example.model;

import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.*;

import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Query(name="latestGreetings",
       value="select from com.example.model.Greeting order by date desc range 0,5")
public class Greeting implements Serializable
{
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    private User author;

    @Persistent(nullValue = NullValue.EXCEPTION)
    private String content;

    @Persistent(nullValue = NullValue.EXCEPTION)
    private Date date;

    public Greeting(User author, String content, Date date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
