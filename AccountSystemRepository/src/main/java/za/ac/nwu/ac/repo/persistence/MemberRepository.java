package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT new za.ac.nwu.ac.domain.dto.MemberDto("+
            "        at.firstName,"+
            "        at.lastName,"+
            "        at.miles,"+
            "        at.creationDate)"+
            "  FROM"+
            "  Member at"+
            "  WHERE at.firstName = :firstName ")
    MemberDto getMilesByMemberName(String firstName);
}
