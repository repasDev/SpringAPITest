package demo.model;

import com.github.tsohr.JSONException;
import com.github.tsohr.JSONObject;

import javax.persistence.*;
import java.util.TimeZone;


@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String userName;
    private TimeZone timeZone;
    private String countryCode;
    private Long timesPlayed;

    public User() {
    }

    public User(Long id, String userName, TimeZone timeZone, String countryCode, Long timesPlayed) {
        this.id = id;
        this.userName = userName;
        this.timeZone = timeZone;
        this.countryCode = countryCode;
        this.timesPlayed = timesPlayed;
    }

    public User(String userName, TimeZone timeZone, String countryCode, Long timesPlayed) {
        this.userName = userName;
        this.timeZone = timeZone;
        this.countryCode = countryCode;
        this.timesPlayed = timesPlayed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(Long timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public JSONObject getJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("userName", userName);
        jsonObject.put("timeZone", timeZone.getID());
        jsonObject.put("countryCode", countryCode);
        jsonObject.put("timesPlayed", timesPlayed);
        return jsonObject;
    }
}
