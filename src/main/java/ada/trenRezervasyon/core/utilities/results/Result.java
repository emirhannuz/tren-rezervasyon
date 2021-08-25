package ada.trenRezervasyon.core.utilities.results;

public class Result {

	private boolean success;
	private String message;

	public Result(boolean success, String message) {
		this(success);
		setMessage(message);
	}

	public Result(boolean success) {
		setSuccess(success);
	}

	public boolean isSuccess() {
		return success;
	}

	protected void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

}
