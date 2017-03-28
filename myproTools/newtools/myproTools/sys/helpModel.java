package myproTools.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mainbody.MethodModel;

public class helpModel implements MethodModel {
	private sysService sys ;
	Map<String, String> commandMap ;
	public helpModel() {
		sys=new sysService();
	}
	@Override
	public String getName() {
		return "帮助";
	}

	@Override
	public String getComm() {
		return "HELP";
	}

	@Override
	public String getHelp() {
		return "帮助文档";
	}

	

	@Override
	public void output() {
		
	}

	@Override
	public boolean useSelfOutput() {
		return true;
	}
	@Override
	public <T> T run(List<T> obj) {
		
		return (T)sys.help();
	}

}
