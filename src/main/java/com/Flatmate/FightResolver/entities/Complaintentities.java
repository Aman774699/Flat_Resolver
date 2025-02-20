package com.Flatmate.FightResolver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Complaintentities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String type; // Noise, Cleanliness, Bills, Pets, etc.
    private String severity; // Minor, Annoying, Major, Nuclear

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Userentities filedBy;

    @ManyToOne
    @JoinColumn(name = "flat_id", nullable = false)
    private Flatentities flat;

    private int upvotes = 0;
    private int downvotes = 0;
    private boolean resolved = false;

    private LocalDateTime timestamp=LocalDateTime.now();


}
