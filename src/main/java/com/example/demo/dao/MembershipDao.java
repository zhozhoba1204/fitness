package com.example.demo.dao;

import com.example.demo.dto.MembershipFilterDto;
import com.example.demo.model.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MembershipDao extends JpaRepository<MembershipEntity, Integer> {
//    default List<MembershipEntity> filter(MembershipFilterDto membershipFilterDto){
//
//    };
}
