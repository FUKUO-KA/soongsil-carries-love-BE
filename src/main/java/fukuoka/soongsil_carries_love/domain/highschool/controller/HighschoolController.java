package fukuoka.soongsil_carries_love.domain.highschool.controller;

import fukuoka.soongsil_carries_love.domain.highschool.service.HighschoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Neis-api")
public class HighschoolController implements HighschoolApi{

    private final HighschoolService highschoolService;

    @PostMapping("/fetch")
    public String fetchAndSaveHighschool() {
        highschoolService.fetchAndSaveHighschoolData();
        return "Highschool data has been successfully fetched and saved.";
    }
}
