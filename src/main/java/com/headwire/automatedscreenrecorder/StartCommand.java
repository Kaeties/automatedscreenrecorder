package com.headwire.automatedscreenrecorder;

public class StartCommand implements Command {

	private Recorder recorder;
	
	public StartCommand (Recorder recorder) {
		this.recorder = recorder;
	}

	@Override
	public void execute(String... vars) throws Exception {
		recorder.start();
	}
}
