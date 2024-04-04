package uwna.knuwiki.dto;

import lombok.Data;
import uwna.knuwiki.entity.Member;

@Data
public class MemberDto {

    private String id;
    private String password;
    private String name;

    public MemberDto() {
    }

    public MemberDto(Member member) {
        this.id = member.getId().toString();
        this.password = member.getPassword();
        this.name = member.getUsername();
    }
}
