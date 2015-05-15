package base;

import java.util.HashSet;

import datastructures.Table;

public class Application {
	
	final private Table<Thread> threads;
	final private HashSet<String> names;
	
	public Application() {
		threads = new Table<Thread>();
		names = new HashSet<String>();
	}
	
	public void setMain(TickingLoop PROGRAM_MAIN) {
		addComponent("MAIN", PROGRAM_MAIN);
		threads.get("MAIN").setPriority(Thread.MAX_PRIORITY);
	}
	
	public void addComponent(String name, Runnable runnableComponent) {
		names.add(name);
		Thread newThread = new Thread(runnableComponent);
		newThread.setName(name);
		threads.insert(name, newThread);
	}
	
	public String[] getComponentNames() {
		return names.toArray(new String[]{});
	}
	
	public void start() {
		for (String name : names) {
			startComponent(name);
		}
	}
	
	public void quit() {
		System.exit(0);
	}
	
	public void startComponent(String name) {
		startThread(threads.get(name));
	}
	
	public void stopComponent(String name) {
		stopThread(threads.get(name));
	}
	
	private void startThread(Thread thread) {
		if (!thread.isAlive()) {
			thread.start();
		} else {
			synchronized (thread) { thread.notify(); }
		}
	}
	
	private void stopThread(Thread thread) {
		thread.interrupt();
		try {
			synchronized (thread) { thread.wait(); }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}