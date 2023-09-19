package com.example.demo;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Photo {
    private Long id;
    public String filter;
    public String album;
    public String image;

    protected Photo() {
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

	public String getfilter() {
		return filter;
	}

	public void setfilter(String filter) {
		this.filter = filter;
	}

	public String getalbum() {
		return album;
	}

	public void setalbum(String album) {
		this.album = album;
	}



	public void setId(Long id) {
		this.id = id;
	}
     
    public void setimage(String image) {
    	this.image = image;
    }
    
    public String getimage() {
    	return image;
    }
  
}