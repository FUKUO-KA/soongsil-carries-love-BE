package fukuoka.soongsil_carries_love.domain.highschool.service;

import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolGenderRatioResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolRankingofUserCountResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolUserCountResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolUserStudentIdResponseDto;
import fukuoka.soongsil_carries_love.domain.highschool.repository.HighschoolRepository;

import java.util.List;

public interface HighschoolService {

    void fetchAndSaveHighschoolData();

    List<String> getAllSchoolNames();

    HighschoolUserCountResponseDto getUserCountByHighschool(String highschoolCode);

    HighschoolGenderRatioResponseDto getGenderRatioByHighschool(String highschoolCode);

    HighschoolUserStudentIdResponseDto getStudentCountByYear(String highschoolCode);

    List<HighschoolRankingofUserCountResponseDto> getUserCountByHighschool();

}
