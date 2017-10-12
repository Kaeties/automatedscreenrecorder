package com.headwire.automatedscreenrecorder;

public class DoubleclickCommand implements Command {

	private Driver driver;
	
	public DoubleclickCommand (Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) throws Exception {
		driver.doubleclick(vars[1], vars[2]);
	}
}
