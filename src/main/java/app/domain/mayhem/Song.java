package app.domain.mayhem;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SONGS")

@XmlRootElement
@XmlType(propOrder={"num", "name", "rate", "album"})
public class Song implements Serializable {


    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "NUMBER")
    private int num;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RATE")
    private int rate;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.LAZY)
    private Album album;

    @ManyToMany(mappedBy = "songs")
    private Set<MayhemUser> mayhemUsers = new HashSet<MayhemUser>();

    //an empty constructor for REST
    public Song(){}

    public Song(int num,String name){
        this(num, name,0);
    }

    public Song(int num, String name, int rate, Album album) {
        this.num = num;
        this.name = name;
        this.rate = rate;
        this.album = album;
    }

    public Song(int num, String name, int rate) {
        this.num = num;
        this.name = name;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "   " + num + ". " + name;
    }

    // Song factory method
    public static Song getSong(int num, String name){
        return new Song(num, name);
    }

    public static Song getSong(int num, String name, int rate, Album album){
        return new Song(num, name, rate, album);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Set<MayhemUser> getMayhemUsers() {
        return mayhemUsers;
    }

    public void setMayhemUsers(Set<MayhemUser> mayhemUsers) {
        this.mayhemUsers = mayhemUsers;
    }

}
