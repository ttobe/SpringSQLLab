package com.springsqllab.springsqllab.controller;

import com.springsqllab.springsqllab.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/query")
public class QueryController {

    private final QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping("/{problemId}")
    public String executeQuery(@PathVariable String problemId, @RequestBody String query) {
        try {
            return queryService.executeQuery(problemId, query);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

