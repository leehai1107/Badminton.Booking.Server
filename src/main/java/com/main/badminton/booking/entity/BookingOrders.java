package com.main.badminton.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "booking_orders")
@EntityListeners(AuditingEntityListener.class)
public class BookingOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_at")
    private LocalDateTime bookingAt = LocalDateTime.now();

    @Column(name = "status")
    private Boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "yard_id")
    private Yards yards;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slots slots;

    @Column(name = "tournament_start")
    private LocalDate tournamentStart;

    @Column(name = "tournament_end")
    private LocalDate tournamentEnd;

    @JsonIgnore
    @OneToMany(mappedBy = "bookingOrders", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Payments> payments;

    @Override
    public String toString() {
        return "BookingOrders{" +
                "id=" + id +
                ", bookingAt=" + bookingAt +
                ", status=" + status +
                ", tournamentStart=" + tournamentStart +
                ", tournamentEnd=" + tournamentEnd +
                '}';
    }

}
