package fukuoka.soongsil_carries_love.domain.highschool.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HighschoolRankingofUserCountResponseDto {
    private String schoolName;
    private Long userCount;
}
