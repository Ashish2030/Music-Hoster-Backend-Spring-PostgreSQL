package com.trail.musicalhost.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Arrays;
@Entity
public class Music {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String musicId;
    private String name;
    private String description;
    private String type;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Lob
    private byte[] data;
    public String getMusicId() {
        return musicId;
    }
    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }
    public byte[] getData() {
        return data;
    }
    //Constructors
    public Music(){
        super();
    }
    public Music(  String name, String type,byte[] data) {
        this.name = name;
        this.type = type;
        this.data=data;
    }
    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }

    //Relation
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties(allowSetters = true)
    private User user;
    //Getters and Setters
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
