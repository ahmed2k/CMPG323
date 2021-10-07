package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.Member;
import za.ac.nwu.ac.logic.flow.addAndSubtractMilesFlow;
import za.ac.nwu.ac.translator.MemberTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class addAndSubtractMilesFlowImpl implements addAndSubtractMilesFlow {
    private final MemberTranslator memberTranslator;

    @Autowired
    public addAndSubtractMilesFlowImpl(MemberTranslator memberTranslator) {
        this.memberTranslator = memberTranslator;
    }

    @Override
    public int addMiles(long number, String firstName) {
        return memberTranslator.addMiles(number,firstName);
    }
    public int subtractMiles(long number, String firstName) {
        return memberTranslator.subtractMiles(number,firstName);
    }
}
