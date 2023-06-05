package com.nuance.middleware.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestResponseLogger {
	
	//private static final Logger logger = LogManager.getLogger(RequestResponseLogger.class);
	
	public static InputOutputParam logRequestResponse(HttpServletRequest request, HttpServletResponse response, 
								String sessionid,String locale,String channel,String language,String library) {
		
		//logger.info("Entering the method logRequestResponse()");
		InputOutputParam inputOutputParam = new InputOutputParam() ;
		inputOutputParam.setRequestUrl(request.getRequestURL().toString()); 
		inputOutputParam.setRequestMethod(request.getMethod()); 
		inputOutputParam.setResponseStatus(response.getStatus()); 
		inputOutputParam.setSessionid(sessionid);
		inputOutputParam.setLocale(locale);
		inputOutputParam.setChannel(channel);
		inputOutputParam.setLanguage(language);
		inputOutputParam.setLibrary(library);
		return inputOutputParam;
	}
	
	public static InputOutputParam logMessage(String loglevel, String message, String variable) {
		
		//logger.info("Entering the method logMessage()");
		InputOutputParam inputOutputParam = new InputOutputParam() ;
		inputOutputParam.setLogLevel(loglevel);
		inputOutputParam.setLogMessage(message);
		inputOutputParam.setLogVariable(variable);
		return inputOutputParam;
	}
	
}