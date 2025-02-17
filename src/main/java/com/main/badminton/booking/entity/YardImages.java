package com.main.badminton.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "yard_images")
@EntityListeners(AuditingEntityListener.class)
public class YardImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "yard_id")
    private Yards yards;


    @Column(name = "image")
    private String image;

    @Override
    public String toString() {
        return "YardImages{" +
                "id=" + id +
                ", image='" + image + '\'' +
                '}';
    }
}
