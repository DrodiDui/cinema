package com.kapitonau.cinema.adminservice.repository.custom.impl;

import com.kapitonau.cinema.adminservice.model.CinemaEntity;
import com.kapitonau.cinema.adminservice.repository.custom.CinemaCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CinemaCustomRepositoryImpl implements CinemaCustomRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<CinemaEntity> findAllByFilters(Long offset, Long limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CinemaEntity> cinemaQuery = builder.createQuery(CinemaEntity.class);
        Root<CinemaEntity> root = cinemaQuery.from(CinemaEntity.class);

        cinemaQuery
                .select(root)
                .orderBy(builder.asc(root.get("cinemaName")));

        return entityManager.createQuery(cinemaQuery)
                .setFirstResult(offset.byteValue())
                .setMaxResults(limit.byteValue())
                .getResultList();
    }

    @Override
    public Long cinemaCountByFilters() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cinemaQuery = builder.createQuery(Long.class);
        Root<CinemaEntity> root = cinemaQuery.from(CinemaEntity.class);

        cinemaQuery
                .select(builder.count(root))
                .orderBy(builder.asc(root.get("cinemaName")));

        return entityManager.createQuery(cinemaQuery).getSingleResult();
    }
}
