package com.main.badminton.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "yard_checkins")
public class YardCheckins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "checkin_time")
    private LocalTime checkInTime;

    @Column(name = "checkout_time")
    private LocalTime checkOutTime;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payments payments;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "checkin_by")
    private User checkInBy;

    @Override
    public String toString() {
        return "YardCheckins{" +
                "id=" + id +
                ", status=" + status +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                '}';
    }
}
