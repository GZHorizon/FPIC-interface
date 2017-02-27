package com.yqsh.ifmp.util;

@SuppressWarnings("serial")
public class IfmpException extends Throwable {

	public IfmpException(){
		super();
	}

	public IfmpException(String msg){
		super(msg);
	}
	
	public IfmpException(Throwable e){
		super(e);
	}
	
	public IfmpException(String msg,Throwable e){
		super(msg, e);
	}
	
}
