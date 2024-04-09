package uwna.knuwiki.dto;

import lombok.Getter;
import uwna.knuwiki.entity.Role;

@Getter
public class LoginDto {

    private String memberId;
    private String username;
    private Role role;

    public LoginDto(String memberId, String username, Role role) {
        this.memberId = memberId;
        this.username = username;
        this.role = role;
    }
}
