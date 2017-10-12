package com.headwire.automatedscreenrecorder;

public class StopCommand implements Command {

	private Recorder recorder;
	
	public StopCommand (Recorder recorder) {
		this.recorder = recorder;
	}

	@Override
	public void execute(String... vars) throws Exception {
		recorder.stop();
	}
}
