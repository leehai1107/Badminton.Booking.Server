package com.main.badminton.booking.entity;

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
@Table(name = "booking_orders")
@EntityListeners(AuditingEntityListener.class)
public class BookingOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_at")
    private String bookingAt;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "yard_id")
    private Yards yards;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slots slots;

    @OneToMany(mappedBy = "bookingOrders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payments> payments;

}
