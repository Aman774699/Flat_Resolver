package com.Flatmate.FightResolver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "flats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flatentities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flat_id;

    private String flatCode;

    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL)
    private List<Userentities> users;

    public Flatentities(String flatCode) {
    }

}
