package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.domain.persistence.Member;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "Member",
            description = "A DTO that represents a Member")
public class MemberDto implements Serializable{

    private static final long serialVersionUID = -3675411777951570019L;
    private String firstName;
    private String lastName;
    private Long miles;
    private LocalDate creationDate;

    public  MemberDto(){

    }

    public MemberDto(String firstName, String lastName, Long miles, LocalDate creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.miles = miles;
        this.creationDate = creationDate;
    }

    public MemberDto(Member member){
        this.setFirstName(member.getFirstName());
        this.setLastName(member.getLastName());
        this.setMiles(member.getMiles());
        this.setCreationDate(member.getCreationDate());
    }

   @ApiModelProperty(position = 1,
           value = "Member first name",
           name = "FirstName",
           notes = "uniquely identifes a member",
           dataType = "java.Lang.String",
           example = "Ahmed",
           required = true)
    public String getFirstName() { return firstName;}
    public void setFirstName(String firstName) { this.firstName = firstName;}

   @ApiModelProperty(position = 2,
           value = "Member last name",
           name = "LastName",
           notes = "uniquely identifes a member",
           dataType = "java.Lang.String",
           example = "Hussein",
           required = true)
    public String getLastName() {return lastName;}
    public void setLastName(String lastName){this.lastName = lastName; }

   @ApiModelProperty(position = 3,
            value = "Number of miles for member",
           name = "Miles",
           notes = "uniquely identifes number of miles for a member",
           dataType = "java.Lang.String",
           example = "1500",
           required = true)
   public Long getMiles() {return miles;}
 public void setMiles(Long miles){this.miles = miles;}

    @ApiModelProperty(position = 4,
            value = "CreationDate of the member",
            name = "CreationDate",
            notes = "uniquely identifes the date on which the member was created",
            dataType = "java.Lang.String",
            example = "2020-01-01",
            required = true)
    public LocalDate getCreationDate() {return creationDate;}

    public void setCreationDate(LocalDate creationDate){this.creationDate = creationDate;}

    @JsonIgnore
    public Member getMembers(){
        return new Member(getFirstName(),getLastName(),getMiles(),getCreationDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return miles == memberDto.miles && Objects.equals(firstName, memberDto.firstName) && Objects.equals(lastName, memberDto.lastName) && Objects.equals(creationDate, memberDto.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, miles, creationDate);
    }
}
