package app.domain.mayhem;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "USERS")
@XmlRootElement
@XmlType(propOrder={"username", "songs"})
public class MayhemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USERNAME")
    private String username;

    public MayhemUser() {
    }

    public MayhemUser(String username) {
        this.username = username;
    }

    public MayhemUser(String username, Set<Song> songs) {
        this.username = username;
        this.songs = songs;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_songs",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") }
    )
    Set<Song> songs = new HashSet<Song>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addSongToUserSongs(Song song){
        this.songs.add(song);
    }

    public void removeSongFromUserSongs(Song song){
        this.songs.remove(song);
    }
}
