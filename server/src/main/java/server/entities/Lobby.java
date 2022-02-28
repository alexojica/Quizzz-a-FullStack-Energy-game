package server.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.ArrayList;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

@Entity
@Table(name = "Lobby")
public class Lobby {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "token")
    public String token;

    @Column(name = "isPublic")
    public Boolean isPublic;

    @Column(name = "hostId")
    public Integer hostId;

    @Column(name = "listOfPlayers")
    public ArrayList<Integer> playerIds;

    public Lobby() {
        // for object mapper
    }

    /**
     * Instantiates the public lobby with empty list of players
     * hostId remains null, as there is no host of a public lobby
     * @param token
     */
    public Lobby(String token) {
        this.token = token;
        this.isPublic = true;
        this.playerIds = new ArrayList<>();
        this.hostId = null;
    }

    /**
     * Instantiates a private lobby given a token, and a host id
     * @param token
     * @param hostId
     */
    public Lobby(String token, Integer hostId)
    {
        this.token = token;
        this.hostId = hostId;
        this.playerIds = new ArrayList<>();
        this.isPublic = false;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public ArrayList<Integer> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(ArrayList<Integer> playerIds) {
        this.playerIds = playerIds;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, MULTI_LINE_STYLE);
    }
}
