package com.headwire.automatedscreenrecorder;

public class UploadCommand implements Command {
	
	private UploadVideo upload;
	
	public UploadCommand (UploadVideo upload) {
		this.upload = upload;
	}

	@Override
	public void execute(String... vars) throws Exception {
		upload.uploadVideo();
	}
}
