package fukuoka.soongsil_carries_love.domain.user.controller;

import fukuoka.soongsil_carries_love.domain.user.dto.JoinDto;
import fukuoka.soongsil_carries_love.domain.user.dto.LoginDto;
import fukuoka.soongsil_carries_love.domain.user.dto.UserInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserApi {

    @Operation(
            summary = "[User] 회원가입 API",
            description = "필수/선택 항목 기반으로 회원가입 진행"
    )
    @Parameters({
//            @Parameter(name = "name", description = "사용자의 이름", required = false, example = "Ryu"),
            @Parameter(name = "password", description = "(필수)사용자의 비밀번호(암호화 후 저장)", required = true, example = "1111"),
            @Parameter(name = "email", description = "(필수)사용자의 이메일 주소 (@soongsil.ac.kr, @ssu.ac.kr만 가능)", required = true, example = "imHK@soongsil.ac.kr"),
            @Parameter(name = "college", description = "(선택)사용자의 단과대학", required = false, example = "IT대학"),
            @Parameter(name = "department", description = "(선택)사용자의 학과/학부", required = false, example = "컴퓨터학부"),
            @Parameter(name = "nickname", description = "(필수)사용자의 닉네임", required = true, example = "나는 효경"),
            @Parameter(name = "studentId", description = "(필수)사용자의 학번", required = true, example = "19"),
            @Parameter(name = "gender", description = "(필수)사용자의 성별 (MALE, FEMALE, NONE 중 하나만 가능)", required = true, example = "MALE"),
            @Parameter(name = "schoolCode", description = "(필수)사용자가 선택한 고등학교 코드", required = true, example = "7010060")
    })
    @GetMapping("/join")
    ResponseEntity<String> joinUser(@ModelAttribute JoinDto joinDto);

    @Operation(summary = "[User] 로그인 API", description = "미가입자: 400; 비밀번호 틀림: 401; 로그인 성공: 200;")
    @PostMapping("/login")
    ResponseEntity<UserInfoDto> login(@RequestBody LoginDto loginDto) throws Exception;


    @Operation(summary = "[User] 본인 고등학교 내의 성별 분포 조회 API", description = "성별 인원 분포")
    @GetMapping("/gender-stats")
    void getGenderStats(@RequestHeader("email") String email);


    @Operation(summary = "[User] 본인 고등학교의 학번별 인원 수 조회 API", description = "학번별 인원 분포")
    @GetMapping("/hakbeon-stats")
    void getHakbeonStats(@RequestHeader("email") String email);

    ResponseEntity<UserInfoDto> getUserInfo(@RequestHeader("email") String email);
}
