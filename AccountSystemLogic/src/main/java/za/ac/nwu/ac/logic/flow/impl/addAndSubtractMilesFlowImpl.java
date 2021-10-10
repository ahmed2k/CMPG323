package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateMemberFlowImpl.class);

    @Autowired
    public addAndSubtractMilesFlowImpl(MemberTranslator memberTranslator) {
        this.memberTranslator = memberTranslator;
    }

    @Override
    public int addMiles(long number, String firstName) {
        LOGGER.info("The input object was {}{}",number,firstName);
        return memberTranslator.addMiles(number,firstName);
    }
    public int subtractMiles(long number, String firstName) {
        LOGGER.info("The input object was {}{}",number,firstName);
        return memberTranslator.subtractMiles(number,firstName);
    }
}
