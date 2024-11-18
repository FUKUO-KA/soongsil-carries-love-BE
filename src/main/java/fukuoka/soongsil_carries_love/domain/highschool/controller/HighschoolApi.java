package fukuoka.soongsil_carries_love.domain.highschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface HighschoolApi {

    @Operation(summary = "[Highschool] 고등학교 정보 가져오기", description = "SCHUL_KND_SC_NM(학교종류명) = \"고등학교인 데이터\" 가져와 DB에 저장, school_code가 pk")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK, 성공"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, 인증 실패"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error, 서버 오류")
    })
//    @Parameters({
//            @Parameter(name = "KEY", description = "Neis API 인증키", required = true),
//            @Parameter(name = "Type", description = "응답 데이터 형식 (xml/json)", required = true, example = "json"),
//            @Parameter(name = "pIndex", description = "페이지 위치", required = false, example = "1"),
//            @Parameter(name = "pSize", description = "페이지 당 요청 숫자", required = false, example = "100"),
//            @Parameter(name = "SCHUL_KND_SC_NM", description = "학교 종류", required = false, example = "고등학교")
//    })
    @PostMapping("/fetch")
    ResponseEntity<String> fetchAndSaveHighschoolData();


    @Operation(summary = "[Highschool] 고등학교별 인원 수 순위 조회 API", description = "고등학교, 인원 수 순위 리스트")
    @GetMapping("/ranking")
    void getHighschoolRanking();
}
