package com.headwire.automatedscreenrecorder;

public class ReturnKeyCommand implements Command {

	private Driver driver;
	
	public ReturnKeyCommand(Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) {
		driver.returnKey();		
	}
}
