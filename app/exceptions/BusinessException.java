/**
 * BusinessException.java
 * Created on 2006-7-25 19:01:48
 */

package exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>Project: com.kompakar.ehealth.base</p>
 * <p>Title: BusinessException.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006.All rights reserved.</p>
 * <p>Company: kompakar.com</p>
 * @author <a href="mailto:xuzengwei@kompakar.com.cn">Gao Yuxiang</a>
 * @version 1.0
 * @see
 */
public class BusinessException extends RuntimeException {
	private String errorCode;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getDetailMessage() {
		Throwable cause = this.getCause();
		if (cause == null) {
			cause = this;
		}
		StringWriter stringWriter = new StringWriter();
		cause.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
