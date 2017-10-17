package com.headwire.automatedscreenrecorder;

public class TextToSpeechCommand implements Command {

	private TextToSpeech textToSpeech;
	
	public TextToSpeechCommand(TextToSpeech textToSpeech) {
		this.textToSpeech = textToSpeech;
	}
	
	@Override
	public void execute(String... vars) throws Exception {
		textToSpeech.transform(vars[1], vars[2]);
	}
}
