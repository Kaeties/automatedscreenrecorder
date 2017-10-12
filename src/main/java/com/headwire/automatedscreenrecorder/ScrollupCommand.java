package com.headwire.automatedscreenrecorder;

public class ScrollupCommand implements Command {

	private Driver driver;
	
	public ScrollupCommand (Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) throws Exception {
		driver.scrollup(vars[1]);
	}
}
