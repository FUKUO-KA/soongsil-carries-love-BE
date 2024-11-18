package fukuoka.soongsil_carries_love.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserApi {

    @Operation(summary = "[User] 본인 고등학교 내의 성별 분포 조회 API", description = "성별 인원 분포")
    @GetMapping("/{highschoolId}/gender")
    void getGenderStats();


    @Operation(summary = "[User] 본인 고등학교의 학번별 인원 수 조회 API", description = "학번별 인원 분포")
    @GetMapping("/{highschoolId}/student-number")
    void getYearStats();
}
