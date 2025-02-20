package com.Flatmate.FightResolver.entities;

import com.Flatmate.FightResolver.enmus.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Userentities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN, USER, etc.

    private boolean isactive = true;

    @ManyToOne
    @JoinColumn(name = "flat_id", nullable = false)
    private Flatentities flat;

    private int karmaPoints = 0;
}
