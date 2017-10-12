package com.headwire.automatedscreenrecorder;

public class OpenCommand implements Command {

	private Driver driver;
	
	public OpenCommand (Driver driver) {
		this.driver = driver;
	}
	
	@Override
	public void execute(String... vars) {
		driver.open(vars[1]);
	}
}
