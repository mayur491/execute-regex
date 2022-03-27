package com.codemayur.executeregex.dto;

public class RegexResponseBody {

    private String match;
    private Boolean error;

    public RegexResponseBody() {
        this.match = null;
        this.error = true;
    }

    public RegexResponseBody(String match, Boolean error) {
        this.match = match;
        this.error = error;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "RegexResponseBody{" +
                "match='" + match + '\'' +
                ", error=" + error +
                '}';
    }
}
