package com.Flatmate.FightResolver.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderBoardDTO {
    private Long id;
    private Long flatId;
    private Long userId;
    private int complaintsFiled;
    private int complaintsResolved;
    private int totalKarma;

}
