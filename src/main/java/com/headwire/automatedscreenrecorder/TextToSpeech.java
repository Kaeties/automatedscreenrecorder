package com.headwire.automatedscreenrecorder;

import java.io.File;
import java.io.FileOutputStream;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.VoiceId;

public class TextToSpeech {

private static AmazonPollyClient polly;
	
	public TextToSpeech(Region region) {
		// create an Amazon Polly client in a specific region
		polly = new AmazonPollyClient(new DefaultAWSCredentialsProviderChain(), 
		new ClientConfiguration());
	}
	
	public static void textToSpeech() throws Exception {
		
		TextToSpeech helloWorld = new TextToSpeech(Region.getRegion(Regions.US_EAST_1));
		
		String outputFileName = "C:\\Users\\Patrick\\Desktop\\Polly\\speech2.mp3";
		 
        SynthesizeSpeechRequest synthesizeSpeechRequest = new SynthesizeSpeechRequest()
                .withOutputFormat(OutputFormat.Mp3)
                .withVoiceId(VoiceId.Joanna)
                .withText("automated screenrecorder sounds like a really cool project.");
 
        try (FileOutputStream outputStream = new FileOutputStream(new File(outputFileName))) {
            SynthesizeSpeechResult synthesizeSpeechResult = polly.synthesizeSpeech(synthesizeSpeechRequest); 
            byte[] buffer = new byte[2 * 1024];
            int readBytes;
 
            try (java.io.InputStream in = synthesizeSpeechResult.getAudioStream()){
                while ((readBytes = in.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, readBytes);
                }
            }
        } catch (Exception e) {
            System.err.println("Exception caught: " + e);
        }
	}
}