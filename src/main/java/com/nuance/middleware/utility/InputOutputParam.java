package com.nuance.middleware.utility;

public class InputOutputParam {
	
    private String sessionid;
    private String locale;
    private String channel;
    private String language;
    private String library;
    private String requestUrl;
    private String requestMethod;
    private int responseStatus;
    
    private String logLevel;
    private String logMessage;
    private String logVariable;
    
    
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLibrary() {
		return library;
	}
	public void setLibrary(String library) {
		this.library = library;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public int getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public String getLogVariable() {
		return logVariable;
	}
	public void setLogVariable(String logVariable) {
		this.logVariable = logVariable;
	}
	
	
	
	@Override
	public String toString() {
		
		if(null != logLevel &&  "" != logLevel && null != logVariable && "" != logVariable) {
			return "InputOutputParam {" +
					 "logLevel='" + logLevel + '\'' +
					 ", logMessage='" + logMessage + logVariable + '\'' +  
					 "}";
		}else if(null != logLevel &&  "" != logLevel ) {
			return "InputOutputParam {" +
					 "logLevel='" + logLevel + '\'' +
					 ", logMessage='" + logMessage + '\'' +  
					 "}";
		}else {
			return "InputOutputParam {" +
					"sessionid='" + sessionid + '\'' +
					", locale='" + locale + '\'' +
					", channel='" + channel + '\'' +
					", language='" + language + '\'' +
					 ", library='" + library + '\'' +
					 ", requestUrl='" + requestUrl + '\'' +
					 ", requestMethod='" + requestMethod + '\'' +
					 ", responseStatus=" + responseStatus + 
					 "}";
		}
	}
	
}