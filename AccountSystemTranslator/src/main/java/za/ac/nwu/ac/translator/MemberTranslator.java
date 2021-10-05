package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.Member;

import java.util.List;

public interface MemberTranslator {
    MemberDto create(MemberDto memberDto);
    List<MemberDto> getAllMembers();

    MemberDto getMilesByMemberName(String firstName);
    MemberDto addMiles(long number,String firstName);
}
