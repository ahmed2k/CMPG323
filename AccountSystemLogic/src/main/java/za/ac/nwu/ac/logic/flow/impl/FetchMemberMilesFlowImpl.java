package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.Member;
import za.ac.nwu.ac.logic.flow.FetchMemberMilesFlow;
import za.ac.nwu.ac.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class FetchMemberMilesFlowImpl implements FetchMemberMilesFlow {

    private final MemberTranslator memberTranslator;

    @Autowired
    public FetchMemberMilesFlowImpl(MemberTranslator memberTranslator) {
        this.memberTranslator = memberTranslator;
    }

    @Override
    public List<MemberDto> getAllMembers(){
        return memberTranslator.getAllMembers();
    }

    @Override
    public MemberDto getMilesByMemberName(String firstName){
        return memberTranslator.getMilesByMemberName(firstName);
    }
}
