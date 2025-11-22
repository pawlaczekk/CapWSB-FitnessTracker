package pl.wsb.fitnesstracker.userevent;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.event.Event;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;

import io.micrometer.common.lang.Nullable;

@Entity
@Table(name = "user_events")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"user", "event"})
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParticipationStatus status;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    public UserEvent(User user,
                     Event event,
                     ParticipationStatus status,
                     LocalDateTime registeredAt) {
        this.user = user;
        this.event = event;
        this.status = status;
        this.registeredAt = registeredAt;
    }
}

enum ParticipationStatus {
    REGISTERED,
    CANCELLED,
    ATTENDED
}