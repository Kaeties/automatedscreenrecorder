package com.headwire.automatedscreenrecorder;

public class ScrolldownCommand implements Command {

	private Driver driver;
	
	public ScrolldownCommand (Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) throws Exception {
		driver.scrolldown(vars[1]);
	}
}
