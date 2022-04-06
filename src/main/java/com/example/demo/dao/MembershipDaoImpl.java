package com.example.demo.dao;

import com.example.demo.dto.MembershipFilterDto;
import com.example.demo.model.MembershipEntity;
import com.example.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.dto.MembershipDto.formatter;

@Repository
public class MembershipDaoImpl {
    private final EntityManager em;

    @Autowired
    public MembershipDaoImpl(EntityManager em) {
        this.em = em;
    }

    public List<MembershipEntity> filter(MembershipFilterDto membershipFilterDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MembershipEntity> criteriaQuery = cb.createQuery(MembershipEntity.class);
        Root<MembershipEntity> membershipEntityRoot = criteriaQuery.from(MembershipEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(membershipFilterDto.getEndDate()).ifPresent(endDate -> predicates.add(
                cb.equal(membershipEntityRoot.get("endDate"), LocalDate.parse(membershipFilterDto.getEndDate(), formatter))));

        Optional.ofNullable(membershipFilterDto.getSection()).ifPresent(section -> predicates.add(
                cb.equal(membershipEntityRoot.get("section"), section)));

        Optional.ofNullable(membershipFilterDto.getUserEntity()).ifPresent(userEntity -> predicates.add(
                cb.equal(membershipEntityRoot.get("userEntity"), userEntity)));

        Predicate[] pr = predicates.toArray(new Predicate[predicates.size()]);
        criteriaQuery.select(membershipEntityRoot).where(pr);

        return em.createQuery(criteriaQuery).getResultList();
    }
}
