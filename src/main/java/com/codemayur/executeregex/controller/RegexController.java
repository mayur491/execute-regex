package com.codemayur.executeregex.controller;

import com.codemayur.executeregex.dto.RegexRequestBody;
import com.codemayur.executeregex.dto.RegexResponseBody;
import com.codemayur.executeregex.service.RegexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/regex")
public class RegexController {

    private static final Logger logger = LoggerFactory.getLogger(RegexController.class);

    private final RegexService regexService;

    @Autowired
    public RegexController(RegexService regexService) {
        this.regexService = regexService;
    }

    @PostMapping
    public ResponseEntity<RegexResponseBody> executeRegexOnText(@Valid @RequestBody RegexRequestBody regexRequestBody) {
        logger.debug("Invoked execute regex on text with: {}", regexRequestBody);
        RegexResponseBody regexResponseBody;
        try {

            regexResponseBody = regexService.executeRegexOnText(regexRequestBody);

        } catch (Exception e) {
            logger.error("*** Execute regex on text failed unexpectedly! ***", e);
            return new ResponseEntity<>(new RegexResponseBody(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.debug("Execute regex on text exiting with: {}", regexResponseBody);
        return new ResponseEntity<>(regexResponseBody, HttpStatus.OK);
    }

}
