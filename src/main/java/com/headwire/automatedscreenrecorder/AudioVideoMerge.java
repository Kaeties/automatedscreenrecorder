package com.headwire.automatedscreenrecorder;

import java.util.ArrayList;
import java.util.Arrays;

public class AudioVideoMerge {
	
	private static String[] audioInput = null;
	private static int pointer = 0;
	private static String[] seconds;

	public boolean mergeAudioVideo(String var) {

		/**** code wie er im cmd eingegeben wird
		 * 
		 * String[] exeCmd = new String[]{"C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\ffmpeg", "-i", "C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\audio.wav", "-i", "C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\video.mp4" ,"-acodec", "copy", "-vcodec", "copy", "C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\eclipsefinalmpeeeee.mp4"};
		 * ffmpeg -i test_video.avi -i beep.mp3 -i beeps.mp3 -filter_complex "[1]adelay=5000[s2]; [2]adelay=10300[s3]; [s2][s3]amix=2[mixout]" -map 0:v -map [mixout] -c:v copy result.avi
		 */
		
		String[] begin = {"ffmpeg", "-i", Recorder.videoname};
		ArrayList<String> exeCmd = null;
		
		exeCmd.addAll(Arrays.asList(begin));
		

		for(int i = 0; i < audioInput.length; i++) {
			exeCmd.add(audioInput[i]);
			exeCmd.add("-i");
		}
		
		exeCmd.add("-filter_complex");
		
		exeCmd.addAll(Arrays.asList(getSeconds()));
		
		String[] end = {"-map", "0:v", "-map", "[mixout]", "-c:v", "copy"}; 
		
		exeCmd.addAll(Arrays.asList(end));
		
		exeCmd.add(var);

		ProcessBuilder pb = new ProcessBuilder(exeCmd);
		boolean exeCmdStatus = executeCMD(pb);

		return exeCmdStatus;
	} //End doSomething Function
	
	public void collectAudios(String var) {
		audioInput[pointer] = var;
		pointer++;
	}
	

	private boolean executeCMD(ProcessBuilder pb) {
		pb.redirectErrorStream(true);
		Process p = null;

		try {
			p = pb.start();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("oops");
			p.destroy();
			return false;
		}
		// wait until the process is done
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("woopsy");
			p.destroy();
			return false;
		}
		return true;
	}// End function executeCMD

	public void setSeconds(long[] seconds) {
		for(int i = 0; i < seconds.length; i++){
		    AudioVideoMerge.seconds[i] = String.valueOf(seconds[i]);
		}
	}
	
	public static String[] getSeconds() {
		return seconds;
	}
}
