package com.cn.app.module.modules;

import com.cn.app.command.Command;
import com.cn.app.module.BaseMocdule;

public class Exit extends BaseMocdule<String>{
	public Exit(Command command) {
		super(Command.EXIT, command);
	}
	@Override
	public String moduleBody(Command t) {
		System.out.println(this.getName());
		return "";
	}
}
