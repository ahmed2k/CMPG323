package za.ac.nwu.ac.domain.persistence;



import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DEMO_ACCOUNT_TYPE",schema = "HR")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = 5909795172467694318L;

    private Long transactionId = 0l;
    private AccountType accountType;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;
    private AccountTransactionDetails details;

    public AccountTransaction(){

    } public AccountTransaction(Long transactionId,Long memberId,Long amount,LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
    public AccountTransaction(Long transactionId,AccountType accountType,Long memberId,Long amount,LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.accountType = accountType;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }


    @Id
    @SequenceGenerator(name = "VIT_RSA_GENERIC_SEQ",sequenceName = "HR.VIT_RSA_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIT_RSA_GENERIC_SEQ")
    @Column(name = "TX_ID")
    public long getTransactionId(){ return transactionId;}

    @Column(name = "MEMBER_ID")
    public long getMemberId(){
        return memberId;
    }


    @Column(name = "AMOUNT")
    public long getAmount(){
        return amount;
    }

    @Column(name = "TX_DATE")
    public LocalDate getTransactionDate(){
        return transactionDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    public AccountType getAccountType(){
        return accountType;
    }

    @OneToMany(targetEntity = AccountTransactionDetails.class,fetch = FetchType.LAZY,mappedBy = "accountTransaction")


    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccountType(AccountType accountType){
        this.accountType = accountType;
    }

    public void setAmount(Long amount){
        this.amount = amount;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    public void setDetails(AccountTransactionDetails details){this.details = details;}

    public AccountTransactionDetails getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionId=" + transactionId +
                ", accountTypeId=" + accountType +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(accountType, that.accountType) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountType, memberId, amount, transactionDate);
    }
}
