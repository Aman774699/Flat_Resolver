package com.Flatmate.FightResolver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "leaderboard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Leaderboardentities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flat_id", referencedColumnName = "flat_Id")
    private Flatentities flat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Userentities user;

    private int complaintsFiled;
    private int complaintsResolved;
    private int totalKarma;
}
