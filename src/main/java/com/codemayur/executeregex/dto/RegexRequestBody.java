package com.codemayur.executeregex.dto;

import javax.validation.constraints.NotBlank;

public class RegexRequestBody {

    @NotBlank(message = "The field: 'regex' is required.")
    private String regex;

    @NotBlank(message = "The field: 'textBody' is required.")
    private String textBody;

    public RegexRequestBody(String regex, String textBody) {
        this.regex = regex;
        this.textBody = textBody;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    @Override
    public String toString() {
        return "RegexRequestBody{" +
                "regex='" + regex + '\'' +
                ", textBody='" + textBody + '\'' +
                '}';
    }
}
