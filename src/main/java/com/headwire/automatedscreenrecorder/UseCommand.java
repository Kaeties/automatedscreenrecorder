package com.headwire.automatedscreenrecorder;

public class UseCommand implements Command {

private Driver driver;
	
	public UseCommand (Driver driver) {
		this.driver = driver;
	}
	
	@Override
	public void execute(String...vars) {
		driver.use(vars[1]);
	}
}
