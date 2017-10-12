package com.headwire.automatedscreenrecorder;

public class EnterCommand implements Command {

	private Driver driver;
	
	public EnterCommand(Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) {
		driver.enter();
	}
}
