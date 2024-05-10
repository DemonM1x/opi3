package opi3.service;

import opi3.dto.ResultsDto;
import opi3.model.NewUser;
import opi3.model.Result;
import opi3.repository.CheckAreaRepository;
import opi3.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
@Service
public class ResultManager {
    private final UserRepository userRepository;
    private final CheckAreaRepository checkAreaRepository;

    public ResultManager(UserRepository userRepository, CheckAreaRepository checkAreaRepository, CheckArea check) {
        this.userRepository = userRepository;
        this.checkAreaRepository = checkAreaRepository;

    }

    public Result addHit(ResultsDto resultsDto, String username , Timestamp startTime) {
        CheckArea checkArea = new CheckArea();
        Long start = System.currentTimeMillis();
        Result results = new Result();
        if(checkArea.validate(resultsDto.getX() , resultsDto.getY() , resultsDto.getR())) {
            results.setX(resultsDto.getX());
            results.setY(resultsDto.getY());
            results.setR(resultsDto.getR());
            results.setResultArea(CheckArea.check(results.getX(), results.getY(), results.getR()));
            results.setUser(userRepository.findByUsername(username));
            results.setTimeScript(System.currentTimeMillis() - start);
            results.setTime(startTime);
            checkAreaRepository.save(results);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NO validation");
        }
        return results;
    }

    public List<Result> getHits(String name) {
        NewUser user = userRepository.findByUsername(name);
        List<Result> hits =  checkAreaRepository.findAllByUser(user);
        Collections.reverse(hits);
        return hits;
    }

}
