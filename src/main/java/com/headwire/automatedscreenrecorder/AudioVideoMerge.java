package com.headwire.automatedscreenrecorder;

public class AudioVideoMerge {
	
public boolean mergeAudioVideo() {
		
		/**** code wie er im cmd eingegeben wird
		 * 
		 * ffmpeg -y -i test_video.avi -itsoffset 00:00:10 -i search.mp3 -map 0:0 -map 1:0 -c:v copy -preset ultrafast -async 1 out.avi
		 */
	
		 String[] exeCmd = new String[]{"C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\ffmpeg", "-i", "C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\audio.wav", "-i", "C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\video.mp4" ,"-acodec", "copy", "-vcodec", "copy", "C:\\Users\\st_pa\\ffmpeg\\ffmpeg-3.3.3-win64-static\\bin\\eclipsefinalmpeeeee.mp4"};

		 
		 
		 ProcessBuilder pb = new ProcessBuilder(exeCmd);
		 boolean exeCmdStatus = executeCMD(pb);

		 return exeCmdStatus;
		} //End doSomething Function

		private boolean executeCMD(ProcessBuilder pb)
		{
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
