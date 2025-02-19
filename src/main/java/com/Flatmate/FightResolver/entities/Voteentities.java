package com.Flatmate.FightResolver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voteentities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Userentities user;

    @ManyToOne
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaintentities complaint;

    private boolean isUpvote;
}
