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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "feedbacks")
@EntityListeners(AuditingEntityListener.class)
public class FeedBacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "problem")
    private String problem;

    @Column(name = "create_at")
    private String createAt;

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
    @JoinColumn(name = "payment_id")
    private Payments payments;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "FeedBacks{" +
                "id=" + id +
                ", rating=" + rating +
                ", problem='" + problem + '\'' +
                ", createAt='" + createAt + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                '}';
    }
}
