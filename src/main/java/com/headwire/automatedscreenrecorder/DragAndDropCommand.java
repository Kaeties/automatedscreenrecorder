package com.headwire.automatedscreenrecorder;

public class DragAndDropCommand implements Command {

	private Driver driver;
	
	public DragAndDropCommand(Driver driver) {
		this.driver = driver;
	}
	
	@Override
	public void execute(String... vars) throws Exception {
		driver.dragAndDrop(vars[1], vars[2]);
	}
}
