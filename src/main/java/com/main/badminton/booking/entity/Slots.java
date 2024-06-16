package com.main.badminton.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "slots")
@EntityListeners(AuditingEntityListener.class)
public class Slots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private String status;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalTime createDate;

    @Column(name = "update_date", insertable = false)
    @LastModifiedDate
    private LocalTime updateDate;

    @Column(name = "create_by", nullable = false, updatable = false)
    @CreatedBy
    private Integer createBy;

    @Column(name = "update_by", insertable = false)
    @LastModifiedBy
    private Integer updateBy;

    @ManyToOne
    @JoinColumn(name = "yard_id")
    private Yards yards;

    @OneToMany(mappedBy = "slots", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingOrders> bookingOrders;
}
