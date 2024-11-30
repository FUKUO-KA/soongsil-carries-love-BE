package fukuoka.soongsil_carries_love.domain.user.service;

import fukuoka.soongsil_carries_love.domain.highschool.entity.Highschool;
import fukuoka.soongsil_carries_love.domain.user.dto.LoginDto;
import fukuoka.soongsil_carries_love.domain.user.dto.UserInfoDto;
import fukuoka.soongsil_carries_love.domain.user.entity.User;
import fukuoka.soongsil_carries_love.domain.user.repository.UserRepository;
import fukuoka.soongsil_carries_love.global.exception.NotJoinedUserException;
import fukuoka.soongsil_carries_love.global.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public UserInfoDto login(LoginDto loginDto) throws Exception {
        User user = userRepository.findByEmail(loginDto.getEmail());

        if (Objects.isNull(user)){
            throw new NotJoinedUserException();
        }

        if (!Objects.equals(user.getPassword(), loginDto.getPassword())){
            throw new WrongPasswordException();
        }

        return UserInfoDto.from(user);
    }
}
