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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "yards")
@EntityListeners(AuditingEntityListener.class)
public class Yards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "open_time")
    private LocalDate openTime;

    @Column(name = "close_time")
    private LocalDate closeTime;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @OneToMany(mappedBy = "yards", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<YardImages> yardImages;

    @OneToMany(mappedBy = "yards", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telephones> telephones;

    @OneToMany(mappedBy = "yards", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Slots> slots;

    @OneToMany(mappedBy = "yards", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedules> schedules;

    @OneToMany(mappedBy = "yards", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingOrders> bookingOrders;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "update_date", insertable = false)
    @LastModifiedDate
    private LocalDateTime updateDate;


    @Column(name = "create_by", nullable = false, updatable = false)
    @CreatedBy
    private Integer createBy;

    @Column(name = "update_by", insertable = false)
    @LastModifiedBy
    private Integer updateBy;

    @ManyToMany
    @JoinTable(
            name = "yard_types",
            joinColumns = @JoinColumn(name = "yard_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<Types> types = new ArrayList<>();
}
