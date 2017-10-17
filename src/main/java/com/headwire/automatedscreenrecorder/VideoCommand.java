package com.headwire.automatedscreenrecorder;

public class VideoCommand implements Command {

	private AudioVideoMerge video;
	
	public VideoCommand(AudioVideoMerge video) {
		this.video = video;
	}

	@Override
	public void execute(String... vars) throws Exception {
		video.collectAudios(vars[0]);
	}
}
