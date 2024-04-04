package uwna.knuwiki.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MemberJoinForm {

    private String username;
    private String password;

    public MemberJoinForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
