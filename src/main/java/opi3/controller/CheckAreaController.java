package opi3.controller;

import opi3.dto.ResultsDto;
import opi3.model.Result;
import opi3.service.ResultManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping( "/hits")
public class CheckAreaController {
    private final ResultManager resultManager;
    public CheckAreaController(ResultManager resultManager){

        this.resultManager = resultManager;
    }

    @PostMapping
    public Result add(@RequestBody ResultsDto resultsDto, @RequestAttribute Timestamp startTime, @RequestAttribute Authentication authorization) {
        return resultManager.addHit(resultsDto, authorization.getName() , startTime);
    }

    @GetMapping
    public List<Result> getHits(@RequestAttribute Authentication authorization) {

        return  resultManager.getHits(authorization.getName());
    }

}