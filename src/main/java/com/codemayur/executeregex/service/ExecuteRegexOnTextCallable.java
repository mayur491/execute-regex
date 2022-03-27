package com.codemayur.executeregex.service;

import com.codemayur.executeregex.dto.RegexRequestBody;
import com.codemayur.executeregex.dto.RegexResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class ExecuteRegexOnTextCallable implements Callable<RegexResponseBody> {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteRegexOnTextCallable.class);

    private Thread currentThread;
    private final RegexRequestBody regexRequestBody;
    private final RegexService regexService;

    public ExecuteRegexOnTextCallable(RegexService regexService, RegexRequestBody regexRequestBody) {
        this.regexService = regexService;
        this.regexRequestBody = regexRequestBody;
    }

    @Override
    public RegexResponseBody call() {
        RegexResponseBody regexResponseBody;
        currentThread = Thread.currentThread();
        if (!currentThread.isInterrupted()) {

            regexResponseBody = regexService.executeRegexOnText(regexRequestBody.getRegex(),
                    regexRequestBody.getTextBody());

        } else {
            logger.warn("Failed to execute regex on text as the thread is interrupted!");
            regexResponseBody = new RegexResponseBody();
        }
        return regexResponseBody;
    }

    public synchronized void interrupt() {
        if (!currentThread.isInterrupted()) {
            String threadName = currentThread.getName();
            logger.info("Interrupt Requested for thread: {}", threadName);

            /*
             * Yes, Thread.stop(); is deprecated, but if a thread is stuck in an operation that can take an undefined
             * amount of time, Thread.stop(); is the only way to stop the thread.
             */
            currentThread.stop();

            logger.info("Interrupted thread: {}", threadName);
        } else {
            logger.warn("Interrupt Requested for thread which is already interrupted!");
        }
    }

}
