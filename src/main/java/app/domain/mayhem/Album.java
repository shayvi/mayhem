package app.domain.mayhem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "ALBUMS")
@XmlRootElement
@XmlType(propOrder={"name", "year", "artist"})
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR")
    private int year;

//    private List<String> genres;

    @JsonIgnoreProperties("album")
    @OneToMany(mappedBy = "album")
    private List<Song> songs = new ArrayList<Song>();

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return this.songs;
    }

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.LAZY)
    private Artist artist;

    //an empty constructor for REST
    public Album(){}

    public Album(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Album(String name, int year, List<Song> songs) {
        this.name = name;
        this.year = year;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void addSong(Song song) {
        if (this.songs.contains(song))
            return ;
        this.songs.add(song);
        song.setAlbum(this);
    }

    public void removeSong(Song song) {
        if (!this.songs.contains(song))
            return ;
        song.setAlbum(null);
        this.songs.remove(song);
    }

    /*Album factory method*/
    public static Album getAlbum(String name, int year){
        return new Album(name, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return year == album.year &&
                Objects.equals(name, album.name) &&
                Objects.equals(artist, album.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, artist);
    }
}
