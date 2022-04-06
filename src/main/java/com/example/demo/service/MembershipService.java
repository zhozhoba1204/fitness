package com.example.demo.service;

import com.example.demo.dto.MembershipDto;
import com.example.demo.dto.MembershipFilterDto;

import java.util.List;

public interface MembershipService {
    MembershipDto add(MembershipDto membershipDto);
    List<MembershipDto> filter(MembershipFilterDto membershipFilterDto);
    void delete(Integer id);
}
