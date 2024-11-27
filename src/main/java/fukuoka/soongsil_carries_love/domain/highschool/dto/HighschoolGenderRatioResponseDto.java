package fukuoka.soongsil_carries_love.domain.highschool.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HighschoolGenderRatioResponseDto {
    private String highschoolCode;
    private double maleRatio;
    private double femaleRatio;
    private double noneRatio;
}