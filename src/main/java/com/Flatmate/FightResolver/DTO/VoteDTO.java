package com.Flatmate.FightResolver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
    private Long id;
    private Long userId;
    private Long complaintId;
    private boolean isUpvote;
}
