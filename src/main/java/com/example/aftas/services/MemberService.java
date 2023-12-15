package com.example.aftas.services;
import com.example.aftas.DTOs.member.memberDTO;
import com.example.aftas.models.Member;
import com.example.aftas.repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }


    public List<memberDTO> getAll() {
        return Arrays.asList(modelMapper.map(memberRepository.findAll(), memberDTO[].class));
    }


    public memberDTO searchMembers(Integer num, String name, String familyName) {
        Member member;
        if (num != null) {
            member = memberRepository.findByNum(num);
        } else if (name != null) {
            member = memberRepository.findByNameContaining(name);
        } else if (familyName != null) {
            member = memberRepository.findByFamilyNameContaining(familyName);
        } else {
            return null;
        }
        return (member != null) ? modelMapper.map(member, memberDTO.class) : null;
    }



    public memberDTO create(memberDTO memberDTO) {
        if (memberRepository.existsByIdentityNumber(memberDTO.getIdentityNumber())) {
            throw new RuntimeException("Member with the same identity number already exists.");
        }

        // Member does not exist, proceed with creation
        Member member = modelMapper.map(memberDTO, Member.class);
        return modelMapper.map(memberRepository.save(member), memberDTO.class);
    }


}