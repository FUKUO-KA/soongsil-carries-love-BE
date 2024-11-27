package fukuoka.soongsil_carries_love.domain.highschool.controller;

import fukuoka.soongsil_carries_love.domain.highschool.dto.*;
import fukuoka.soongsil_carries_love.domain.highschool.service.HighschoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/highschool")
public class HighschoolController implements HighschoolApi{

    private final HighschoolService highschoolService;

    @PostMapping("/neis-api/fetch")
    public ResponseEntity<String> fetchAndSaveHighschoolData() {
        try {
            highschoolService.fetchAndSaveHighschoolData();
            return ResponseEntity.ok("Highschool data fetched and saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch and save Highschool data.");
        }
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllSchoolNames() {
        List<String> schoolNames = highschoolService.getAllSchoolNames();
        return ResponseEntity.ok(schoolNames);
    }

    @PostMapping("/user-count")
    public ResponseEntity<HighschoolUserCountResponseDto> getUserCount(@RequestBody HighschoolUserCountRequestDto requestDto) {
        HighschoolUserCountResponseDto response = highschoolService.getUserCountByHighschool(requestDto.getHighschoolCode());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/gender-ratio")
    public ResponseEntity<HighschoolGenderRatioResponseDto> getGenderRatio(@RequestBody HighschoolUserCountRequestDto requestDto) {
        HighschoolGenderRatioResponseDto response = highschoolService.getGenderRatioByHighschool(requestDto.getHighschoolCode());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student-count")
    public ResponseEntity<HighschoolUserStudentIdResponseDto> getStudentCountByYear(
            @RequestParam String highschoolCode) {
        HighschoolUserStudentIdResponseDto responseDto = highschoolService.getStudentCountByYear(highschoolCode);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/highschool-ranking")
    public ResponseEntity<List<HighschoolRankingofUserCountResponseDto>> getHighschoolRanking() {
        List<HighschoolRankingofUserCountResponseDto> response = highschoolService.getUserCountByHighschool();
        return ResponseEntity.ok(response);
    }}
