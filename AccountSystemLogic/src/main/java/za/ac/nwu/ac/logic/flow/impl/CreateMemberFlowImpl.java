package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.logic.flow.CreateMemberFlow;
import za.ac.nwu.ac.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("CreateMemberFlowName")
public class CreateMemberFlowImpl implements CreateMemberFlow {
    private final MemberTranslator memberTranslator;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateMemberFlowImpl.class);

    public CreateMemberFlowImpl(MemberTranslator memberTranslator) {
        this.memberTranslator = memberTranslator;
    }

    @Override
    public MemberDto create(MemberDto memberDto)
    {
        LOGGER.info("The input object was {}",memberDto);
        if(null == memberDto.getCreationDate())
        {
            memberDto.setCreationDate(LocalDate.now());
        }
        //AccountTransactionDto results = new AccountTransactionDto(createdAccountTransaction)
        //LOGGER.info("The return object is {}",results)
        return memberTranslator.create(memberDto);
    }
}
