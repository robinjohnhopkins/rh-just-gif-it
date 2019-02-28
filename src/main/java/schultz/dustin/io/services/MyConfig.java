package schultz.dustin.io.services;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
@ConfigurationProperties(prefix = "my")
public class MyConfig {

    @NotNull
    @Pattern( regexp = "my.{5}zip", message = "regex check should match my.{5}zip")
    private String myString;
    private long myLong;
    private boolean myBoolean;

    private String artString;
    private boolean enableArtString;

    public String getArtString() {
        return artString;
    }

    public void setArtString(String artString) {
        this.artString = artString;
    }

    public boolean isEnableArtString() {
        return enableArtString;
    }

    public void setEnableArtString(boolean enableArtString) {
        this.enableArtString = enableArtString;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public long getMyLong() {
        return myLong;
    }

    public void setMyLong(long myLong) {
        this.myLong = myLong;
    }

    public boolean isMyBoolean() {
        return myBoolean;
    }

    public void setMyBoolean(boolean myBoolean) {
        this.myBoolean = myBoolean;
    }
}
