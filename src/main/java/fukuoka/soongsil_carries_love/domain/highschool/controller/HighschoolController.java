package fukuoka.soongsil_carries_love.domain.highschool.controller;

import fukuoka.soongsil_carries_love.domain.highschool.service.HighschoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
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

    @GetMapping("/highschool/ranking")
    public void getHighschoolRanking() {
        return ;
    }
}
