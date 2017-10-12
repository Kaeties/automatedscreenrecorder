package com.headwire.automatedscreenrecorder;

public class GetItemCommand implements Command {

	private Driver driver;
	
	public GetItemCommand (Driver driver) {
		this.driver = driver;
	}
	
	@Override
	public void execute(String... vars) throws Exception {
		driver.getItem(vars[1]);
	}
}
