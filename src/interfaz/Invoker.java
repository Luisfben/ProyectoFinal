package interfaz;

import java.util.HashMap;
import java.util.Map;

public class Invoker {
	private Map<String, Command> commandList = new HashMap<>();

	public void add(String key, Command command) {
		this.commandList.put(key, command);
	}

	public void execute(String key) {
		this.commandList.get(key).execute();
	}
}
