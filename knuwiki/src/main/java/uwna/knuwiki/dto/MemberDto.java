package uwna.knuwiki.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uwna.knuwiki.entity.Member;
import uwna.knuwiki.entity.Role;
import uwna.knuwiki.entity.Sanctions;
import uwna.knuwiki.entity.State;

import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String id;
    private String username;
    private String password;
    private Role role;
    private State state;
    private List<Sanctions> sanctionsList;

    public MemberDto(String id, String username, String password, Role role, State state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.state = state;
    }

    public MemberDto(String id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }



}
