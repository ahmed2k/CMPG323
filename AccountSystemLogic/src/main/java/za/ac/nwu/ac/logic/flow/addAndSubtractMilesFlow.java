package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.Member;

public interface addAndSubtractMilesFlow {
    int addMiles(long miles, String firstName);
    int subtractMiles(long miles, String firstName);
}
