package app.domain.mayhem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "ARTISTS")
@XmlRootElement
@XmlType(propOrder={"name", "origin"})

@Embeddable
public class Artist implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ORIGIN")
    private String origin;


    @JsonIgnoreProperties("artist")
    @OneToMany(mappedBy = "artist")
    private List<Album> albums = new ArrayList<Album>();

    //an empty constructor for REST
    public Artist(){}

    public Artist(String name, String origin) {
        this.name = name;
        this.origin = origin;
    }

    public Artist(String name, String origin, List<Album> albums) {
        this.name = name;
        this.origin = origin;
        this.albums = albums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<Album> getAlbums() {
        return new ArrayList<Album>(this.albums);
    }

    public void addAlbum(Album album) {
        if (this.albums.contains(album))
            return ;
        this.albums.add(album);
        album.setArtist(this);
    }

    public void removeAlbum(Album album) {
        if (!this.albums.contains(album))
            return ;
        album.setArtist(null);
        this.albums.remove(album);
    }

    /*Artist factory method*/
    public static Artist getArtist(String name, String origin){
        return new Artist(name, origin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) &&
                Objects.equals(origin, artist.origin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, origin);
    }
}
