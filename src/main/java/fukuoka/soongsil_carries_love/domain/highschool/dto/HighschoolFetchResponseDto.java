package fukuoka.soongsil_carries_love.domain.highschool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HighschoolFetchResponseDto {
    @JsonProperty("SD_SCHUL_CODE")
    private String schoolCode; // JSON의 SD_SCHUL_CODE에 대응
    @JsonProperty("SCHUL_NM")
    private String schoolName; // JSON의 SCHUL_NM에 대응
    @JsonProperty("COEDU_SC_NM")
    private String coeduType;  // JSON의 COEDU_SC_NM에 대응
    @JsonProperty("SIDO")
    private String sido;

}
