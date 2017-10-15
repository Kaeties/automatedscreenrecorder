package com.headwire.automatedscreenrecorder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

	private static Driver testDriver = new Driver();
	private static Recorder testRecorder = new Recorder();
	private static Map<String, Command> commands = new HashMap<>();
	private static String path;
	private static long now;
	private static boolean started = false;

	public static void main(String[] args) throws Exception {


		if(args.length < 2) {
			System.out.println("automatedscreenrecorder <script> <driverpath>");
		} else {
			setDriverPath(args[1]);
			readFile(args[0]);
		}
	}
	

	static {
		commands.put("start", new StartCommand(testRecorder));
		commands.put("stop", new StopCommand(testRecorder));
		commands.put("use", new UseCommand(testDriver));
		commands.put("open", new OpenCommand(testDriver));
		commands.put("click", new ClickCommand(testDriver));
		commands.put("goTo", new GoToCommand(testDriver));
		commands.put("input", new InputCommand(testDriver));
		commands.put("rightclick", new RightclickCommand(testDriver));
		commands.put("getItem", new GetItemCommand(testDriver));
		commands.put("doubleclick", new DoubleclickCommand(testDriver));
		commands.put("highlight", new HighlightCommand(testDriver));
		commands.put("scrolldown", new ScrolldownCommand(testDriver));
		commands.put("scrollup", new ScrollupCommand(testDriver));
		commands.put("enter", new EnterCommand(testDriver));
		commands.put("quit", new QuitCommand(testDriver));
		commands.put("goToAndClick", new GoToAndClickCommand(testDriver));
		commands.put("dragAndDrop", new DragAndDropCommand(testDriver));
	}

	private static void readFile(String filePath) throws Exception {
		BufferedReader reader = null;

		try {
			String currentLine;
			reader = new BufferedReader(new FileReader(filePath));
			while ((currentLine = reader.readLine()) != null) {
				String[] strArr = currentLine.split(";");
				commands.get(strArr[0]).execute(strArr);
				writeLogFile(strArr[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static void writeLogFile(String command) {
		if(command.equals("start")) {
			setNow();
			Logger logger = Logger.getLogger("MyLogger");
			logger.info(command + " " + ((System.currentTimeMillis() - now)/1000) + " seconds");
			started = true;
		} else if (started == true) {
			Logger logger = Logger.getLogger("MyLogger");
			logger.info(command + " " + ((System.currentTimeMillis() - now)/1000) + " seconds");
			started = true;
		}
	}

	private static void setNow() {
		now = System.currentTimeMillis();
	}

	private static void setDriverPath(String driverPath) {
		path = driverPath;
	}

	public static String getDriverPath() {
		return path;	
	}	
}
