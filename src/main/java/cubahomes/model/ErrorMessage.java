package cubahomes.model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;

@Embeddable
public class ErrorMessage {

	private String message;
	private Date timestamp;
	@Enumerated
	private HttpStatus status;
	
	
	public ErrorMessage() {
		// TODO Auto-generated constructor stub
	}

	public ErrorMessage(String message) {
		this.message = message;
	}

	public ErrorMessage(String message, Date timestamp, HttpStatus status) {
		this.message = message;
		this.timestamp = timestamp;
		this.status = status;
	}

	protected String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

	protected Date getTimestamp() {
		return timestamp;
	}

	protected void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	protected HttpStatus getStatus() {
		return status;
	}

	protected void setStatus(HttpStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorMessage other = (ErrorMessage) obj;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErrorMessage [message=" + message + ", timestamp=" + timestamp + ", status=" + status + "]";
	}
	
	
}
