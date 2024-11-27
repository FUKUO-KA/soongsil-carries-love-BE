package fukuoka.soongsil_carries_love.domain.highschool.controller;

import fukuoka.soongsil_carries_love.domain.highschool.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface HighschoolApi {

    @Operation(
            summary = "[Highschool] 고등학교 데이터 가져오기",
            description = "NEIS API를 호출하여 고등학교 데이터를 가져오고 DB에 저장합니다. 이 API는 프론트엔드에서 사용하지 않습니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - 데이터 저장 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request - 잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - 인증 실패"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - 서버 오류")
    })
    @Parameters({
            @Parameter(name = "KEY", description = "NEIS API 인증키 (필수)", required = true),
            @Parameter(name = "Type", description = "응답 데이터 형식 (xml/json)", required = true, example = "json"),
            @Parameter(name = "pIndex", description = "페이지 위치 (선택)", example = "1"),
            @Parameter(name = "pSize", description = "페이지 당 요청 수 (선택)", example = "100"),
            @Parameter(name = "SCHUL_KND_SC_NM", description = "학교 종류 필터 (선택)", example = "고등학교")
    })
    @PostMapping("/fetch")
    ResponseEntity<String> fetchAndSaveHighschoolData();

    @Operation(
            summary = "[Highschool] 고등학교 이름 조회 API",
            description = "DB에 저장된 모든 고등학교의 이름을 조회합니다. 이 API는 사용자가 등록 가능한 고등학교 목록을 제공하기 위해 사용됩니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - 성공적으로 고등학교 이름 리스트 반환"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - 서버 오류")
    })
    @GetMapping("/names")
    ResponseEntity<List<String>> getAllSchoolNames();

    @Operation(
            summary = "[Highschool] 동일 고등학교 내 유저 수 조회",
            description = "특정 고등학교 코드로 등록된 사용자의 총 인원 수를 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - 성공적으로 유저 수 반환"),
            @ApiResponse(responseCode = "400", description = "Bad Request - 잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - 서버 오류")
    })
    @PostMapping("/user-count")
    ResponseEntity<HighschoolUserCountResponseDto> getUserCount(@RequestBody HighschoolUserCountRequestDto requestDto);


    @Operation(
            summary = "[Highschool] 동일 고등학교 내 성별 비율 조회",
            description = "특정 고등학교 코드로 등록된 사용자의 남성, 여성, 미지정 비율을 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - 성공적으로 성별 비율 반환"),
            @ApiResponse(responseCode = "400", description = "Bad Request - 잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - 서버 오류")
    })
    @PostMapping("/gender-ratio")
    ResponseEntity<HighschoolGenderRatioResponseDto> getGenderRatio(@RequestBody HighschoolUserCountRequestDto requestDto);


    @Operation(
            summary = "[Highschool] 고등학교 코드를 기반으로 학번별 학생 분포 조회",
            description = "고등학교 코드를 기반으로 학번별 학생 분포를 반환합니다. (예: 19: 1, 20: 3, 21: 1)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request - 잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - 서버 오류")
    })
    ResponseEntity<HighschoolUserStudentIdResponseDto> getStudentCountByYear(@RequestParam String highschoolCode);


    @Operation(
            summary = "[Highschool] 학교 별 유저 수 기반의 랭킹 조회",
            description = "각 학교에 속한 유저 수를 조회. 내림차순 정렬"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request - 잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - 서버 오류")
    })
    ResponseEntity<List<HighschoolRankingofUserCountResponseDto>> getHighschoolRanking();

}