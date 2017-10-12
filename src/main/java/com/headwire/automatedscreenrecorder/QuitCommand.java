package com.headwire.automatedscreenrecorder;

public class QuitCommand implements Command {

	private Driver driver;
	
	public QuitCommand (Driver driver) {
		this.driver = driver;
	}

	@Override
	public void execute(String... vars) throws Exception {
		driver.quit();
	}
}
