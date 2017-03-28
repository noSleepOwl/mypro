package com.cn.app.command;

public class BaseCommand  implements Command{
	private String command;
	private String[] argvs;
	public BaseCommand(String command) {
		this.command=command;
	}
	{
		parseCommand();
	}
	@Override
	public void parseCommand() {
		if(command != null){
			String[] commandParsed = command.split("\\s");
			argvs = new String[commandParsed.length-1];
			for(int i = 0 ; i < commandParsed.length;i++)
			{
				if(i==0) this.command = commandParsed[i];
				else argvs[i-1]=commandParsed[i];
			}
			
		}
		
	}
	@Override
	public String getCommand() {
		return this.command;
	}
	@Override
	public String[] getArgv() {
		return this.argvs;
	}

}
