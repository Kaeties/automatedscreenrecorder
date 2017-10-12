package com.headwire.automatedscreenrecorder;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

public class Driver extends TypeCondition {

	public static WebDriver driver;
	public static double multiplicator;

	private void initChromeDriver() {
		String exePath = Main.getDriverPath();
		System.setProperty("webdriver.chrome.driver", exePath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("--start-fullscreen");
		driver = new ChromeDriver(options);
	}

	private void initFirefoxDriver() {
		String exePath = Main.getDriverPath();
		System.setProperty("webdriver.gecko.driver", exePath);
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--start-fullscreen");
		driver = new FirefoxDriver(options);
	}

	public void use(String var) {
		if(var.equals("chrome")) {
			initChromeDriver();
		} else if (var.equals("firefox")) {
			initFirefoxDriver();
		}
		setMultiplicator();
	}

	public void open(String var) {
		driver.get(var);
	}

	public void click(String var, String type) {
		super.typeCondition(var, type).click();	
	}

	public void enter() {
		getActiveElement().submit();		
	}

	public void input(String var) {
		getActiveElement().sendKeys(var);
	}

	public WebElement getActiveElement() {
		return driver.switchTo().activeElement();
	}

	public void quit() {
		driver.quit();
	}

	public void rightclick(String var, String type) {
		Actions action = new Actions(driver);
		action.moveToElement(super.typeCondition(var, type));
		action.contextClick(super.typeCondition(var, type)).build().perform();
	}

	public void doubleclick(String var, String type) {
		Actions action = new Actions(driver);
		action.moveToElement(super.typeCondition(var, type));
		action.doubleClick(super.typeCondition(var, type)).build().perform();
	}

	public void setMultiplicator() {
		GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = g.getDefaultScreenDevice();
		long lDeviceResolution = device.getDisplayMode().getWidth();
		double deviceResolution = (double) lDeviceResolution;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long lBrowserResolution = (long) js.executeScript("return screen.width;");
		double browserResolution = (double) lBrowserResolution;
		multiplicator = deviceResolution/browserResolution;
	}

	public double getMultiplicator() {
		return multiplicator;
	}

	public void scrolldown (String var) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0," + var + ");");
	}

	public void scrollup (String var) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0, -" + var + ");");
	}

	public void highlight(String var, String type) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", super.typeCondition(var, type));
	}

	public void goToAndClick(String var, String type) throws Exception {
		goTo(var, type);
		click(var, type);
	}

	private static Robot initRobot() throws Exception {
		Robot robot = new Robot();
		return robot;
	}

	public void getItem(String var) throws Exception {
		int number = Integer.parseInt(var);

		for(int i = 1; i <= number; i++) {
			initRobot().keyPress(KeyEvent.VK_DOWN);
			initRobot().keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(500);
		}

		initRobot().keyPress(KeyEvent.VK_ENTER);
		initRobot().keyRelease(KeyEvent.VK_ENTER);
	}

	public void goTo(String var, String type) throws Exception {
		double[] coordinates;
		coordinates = prepareCoordinates(var, type);
		mouseMovement(coordinates);
	}
	
	public void dragAndDrop(String var, String type) throws Exception {
		double[] coordinates;
		coordinates = prepareCoordinates(var, type);
		initRobot().mousePress(InputEvent.BUTTON1_MASK);
		mouseMovement(coordinates);
		initRobot().mouseRelease(InputEvent.BUTTON1_MASK);
	}

	private double[] prepareCoordinates(String var, String type) {
		Point pTo = super.typeCondition(var, type).getLocation();

		java.awt.Point mouse = MouseInfo.getPointerInfo().getLocation();

		double pFromX = mouse.x;
		double pFromY = mouse.y;

		double pToX = pTo.x*Driver.multiplicator;
		double pToY = pTo.y*Driver.multiplicator;

		Dimension toSize = super.typeCondition(var, type).getSize();

		int xCentreTo = toSize.width / 2;
		int yCentreTo = toSize.height / 2;

		pToX += xCentreTo;
		pToY += yCentreTo;
		
		double[] coordinates = {pFromX, pFromY, pToX, pToY};
		return coordinates;
	}
	
	private void mouseMovement(double[] coordinates) throws Exception {
		double pFromX = coordinates[0];
		double pFromY = coordinates[1];
		double pToX = coordinates[2];
		double pToY = coordinates[3];
		
		int i = (int) pFromX;
		int j = (int) pFromY;

		if(pFromX < pToX && pFromY < pToY) {
			for(i = (int) pFromX; i<pToX; i++) {
				initRobot().mouseMove(i, j);
				Thread.sleep(2);
				if(j<pToY) {
					j++;
				}
			}

		} else if(pFromX < pToX && pFromY > pToY) {
			for(i = (int) pFromX; i<pToX; i++) {
				initRobot().mouseMove(i, j);
				Thread.sleep(2);
				if(j>pToY) {
					j--;
				}
			}

		} else if(pFromX > pToX && pFromY < pToY) {
			for(i = (int) pFromX; i>pToX; i--) {
				initRobot().mouseMove(i, j);
				Thread.sleep(2);
				if(j<pToY) {
					j++;
				}
			}

		} else if(pFromX > pToX && pFromY > pToY) {
			for(i = (int) pFromX; i>pToX; i--) {
				initRobot().mouseMove(i, j);
				Thread.sleep(2);
				if(j>pToY) {
					j--;
				}
			}
		}
	}
}
