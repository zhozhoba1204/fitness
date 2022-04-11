package com.example.demo.dao;

import com.example.demo.model.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipDao extends JpaRepository<MembershipEntity, Integer> {
}
