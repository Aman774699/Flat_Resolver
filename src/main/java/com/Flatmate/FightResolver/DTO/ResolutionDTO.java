package com.Flatmate.FightResolver.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResolutionDTO {
    private Long id;
    private Long complaintId;
    private String complaintTitle;
    private Long resolverId;
    private String resolverName;
    private LocalDateTime resolvedAt;


    public ResolutionDTO(Long id, Long id1, String title, Long id2, LocalDateTime resolvedAt) {
    }
}
