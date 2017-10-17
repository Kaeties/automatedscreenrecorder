package com.headwire.automatedscreenrecorder;

import java.util.ArrayList;
import java.util.Arrays;

public class AudioVideoMerge {
	
	private static ArrayList<String> audioInput = new ArrayList<>();
	private static int pointer = 0;
	private static ArrayList<Long> seconds = new ArrayList<>();
	private static ArrayList<String> secondsAsString = new ArrayList<>();

	public boolean mergeAudioVideo(String var, String ffmpegPath) {

		/**** code wie er im cmd eingegeben wird
		 * 
		 * String[] exeCmd = new String[]{"C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\ffmpeg", "-i", "C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\audio.wav", "-i", "C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\video.mp4" ,"-acodec", "copy", "-vcodec", "copy", "C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\eclipsefinalmpeeeee.mp4"};
		 * ffmpeg -i test_video.avi -i beep.mp3 -i beeps.mp3 -filter_complex "[1]adelay=5000[s2]; [2]adelay=10300[s3]; [s2][s3]amix=2[mixout]" -map 0:v -map [mixout] -c:v copy result.avi
		 */
		
		String[] begin = {ffmpegPath + "\\ffmpeg", "-i", Recorder.path + "\\" + Recorder.videoname + ".avi"};
		ArrayList<String> exeCmd = new ArrayList<>();
		exeCmd.addAll(Arrays.asList(begin));
		exeCmd.addAll(audioInput);
		exeCmd.add("-filter_complex");
		
		seconds = Main.getSeconds();
		String string = "\"";
		for(int i = 0, j = i+1; i<seconds.size(); i++, j++) {
			string = string.concat("[" + j + "]" + "adelay=" + seconds.get(i)*1000 + "[s" + j + "];");
		}
		
		for(int i = 1; i<=seconds.size(); i++) {
			string = string.concat("[s" + i + "]");
		}
		string = string.concat("amix=" + seconds.size() + "[mixout]\"");
		
		exeCmd.add(string);
		
		String[] end = {"-map", "0:v", "-map", "[mixout]", "-c:v", "copy"}; 
		
		exeCmd.addAll(Arrays.asList(end));
		
		exeCmd.add(Recorder.path + "\\" + var + ".avi");
		
		String[] command = exeCmd.toArray(new String[exeCmd.size()]);

		ProcessBuilder pb = new ProcessBuilder(command);
		boolean exeCmdStatus = executeCMD(pb);

		return exeCmdStatus;
	} //End doSomething Function
	
	public void collectAudios(String var) {
		audioInput.add("-i");
		pointer++;
		audioInput.add(pointer, var);
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
}
