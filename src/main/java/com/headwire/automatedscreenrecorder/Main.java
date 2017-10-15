package com.headwire.automatedscreenrecorder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

	private static Driver driver = Driver.getInstance();
	private static Recorder recorder = new Recorder();
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
		commands.put("start", new StartCommand(recorder));
		commands.put("stop", new StopCommand(recorder));
		commands.put("use", new UseCommand(driver));
		commands.put("open", new OpenCommand(driver));
		commands.put("click", new ClickCommand(driver));
		commands.put("goTo", new GoToCommand(driver));
		commands.put("input", new InputCommand(driver));
		commands.put("rightclick", new RightclickCommand(driver));
		commands.put("getItem", new GetItemCommand(driver));
		commands.put("doubleclick", new DoubleclickCommand(driver));
		commands.put("highlight", new HighlightCommand(driver));
		commands.put("scrolldown", new ScrolldownCommand(driver));
		commands.put("scrollup", new ScrollupCommand(driver));
		commands.put("enter", new EnterCommand(driver));
		commands.put("quit", new QuitCommand(driver));
		commands.put("goToAndClick", new GoToAndClickCommand(driver));
		commands.put("dragAndDrop", new DragAndDropCommand(driver));
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
