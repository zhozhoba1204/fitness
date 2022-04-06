package com.example.demo.controller;

import com.example.demo.dto.MembershipDto;
import com.example.demo.dto.MembershipFilterDto;
import com.example.demo.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembershipController {
    private MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping("/addMembership")
    public MembershipDto addMembership(@RequestBody MembershipDto membershipDto) {
        return membershipService.add(membershipDto);
    }

    @PostMapping("/membership/filter")
    public List<MembershipDto> filter(@RequestBody MembershipFilterDto membershipFilterDto) {
        return membershipService.filter(membershipFilterDto);
    }

    @GetMapping("/membership/delete/{id}")
    public void delete(@PathVariable Integer id) {
        membershipService.delete(id);
    }
}
