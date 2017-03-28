package mytools.features.systemmethod;

import java.util.Map;

import mytools.command.InputModel;
import mytools.command.ControlCommand;

public class SystemMethod {
	public void systemHelp() {
		ControlCommand ccmd = new ControlCommand();
		for (Map.Entry<String, InputModel> ent : ccmd.getCss().entrySet()) {
			System.out.println(ent.getKey() + ":\t\t\t" + ent.getValue().getWord());
		}
	}
}
