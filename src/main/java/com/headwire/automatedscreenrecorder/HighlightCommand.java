package com.headwire.automatedscreenrecorder;

public class HighlightCommand implements Command {

	private Driver driver;
	
	public HighlightCommand (Driver driver) {
		this.driver = driver;
	}
	
	@Override
	public void execute(String... vars) {
		driver.highlight(vars[1], vars[2]);
	}
}
