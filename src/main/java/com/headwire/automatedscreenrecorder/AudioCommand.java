package com.headwire.automatedscreenrecorder;

public class AudioCommand implements Command {
	
	private AudioVideoMerge audio;
	
	public AudioCommand(AudioVideoMerge audio) {
		this.audio = audio;
	}

	@Override
	public void execute(String... vars) throws Exception {
		audio.collectAudios(vars[0]);
	}
}
