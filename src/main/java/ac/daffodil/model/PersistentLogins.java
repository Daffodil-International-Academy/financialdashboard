package ac.daffodil.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by codin on 3/20/2018.
 */

@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="series")
    private String series;

    @Column(name="username")
    private String userName;

    @Column(name="token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat( pattern="yyyy-MM-dd")
    @Column(name="last_used")
    private Date lastUsed;

    public PersistentLogins() {
    }

    public PersistentLogins(String userName, String token, Date lastUsed) {
        this.userName = userName;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public String toString() {
        return "PersistentLogins{" +
                "series='" + series + '\'' +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                ", lastUsed=" + lastUsed +
                '}';
    }
}
