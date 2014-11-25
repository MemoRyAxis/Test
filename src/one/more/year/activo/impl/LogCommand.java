package one.more.year.activo.impl;

import one.more.year.activo.ActiveObjectEngine;
import one.more.year.activo.Command;

public class LogCommand implements Command {
	
	// log thread id
	private String id;

	// aoe.
	private ActiveObjectEngine aoe;

	// sign whether logged
	private boolean isLogged;

	// time-lag between two times log
	private long delay;
	
	// record log time
	private long logTimestamp;
	
	// count times of execute
	private long counter;

	public LogCommand(String id, long delay, ActiveObjectEngine aoe) {
		this.id = id;
		this.aoe = aoe;
		this.isLogged = false;
		this.delay = delay;
		counter = 0L;
	}

	@Override
	public void execute() throws Exception {
		counter ++;
		if (!isLogged) {
			// record time and log info
			logTimestamp = System.currentTimeMillis();
			log();
			isLogged = true;
			// add command to linked list again
			aoe.addCommand(this);
		} else if(System.currentTimeMillis() - logTimestamp < delay) {
			// confirm whether to delay  
			aoe.addCommand(this);
		} else {
			// delay over
			isLogged = false;
			aoe.addCommand(this);
		}
	}

	private void log() {
		System.out.println("--->\t id: " + this.id + ", time: " + System.currentTimeMillis() / 1000);
		System.out.println("--->\t counter: " + counter + ", obj: " + this.toString());
	}

	public static void main(String[] args) throws Exception {
		ActiveObjectEngine aoe = new ActiveObjectEngine();
		aoe.addCommand(new LogCommand("01", 1000, aoe));
		aoe.addCommand(new LogCommand("02", 2000, aoe));
		aoe.addCommand(new LogCommand("03", 3000, aoe));
		aoe.run();
	}
}
