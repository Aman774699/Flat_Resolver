package com.Flatmate.FightResolver.DTO;

import lombok.Data;

@Data
public class LeaderBoardDTO {
    private Long flatId;
    private String flatCode;
    private Long userId;
    private String userName;
    private int complaintsFiled;
    private int complaintsResolved;
    private int totalKarma;

    public LeaderBoardDTO(Long id, Long flatId, String flatCode, Long userId, String userName, int complaintsFiled, int complaintsResolved, int totalKarma) {
        this.flatId = flatId;
        this.flatCode = flatCode;
        this.userId = userId;
        this.userName = userName;
        this.complaintsFiled = complaintsFiled;
        this.complaintsResolved = complaintsResolved;
        this.totalKarma = totalKarma;
    }
}
