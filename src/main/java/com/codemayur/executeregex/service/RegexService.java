package com.codemayur.executeregex.service;

import com.codemayur.executeregex.dto.RegexRequestBody;
import com.codemayur.executeregex.dto.RegexResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexService {

    private static final Logger logger = LoggerFactory.getLogger(RegexService.class);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Value("${regex.timeout_in_seconds}")
    private Integer TIMEOUT;

    public RegexResponseBody executeRegexOnText(RegexRequestBody regexRequestBody) {
        RegexResponseBody regexResponseBody;
        ExecuteRegexOnTextCallable callableTask = null;
        try {
            callableTask = new ExecuteRegexOnTextCallable(this, regexRequestBody);

            Future<RegexResponseBody> future = executorService.submit(callableTask);

            regexResponseBody = future.get(TIMEOUT, TimeUnit.SECONDS);

        } catch (InterruptedException | ExecutionException e) {
            logger.error(String.format("Execute regex on text failed unexpectedly! Request: %s", regexRequestBody), e);
            return new RegexResponseBody();
        } catch (TimeoutException e) {
            logger.error("TIME-OUT while executing regex on text!");
            callableTask.interrupt();
            return new RegexResponseBody();
        }

        return regexResponseBody;
    }

    public RegexResponseBody executeRegexOnText(String regex, String textBody) {
        RegexResponseBody regexResponseBody;
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(textBody);
        if (matcher.find()) {
            String match = matcher.group();
            logger.info("Match found: {}", match);
            regexResponseBody = new RegexResponseBody(match, false);
        } else {
            logger.warn("Match NOT found!");
            regexResponseBody = new RegexResponseBody();
        }
        return regexResponseBody;
    }

}
