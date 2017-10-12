package com.headwire.automatedscreenrecorder;

public class RightclickCommand implements Command {

	private Driver driver;
	
	public RightclickCommand (Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) throws Exception {
		driver.rightclick(vars[1], vars[2]);
	}
}
