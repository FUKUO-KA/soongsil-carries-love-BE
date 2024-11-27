package fukuoka.soongsil_carries_love.domain.user.service;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.domain.user.dto.UserInfoDto;
import fukuoka.soongsil_carries_love.domain.user.entity.User;
import fukuoka.soongsil_carries_love.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserInfoDto getUserInfo(User user){
        Highschool highschool = user.getHighschool();

        return UserInfoDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .highSchoolName(highschool.getSchoolName())
                .highSchoolCode(highschool.getSchoolCode())
                .department(user.getDepartment())
                .gender(user.getGender())
                .build();
    }
}
