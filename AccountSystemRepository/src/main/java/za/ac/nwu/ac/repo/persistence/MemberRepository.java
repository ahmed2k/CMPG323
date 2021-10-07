package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT new za.ac.nwu.ac.domain.dto.MemberDto( "+
            "       at.firstName, "+
            "       at.lastName, "+
            "       at.miles,"+
            "       at.creationDate)"+
            "       FROM "+
            "       Member at "+
            "       WHERE at.firstName = :firstName ")
    MemberDto getMilesByMemberName(String firstName);
   /* @Query(value = "SELECT "+
            "        FIRST_NAME"+
            "        LAST_NAME,"+
            "        MILES,"+
            "        CREATION_DATE"+
            "  FROM"+
            "  HR.MEMBER at"+
            "  WHERE FIRST_NAME = ?1 ",
    nativeQuery = true)
    MemberDto getMilesByMemberName(String firstName);*/


    @Modifying
    @Query(value = "UPDATE "+
            "       MEMBER"+
            "      SET MILES = MILES + ?1"+
            "        WHERE"+
            "  FIRST_NAME = ?2 ",
            nativeQuery = true)
    int addMiles(long number,String firstName);

    @Modifying
    @Query(value = "UPDATE "+
            "       MEMBER"+
            "      SET MILES = MILES - ?1"+
            "        WHERE"+
            "  FIRST_NAME = ?2 ",
            nativeQuery = true)
    int subtractMiles(long number,String firstName);
}
