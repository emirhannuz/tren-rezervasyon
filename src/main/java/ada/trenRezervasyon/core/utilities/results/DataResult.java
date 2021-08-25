package ada.trenRezervasyon.core.utilities.results;

public class DataResult<T> extends Result {

	private T data;

	public DataResult(T data, boolean success, String message) {
		super(success, message);
		setData(data);
	}

	public DataResult(T data, boolean success) {
		super(success);
		setData(data);
	}

	public T getData() {
		return data;
	}

	protected void setData(T data) {
		this.data = data;
	}

}
