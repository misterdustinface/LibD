package base;

import functionpointers.VoidFunctionPointer;

import java.util.LinkedList;

import timers.StopwatchTimer;

public class TickingLoop implements Runnable {

	final private LinkedList<VoidFunctionPointer> functions;
	final private StopwatchTimer iterationStopwatch;
	private long millisAllowedPerUpdate = 1000 / 60;
	private volatile boolean isPaused;
	
	public TickingLoop() {
		iterationStopwatch = new StopwatchTimer();
		functions = new LinkedList<VoidFunctionPointer>();
		isPaused = false;
	}
	
	public void setUpdatesPerSecond(int UPS) {
		isPaused = (UPS <= 0);
		if (!isPaused()) {
			millisAllowedPerUpdate = 1000 / UPS;
		}
	}

	public void addFunction(VoidFunctionPointer function) {
		if (function == null) {
			throw new NullPointerException();
		}
		functions.add(function);
	}
	
	public void run() {
		while (!shouldQuitRunning()) {
			iterationStopwatch.reset();
			if (!isPaused()) {
				executeAllSpecifiedFunctions();
			}
			sleep();
		}
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	private void executeAllSpecifiedFunctions() {
		for (VoidFunctionPointer function : functions) {
			function.call();
		}
	}
	
	private void sleep() {
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	private long getSleepTime() {
		long sleeptime = millisAllowedPerUpdate - iterationStopwatch.time__ms();
		if (sleeptime < 0)
			sleeptime = 0;
		return sleeptime;
	}
	
	private boolean shouldQuitRunning() {
		return Thread.currentThread().isInterrupted();
	}
}