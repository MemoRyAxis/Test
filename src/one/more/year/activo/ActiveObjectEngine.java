package one.more.year.activo;

import java.util.LinkedList;

public class ActiveObjectEngine {

	private LinkedList<Command> commands = new LinkedList<>();

	public void addCommand(Command command) {
		commands.add(command);
	}

	public void run() throws Exception {
		while (!commands.isEmpty()) {
			Command command = commands.getFirst();
			// remove command
			// actually command is will be added in method execute
			commands.removeFirst();
			command.execute();
		}
	}
}
