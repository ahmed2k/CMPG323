package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.Member;

import  java.util.List;
public interface FetchMemberMilesFlow {
    List<MemberDto> getAllMembers();
    MemberDto getMilesByMemberName(String firstName);
}
