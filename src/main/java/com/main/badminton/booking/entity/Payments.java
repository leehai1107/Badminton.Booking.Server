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
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "final_price")
    private Double finalPrice;

    @Column(name = "is_tournament")
    private Boolean iStournament;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingOrders bookingOrders;

    @OneToMany(mappedBy = "payments", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedBacks> feedBacks;




}
