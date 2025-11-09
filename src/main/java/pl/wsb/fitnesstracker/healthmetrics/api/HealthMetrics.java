package pl.wsb.fitnesstracker.healthmetrics.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Health_Metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOnex
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private pl.wsb.fitnesstracker.user.api.User user;

    @Column(name = "date")
    private java.time.LocalDate date;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "heartRate")
    private Integer heartRate;

    public HealthMetrics(Double weight, Double height, pl.wsb.fitnesstracker.user.api.User user, Integer heartRate) {
        this.weight = weight;
        this.height = height;
        this.user = user;
        this.heartRate = heartRate;
    }
}
