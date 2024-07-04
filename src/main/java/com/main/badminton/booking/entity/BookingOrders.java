package com.main.badminton.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
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
    private LocalDate bookingAt = LocalDate.now();

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "yard_id")
    private Yards yards;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slots slots;

    @Column(name = "tournament_start")
    private LocalDate tournamentStart;

    @Column(name = "tournament_end")
    private LocalDate tournamentEnd;

    @OneToMany(mappedBy = "bookingOrders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payments> payments;

}
