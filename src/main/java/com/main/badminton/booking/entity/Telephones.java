package com.main.badminton.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "telephones")
public class Telephones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "yard_id")
    private Yards yards;

    @Column(name = "telephone")
    private String telephone;

    @Override
    public String toString() {
        return "Telephones{" +
                "id=" + id +
                ", telephone='" + telephone + '\'' +
                '}';
    }

}
