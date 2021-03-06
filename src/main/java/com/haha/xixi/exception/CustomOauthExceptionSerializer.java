package com.haha.xixi.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 * @data 2018年11月4日 下午5:37:40
 *
 * @desc 类描述
 *       <li>
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {
	
	private static final long serialVersionUID = -3067152331573776010L;

	public CustomOauthExceptionSerializer() {
		super(CustomOauthException.class);
	}

	@Override
	public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		gen.writeStartObject();
		gen.writeStringField("error", String.valueOf(value.getHttpErrorCode()));
		gen.writeStringField("message", value.getMessage());
		gen.writeStringField("path", request.getServletPath());
		gen.writeStringField("timestamp", String.valueOf(new Date().getTime()));
		if (value.getAdditionalInformation() != null) {
			for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
				String key = entry.getKey();
				String add = entry.getValue();
				gen.writeStringField(key, add);
			}
		}
		gen.writeEndObject();
	}
}
