package com.example.demo.service;

import com.example.demo.dao.MembershipDao;
import com.example.demo.dao.MembershipDaoImpl;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.MembershipDto;
import com.example.demo.dto.MembershipFilterDto;
//import com.example.demo.mapper.MembershipDtoToMembershipEntityMapper;
import com.example.demo.mapper.MembershipEntityToMembershipDtoMapper;
import com.example.demo.model.MembershipEntity;
import com.example.demo.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class MembershipServiceImpl implements MembershipService {
    private final MembershipDao membershipDao;
    private final MembershipDaoImpl membershipDaoImpl;
    private final UserDao userDao;
//    private final MembershipDtoToMembershipEntityMapper membershipDtoToMembershipEntityMapper;
    private final MembershipEntityToMembershipDtoMapper membershipEntityToMembershipDtoMapper;

    @Override
    public MembershipDto add(MembershipDto membershipDto) {
        UserEntity userEntity = userDao.getById(membershipDto.getUserEntity());
        MembershipEntity membershipEntity = MembershipDto.getMembershipFromDto(userEntity, membershipDto);
        userEntity.getMembershipEntities().add(membershipEntity);
        MembershipEntity save = membershipDao.save(membershipEntity);
        return membershipEntityToMembershipDtoMapper.mapTo(save);
    }

    @Override
    public List<MembershipDto> filter(MembershipFilterDto membershipFilterDto) {
        List<MembershipEntity> filter = membershipDaoImpl.filter(membershipFilterDto);
        return filter.stream()
                .map(membershipEntityToMembershipDtoMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        membershipDao.deleteById(id);
    }
}
