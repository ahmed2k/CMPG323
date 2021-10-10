package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.domain.persistence.Member;
import za.ac.nwu.ac.repo.persistence.MemberRepository;
import za.ac.nwu.ac.translator.MemberTranslator;

import java.util.ArrayList;
import java.util.List;
@Component
public class MemberTranslatorImpl implements MemberTranslator {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Autowired
    public List<MemberDto> getAllMembers(){
        List<MemberDto> memberDtos = new ArrayList<>();
        try{
            for(Member member : memberRepository.findAll()){
                memberDtos.add(new MemberDto(member));
            }
        }catch (Exception e){
            // TODO: Log
            throw new RuntimeException("unable to read for DB",e);
        }
        return memberDtos;
    }

    @Override
    public MemberDto getMilesByMemberName(String firstName) {
        try{
           return  memberRepository.getMilesByMemberName(firstName);

        }catch(Exception e){
            throw new RuntimeException("Unable to read from DB",e);
        }
    }
    @Override
    public MemberDto create(MemberDto memberDto) {
        try{
            Member member = memberRepository.save(memberDto.getMembers());
            return new MemberDto(member);
        }catch(Exception e)
        {
            throw new RuntimeException("Unable to save data the DB",e);
        }
    }
    @Override
    public void someMethod() {

    }

    @Override
    public  int addMiles(long number,String firstName){
        try {
            return memberRepository.addMiles(number,firstName);
        }catch (Exception e){
            throw new RuntimeException("Unable to update",e);
        }
    }
    @Override
    public  int subtractMiles(long number,String firstName){
        try {
            return memberRepository.subtractMiles(number,firstName);
        }catch (Exception e){
            throw new RuntimeException("Unable to update",e);
        }
    }

}
