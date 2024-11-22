package fukuoka.soongsil_carries_love.domain.user.service;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.domain.highschool.repository.HighschoolRepository;
import fukuoka.soongsil_carries_love.domain.user.converter.UserConverter;
import fukuoka.soongsil_carries_love.domain.user.dto.JoinDto;
import fukuoka.soongsil_carries_love.domain.user.entity.User;
import fukuoka.soongsil_carries_love.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserConverter userConverter;
    private final HighschoolRepository highschoolRepository;

    public void joinProcess(JoinDto joinDto){

        // 이메일 중복 검사 -> 해당 회원이 이미 존재하는지 확인
        if (userRepository.existsByEmail(joinDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 학교 코드로 Highschool 엔티티 조회
        Highschool highschool = highschoolRepository.findBySchoolCode(joinDto.getSchoolCode())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학교 코드입니다."));

        // 패스워드 암호화
        String encodedPassword = bCryptPasswordEncoder.encode(joinDto.getPassword());

        // JoinDto를 User 엔티티로 변환
        User user = userConverter.toEntity(joinDto, encodedPassword, highschool);

        // User 엔티티 저장
        userRepository.save(user);
    }
}
