package za.ac.nwu.ac.domain.persistence;

import javax.persistence.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MEMBER",schema = "HR")
public class Member implements Serializable{
    private Long MemberId;
    private String firstName;
    private String lastName;
    private Long miles;
    private LocalDate creationDate;

    public Member(){

    }

    public Member(Long memberId, String firstName, String lastName, Long miles, LocalDate creationDate) {
        this.MemberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.miles = miles;
        this.creationDate = creationDate;
    }

    public Member(String firstName, String lastName, Long miles, LocalDate creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.miles = miles;
        this.creationDate = creationDate;
    }

    public Member(Member member){

    }

    @Id
    @SequenceGenerator(name = "VIT_RSA_GENERIC_SEQ2",sequenceName = "HR.VIT_RSA_GENERIC_SEQ2",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIT_RSA_GENERIC_SEQ2")
    @Column(name = "MEMBER_ID")
    public Long getMemberId() { return MemberId;}

    @Column(name = "FIRST_NAME")
    public String getFirstName() { return firstName;}

    @Column(name = "LAST_NAME")
    public String getLastName() { return lastName;}

    @Column(name = "MILES")
    public Long getMiles() { return miles;}

    @Column(name = "CREATION_DATE")

    public LocalDate getCreationDate() { return creationDate;}

    public void setMemberId(Long memberId) {
        MemberId = memberId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiles(Long miles) {
        this.miles = miles;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return miles == member.miles && Objects.equals(MemberId, member.MemberId) && Objects.equals(firstName, member.firstName) && Objects.equals(lastName, member.lastName) && Objects.equals(creationDate, member.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MemberId, firstName, lastName, miles, creationDate);
    }

    @Override
    public String toString() {
        return "Member{" +
                "MemberId=" + MemberId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Available miles=" + miles +
                ", creationDate=" + creationDate +
                '}';
    }
}
