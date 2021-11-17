package com.albares.ahorcado.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private Integer responseCode;
    private Gamer gamer;
    private Map<Integer, Gamer> gamers;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    public Map<Integer, Gamer> getGamers() {
        return gamers;
    }

    public void setGamers(Map<Integer, Gamer> gamers) {
        this.gamers = gamers;
    }

}
