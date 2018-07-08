package ac.daffodil.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Entity
@Table(name = "financial")
public class Financial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="financial_id")
    private Long financialId;

    @Column(name="organization_id")
    private Long organizationId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat( pattern="yyyy-MM-dd")
    @Column(name="last_update_date")
    private Date lastUpdateDate;

    @Column(name="collection_cash")
    private Long collectionCash;

    @Column(name="collection_bank")
    private Long collectionBank;

    @Column(name="total_payment_cash")
    private Long totalPaymentCash;

    @Column(name="total_payment_bank")
    private Long totalPaymentBank;

    @Column(name="balance_bank")
    private Long balanceBank;

    @Column(name="balance_cash")
    private Long balanceCash;

    @Column(name="accounts_receivable")
    private Long accountsReceivable;

    @Column(name="accounts_payable")
    private Long accountsPayable;

    @Column(name="ondate_admited_students")
    private Long ondateAdmitedStudents;

    @Column(name="new_students")
    private Long newStudents;

    public Financial() {
    }

    public Financial(Long organizationId, Date lastUpdateDate, Long collectionCash, Long collectionBank, Long totalPaymentCash, Long totalPaymentBank, Long balanceBank, Long balanceCash, Long accountsReceivable, Long accountsPayable, Long ondateAdmitedStudents, Long newStudents) {
        this.organizationId = organizationId;
        this.lastUpdateDate = lastUpdateDate;
        this.collectionCash = collectionCash;
        this.collectionBank = collectionBank;
        this.totalPaymentCash = totalPaymentCash;
        this.totalPaymentBank = totalPaymentBank;
        this.balanceBank = balanceBank;
        this.balanceCash = balanceCash;
        this.accountsReceivable = accountsReceivable;
        this.accountsPayable = accountsPayable;
        this.ondateAdmitedStudents = ondateAdmitedStudents;
        this.newStudents = newStudents;
    }

    public Long getFinancialId() {
        return financialId;
    }

    public void setFinancialId(Long financialId) {
        this.financialId = financialId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getCollectionCash() {
        return collectionCash;
    }

    public void setCollectionCash(Long collectionCash) {
        if (collectionCash == null)
            this.collectionCash = 0L;
        else
            this.collectionCash = collectionCash;
    }

    public Long getCollectionBank() {
        return collectionBank;
    }

    public void setCollectionBank(Long collectionBank) {
        if (collectionBank == null)
            this.collectionBank = 0L;
        else
            this.collectionBank = collectionBank;
    }

    public Long getTotalPaymentCash() {
        return totalPaymentCash;
    }

    public void setTotalPaymentCash(Long totalPaymentCash) {
        if (totalPaymentCash == null)
            this.totalPaymentCash = 0L;
        else
            this.totalPaymentCash = totalPaymentCash;
    }

    public Long getTotalPaymentBank() {
        return totalPaymentBank;
    }

    public void setTotalPaymentBank(Long totalPaymentBank) {
        if (totalPaymentBank == null)
            this.totalPaymentBank = 0L;
        else
            this.totalPaymentBank = totalPaymentBank;
    }

    public Long getBalanceBank() {
        return balanceBank;
    }

    public void setBalanceBank(Long balanceBank) {
        if (balanceBank == null)
            this.balanceBank = 0L;
        else
            this.balanceBank = balanceBank;
    }

    public Long getBalanceCash() {
        return balanceCash;
    }

    public void setBalanceCash(Long balanceCash) {
        if (balanceCash == null)
            this.balanceCash = 0L;
        else
            this.balanceCash = balanceCash;
    }

    public Long getAccountsReceivable() {
        return accountsReceivable;
    }

    public void setAccountsReceivable(Long accountsReceivable) {
        if (accountsReceivable == null)
            this.accountsReceivable = 0L;
        else
            this.accountsReceivable = accountsReceivable;
    }

    public Long getAccountsPayable() {
        return accountsPayable;
    }

    public void setAccountsPayable(Long accountsPayable) {
        if (accountsPayable == null)
            this.accountsPayable = 0L;
        else
            this.accountsPayable = accountsPayable;
    }

    public Long getOndateAdmitedStudents() {
        return ondateAdmitedStudents;
    }

    public void setOndateAdmitedStudents(Long ondateAdmitedStudents) {
        if (ondateAdmitedStudents == null)
            this.ondateAdmitedStudents = 0L;
        else
            this.ondateAdmitedStudents = ondateAdmitedStudents;
    }

    public Long getNewStudents() {
        return newStudents;
    }

    public void setNewStudents(Long newStudents) {
        if (newStudents == null)
            this.newStudents = 0L;
        else
            this.newStudents = newStudents;
    }

    @Override
    public String toString() {
        return "Financial{" +
                "financialId=" + financialId +
                ", organizationId=" + organizationId +
                ", lastUpdateDate=" + lastUpdateDate +
                ", collectionCash=" + collectionCash +
                ", collectionBank=" + collectionBank +
                ", totalPaymentCash=" + totalPaymentCash +
                ", totalPaymentBank=" + totalPaymentBank +
                ", balanceBank=" + balanceBank +
                ", balanceCash=" + balanceCash +
                ", accountsReceivable=" + accountsReceivable +
                ", accountsPayable=" + accountsPayable +
                ", ondateAdmitedStudents=" + ondateAdmitedStudents +
                ", newStudents=" + newStudents +
                '}';
    }
}
