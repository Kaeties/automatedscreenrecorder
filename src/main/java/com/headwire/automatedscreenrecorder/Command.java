package com.headwire.automatedscreenrecorder;

public interface Command {
	
	public abstract void execute(String... vars) throws Exception;

}
