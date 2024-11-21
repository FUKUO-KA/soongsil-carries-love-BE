package fukuoka.soongsil_carries_love.domain.user.controller;

import fukuoka.soongsil_carries_love.domain.user.dto.JoinDto;
import fukuoka.soongsil_carries_love.domain.user.service.JoinService;
import fukuoka.soongsil_carries_love.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JoinService joinService;

    @PostMapping("/join")   // 회원가입
    public ResponseEntity<String> joinUser(@ModelAttribute JoinDto joinDto) {
        System.out.println(joinDto.getName() + "님 회원가입 되었습니다.");
        joinService.joinProcess(joinDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

//    @PostMapping("/user")
//    public String signup(AddUserRequestDto request){
//        userService.save(request); // 회원 가입 메소드 호출
//        return "redirect:/login"; // 회원 가입이 완료된 후 로그인 페이지로 이동
//    }
//
//    @GetMapping("/login")
//    public String login(){
//        return "login completed";
//    }
//
//    @GetMapping("/signup")
//    public String signup(){
//        return "signup completed";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        new SecurityContextLogoutHandler().logout(request, response,
//                SecurityContextHolder.getContext().getAuthentication());
//        return "redirect:/login";
//    }

    @GetMapping("/{highschoolId}/gender")
    public void getGenderStats() {
        return ;
    }


    @GetMapping("/{highschoolId}/student-number")
    public void getYearStats() {
        return ;
    }
}
