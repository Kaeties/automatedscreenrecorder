package com.headwire.automatedscreenrecorder;

public class GoToCommand implements Command {

	private Driver driver;
	
	public GoToCommand (Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) throws Exception {
		driver.goTo(vars[1], vars[2]);
	}
}
