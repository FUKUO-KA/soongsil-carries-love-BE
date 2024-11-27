package fukuoka.soongsil_carries_love.domain.highschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class HighschoolUserStudentIdResponseDto {

    private Map<String, Long> studentIdDistribution;

}
