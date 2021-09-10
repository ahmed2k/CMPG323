package za.ac.nwu.ac.domain.persistence;



import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "DEMO_ACCOUNT_TYPE",schema = "VITRSA_SANDBOX")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = 5909795172467694318L;

    private Long transactionId;
    private AccountType accountType;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;

    public AccountTransaction(){

    }
    public AccountTransaction(Long transactionId,AccountType accountType,Long memberId,Long amount,LocalDate transactionDate) {
       this.transactionId = transactionId;
        this.accountType = accountType;
       this.memberId = memberId;
       this.amount = amount;
       this.transactionDate = transactionDate;

    }

    @Id
    @SequenceGenerator(name = "VIT_RSA_GENERIC_SEQ",sequenceName = "VITRSA_SANDBOX.VIT_RSA_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIT_RSA_GENERIC_SEQ")
    @Column(name = "TX_ID")
    public long getTransactionId(){
        return transactionId;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    public AccountType getAccountType(){
        return accountType;
    }

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

    public void setTransactionId(Long transactionId){
        this.transactionId = transactionId;
    }

    public void setAccountType(AccountType accountType){
        this.accountType = accountType;
    }

    public void setAmount(Long amount){
        this.amount = amount;
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
