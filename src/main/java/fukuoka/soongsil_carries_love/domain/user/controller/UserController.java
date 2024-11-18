package fukuoka.soongsil_carries_love.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/{highschoolId}/gender")
    public void getGenderStats() {
        return ;
    }


    @GetMapping("/{highschoolId}/student-number")
    public void getYearStats() {
        return ;
    }
}
