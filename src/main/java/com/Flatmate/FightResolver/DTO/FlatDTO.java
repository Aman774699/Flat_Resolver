package com.Flatmate.FightResolver.DTO;

import java.util.List;

public class FlatDTO {
    private Long id;
    private String flatCode;
    private List<String> userNames; // List of user names in the flat

    public FlatDTO(Long id, String flatCode, List<String> userNames) {
        this.id = id;
        this.flatCode = flatCode;
        this.userNames = userNames;
    }

    public String getFlatCode() {
        return flatCode;
    }

    public void setFlatCode(String flatCode) {
        this.flatCode = flatCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }
}
