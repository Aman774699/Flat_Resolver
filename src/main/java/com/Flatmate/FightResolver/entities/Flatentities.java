package com.Flatmate.FightResolver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "flats")
public class Flatentities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flat_id;

    private String flatCode;

    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL)
    private List<Userentities> users;

    public Flatentities(String flatCode) {
    }

    public Flatentities() {}
    public Flatentities(Long flat_id, String flatCode, List<Userentities> users) {
        this.flat_id = flat_id;
        this.flatCode = flatCode;
        this.users = users;
    }

    public Long getFlat_id() {
        return flat_id;
    }

    public void setFlat_id(Long flat_id) {
        this.flat_id = flat_id;
    }

    public String getFlatCode() {
        return flatCode;
    }

    public void setFlatCode(String flatCode) {
        this.flatCode = flatCode;
    }

    public List<Userentities> getUsers() {
        return users;
    }

    public void setUsers(List<Userentities> users) {
        this.users = users;
    }
}
