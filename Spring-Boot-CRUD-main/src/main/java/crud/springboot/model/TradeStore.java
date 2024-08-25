package crud.springboot.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "TRADE_STORE")
public class TradeStore {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long tradeId;
	
	@Column(name = "VERSION")
	private long version;
	
	@Column(name = "COUNTPARTY_ID")
	private String counterPartyId ;
	
	@Column(name = "BOOK_ID")
	private String bookId ;
	
	@Column(name = "MATURITY_DATE")
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	private LocalDate maturityDate;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate createdDate;
	
	@Column(name = "EXPIRED")
	private boolean expired ;

	public long getTradeId() {
		return tradeId;
	}

	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "TraadeStore [version=" + version + ", CounterPartyId=" + counterPartyId + ", bookId=" + bookId
				+ ", maturityDate=" + maturityDate + ", createdDate=" + createdDate + ", expired=" + expired + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(counterPartyId, bookId, createdDate, expired, maturityDate, tradeId, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeStore other = (TradeStore) obj;
		return Objects.equals(counterPartyId, other.counterPartyId) && Objects.equals(bookId, other.bookId)
				&& Objects.equals(createdDate, other.createdDate) && expired == other.expired
				&& Objects.equals(maturityDate, other.maturityDate) && tradeId == other.tradeId
				&& version == other.version;
	}
	
	
	

}
