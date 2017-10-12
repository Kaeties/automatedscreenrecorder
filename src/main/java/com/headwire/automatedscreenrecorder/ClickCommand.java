package com.headwire.automatedscreenrecorder;

public class ClickCommand implements Command {

	private Driver driver;
	
	public ClickCommand(Driver driver) {
		this.driver = driver;
	}
	
	@Override
	public void execute(String... vars) {
		driver.click(vars[1], vars[2]);
	}
}
