package com.example.demo.dao;

import com.example.demo.dto.MembershipFilterDto;
import com.example.demo.dto.UserFilterDto;
import com.example.demo.model.MembershipEntity;
import com.example.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.dto.MembershipDto.formatter;

@Repository
public class UserDaoImpl {
    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    public List<UserEntity> filter(UserFilterDto userFilterDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = cb.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = criteriaQuery.from(UserEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(userFilterDto.getFirstName()).ifPresent(firstName -> predicates.add(
                cb.equal(userEntityRoot.get("firstName"), firstName)));

        Optional.ofNullable(userFilterDto.getLastName()).ifPresent(lastName -> predicates.add(
                cb.equal(userEntityRoot.get("lastName"), lastName)));

        Optional.ofNullable(userFilterDto.getAge()).ifPresent(age -> predicates.add(
                cb.equal(userEntityRoot.get("age"), age)));

        Optional.ofNullable(userFilterDto.getPassport()).ifPresent(passport -> predicates.add(
                cb.equal(userEntityRoot.get("passport"), passport)));

        Predicate[] pr = predicates.toArray(new Predicate[predicates.size()]);
        criteriaQuery.select(userEntityRoot).where(pr);

        return em.createQuery(criteriaQuery).getResultList();
    }
}
