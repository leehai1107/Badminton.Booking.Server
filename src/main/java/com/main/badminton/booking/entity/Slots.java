package com.main.badminton.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

import java.time.LocalDate;
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
    private Boolean status;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDate createDate;

    @Column(name = "update_date", insertable = false)
    @LastModifiedDate
    private LocalDate updateDate;

    @Column(name = "create_by", nullable = false, updatable = false)
    @CreatedBy
    private Integer createBy;

    @Column(name = "update_by", insertable = false)
    @LastModifiedBy
    private Integer updateBy;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "yard_id")
    private Yards yards;

    @JsonIgnore
    @OneToMany(mappedBy = "slots", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<BookingOrders> bookingOrders;

    @Override
    public String toString() {
        return "Slots{" +
                "id=" + id +
                ", price=" + price +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                '}';
    }
}
