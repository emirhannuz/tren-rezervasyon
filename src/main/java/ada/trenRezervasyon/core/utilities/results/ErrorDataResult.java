package ada.trenRezervasyon.core.utilities.results;

public class ErrorDataResult<T> extends DataResult<T> {
	
	public ErrorDataResult() {
		super(null, false);
	}

	public ErrorDataResult(T data) {
		super(data, false);
	}

	public ErrorDataResult(String message) {
		this(null, message);
	}

	public ErrorDataResult(T data, String message) {
		super(data, false, message);
	}
}
