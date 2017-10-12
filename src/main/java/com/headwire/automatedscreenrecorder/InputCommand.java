package com.headwire.automatedscreenrecorder;

public class InputCommand implements Command {

	private Driver driver;
	
	public InputCommand(Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) {
		driver.input(vars[1]);		
	}
}
