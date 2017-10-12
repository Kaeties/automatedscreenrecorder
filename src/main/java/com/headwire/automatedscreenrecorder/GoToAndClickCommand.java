package com.headwire.automatedscreenrecorder;

public class GoToAndClickCommand implements Command {

	private Driver driver;
	
	public GoToAndClickCommand(Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) throws Exception {
		driver.goToAndClick(vars[1], vars[2]);
	}
}
