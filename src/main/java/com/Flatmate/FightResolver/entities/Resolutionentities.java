package com.Flatmate.FightResolver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "resolutions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resolutionentities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaintentities complaint;

    @ManyToOne
    @JoinColumn(name = "resolver_id", nullable = false)
    private Userentities resolver; // User who resolved the issue

    private LocalDateTime resolvedAt;
}
