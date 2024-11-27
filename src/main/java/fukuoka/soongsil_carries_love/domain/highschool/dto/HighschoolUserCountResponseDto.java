package fukuoka.soongsil_carries_love.domain.highschool.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HighschoolUserCountResponseDto {
    private String highschoolCode;
    private long userCount;
}