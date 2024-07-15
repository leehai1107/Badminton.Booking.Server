package com.main.badminton.booking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @JsonIgnore
    @OneToMany(mappedBy = "yards", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<YardImages> yardImages;

    @JsonIgnore
    @OneToMany(mappedBy = "yards", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Telephones> telephones;

    @JsonIgnore
    @OneToMany(mappedBy = "yards", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Slots> slots;

    @JsonIgnore
    @OneToMany(mappedBy = "yards", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<BookingOrders> bookingOrders;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalDate createDate;

    @Column(name = "update_date", insertable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @LastModifiedDate
    private LocalDate updateDate;


    @Column(name = "create_by", nullable = false, updatable = false)
    @CreatedBy
    private Integer createBy;

    @Column(name = "update_by", insertable = false)
    @LastModifiedBy
    private Integer updateBy;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "yard_types",
            joinColumns = @JoinColumn(name = "yard_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<Types> types = new ArrayList<>();

    @Override
    public String toString() {
        return "Yards{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", provinceId=" + provinceId +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                '}';
    }
}
