package pl.wsb.fitnesstracker.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventQueryRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Event> findByCountry(String country) {
        return em.createQuery("from Event e where e.country = :country order by e.startTime", Event.class)
            .setParameter("country", country)
            .getResultList();
    }
}