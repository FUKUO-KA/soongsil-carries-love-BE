package fukuoka.soongsil_carries_love.domain.highschool.service;

import fukuoka.soongsil_carries_love.domain.highschool.repository.HighschoolRepository;

import java.util.List;

public interface HighschoolService {

    void fetchAndSaveHighschoolData();

    List<String> getAllSchoolNames();
}
