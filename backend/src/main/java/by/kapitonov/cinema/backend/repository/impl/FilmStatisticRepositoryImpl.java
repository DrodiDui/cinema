package by.kapitonov.cinema.backend.repository.impl;

import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.model.statistics.FilmStatistics;
import by.kapitonov.cinema.backend.repository.FilmStatisticRepository;
import by.kapitonov.cinema.backend.service.FilmService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class FilmStatisticRepositoryImpl implements FilmStatisticRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final FilmService filmService;

    public FilmStatisticRepositoryImpl(
            EntityManager entityManager,
            FilmService filmService
    ) {
        this.entityManager = entityManager;
        this.filmService = filmService;
    }

    @Override
    public List<FilmStatistics> findAllCountOfReservedTicketsById(Long ownerId) {
         Map<Long, Long> query =
                 entityManager.createQuery(
                         "select f.id as id, count(t) as ticketsCount from Film f " +
                                 "left join f.filmSessions fs " +
                                 "left join fs.tickets t " +
                                 "where f.owner.id = :ownerId and t.reserved = true " +
                                 "group by f.filmName " +
                                 "order by count(t) desc",
                         Tuple.class)
                 .setParameter("ownerId", ownerId)
                 .getResultStream()
                 .collect(
                         Collectors.toMap(
                                 tuple -> ((Long) tuple.get("id")),
                                 tuple -> ((Long) tuple.get("ticketsCount"))
                         )
                 );

         return query.entrySet()
                 .stream()
                 .map(mapItems -> {
                     Film film = getFilm(mapItems.getKey());
                     return new FilmStatistics(film, mapItems.getValue());
                 })
                 .collect(Collectors.toList());
    }


    private Film getFilm(Long filmId) {
        return filmService.getById(filmId);
    }
}
