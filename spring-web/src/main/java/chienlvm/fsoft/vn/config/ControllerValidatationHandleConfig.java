package chienlvm.fsoft.vn.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import chienlvm.fsoft.vn.entity.ApiError;
import chienlvm.fsoft.vn.utils.ResponseObject;

@ControllerAdvice
public class ControllerValidatationHandleConfig {

	@Autowired
	private MessageSource msgMessageSource;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ResponseEntity<ResponseObject> processValidationError(BindException ex) {
		List<ApiError> errors = new ArrayList<ApiError>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			ApiError err = new ApiError();
			err.setField(error.getField());
			err.setMessage(processFieldError(error));
			errors.add(err);
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			ApiError err = new ApiError();
			err.setField(error.getObjectName());
			err.setMessage(processFieldErrorObj(error));
			errors.add(err);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseObject("FAILED", "Đã có lỗi xảy ra", errors)
      );
	}

	private String processFieldError(FieldError error) {
		String msg = null;
		if (error != null) {
			Locale currentLocale = LocaleContextHolder.getLocale();
			msg = msgMessageSource.getMessage(error.getDefaultMessage(), null, currentLocale);
		}
		return msg;
	}
	private String processFieldErrorObj(ObjectError error) {
		String msg = null;
		if (error != null) {
			Locale currentLocale = LocaleContextHolder.getLocale();
			msg = msgMessageSource.getMessage(error.getDefaultMessage(), null, currentLocale);
		}
		return msg;
	}
}
