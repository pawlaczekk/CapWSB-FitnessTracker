package pl.wsb.fitnesstracker.training.internal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class TrainingQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Training> findByUserAndActivity(Long userId, ActivityType activityType) {
        return entityManager.createQuery(
                "select t from Training t where t.user.id = :userId and t.activityType = :activityType order by t.startTime",
                Training.class)
            .setParameter("userId", userId)
            .setParameter("activityType", activityType)
            .getResultList();
    }

    public double sumDistanceForUserNative(Long userId) {
        Number n = (Number) entityManager.createNativeQuery(
                "select coalesce(sum(distance),0) from trainings where user_id = :userId")
            .setParameter("userId", userId)
            .getSingleResult();
        return n.doubleValue();
    }
}
