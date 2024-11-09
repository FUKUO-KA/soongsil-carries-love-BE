package fukuoka.soongsil_carries_love.domain.highschool.service;

import fukuoka.soongsil_carries_love.domain.highschool.dto.HighschoolResponseDto;

import java.util.List;

public interface HighschoolService {

    void fetchAndSaveHighschoolData();
    List<HighschoolResponseDto> getAllHighschools();
}
