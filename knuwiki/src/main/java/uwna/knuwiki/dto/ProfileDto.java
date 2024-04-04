package uwna.knuwiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProfileDto {

    private String userName;
    @JsonProperty(value = "photo_url")
    private String photoUrl;
    @JsonProperty(value = "sum_dis")
    private int sumDistance;
    @JsonProperty(value = "walk_count")
    private Integer walkCount;

    public ProfileDto(String userName, String photoUrl, int sumDistance, Integer walkCount) {

        this.userName = userName;
        this.photoUrl = photoUrl;
        this.sumDistance = sumDistance;
        this.walkCount = walkCount;
    }

}
