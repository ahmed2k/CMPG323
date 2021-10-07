package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.MemberDto;

public interface CreateMemberFlow {
    MemberDto create(MemberDto memberDto);
}
