package com.cn.app.module;

import com.cn.app.command.Command;

public abstract class BaseMocdule<R> implements Module<Command,R> {
	private String name;
	private Command command;
	@Override
	public String getName() {
		return this.name;
	}
	public BaseMocdule(String name,Command command) {
		this.command=command;
		this.name  = name;
	}
	@Override
	public boolean run() {
		if(name.equals(command.getCommand())){
			this.moduleBody(command);
			return true;	
		}else{
			return false;
		}
	}

	@Override
	public abstract R moduleBody(Command t) ;
	

}
