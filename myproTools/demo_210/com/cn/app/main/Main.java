package com.cn.app.main;

import java.util.Scanner;


public class Main {
	boolean isRuning = true;
	private static final String COM_EXIT="EXIT";
	private String [] argvs = null;
	private String command = null;
	public static void main(String[] args) {
		System.out.println("start");
		Main m = new Main();
		m.Run();
	}
	public void Run(){
		Scanner scann = new Scanner(System.in);
	LABLE:
		while(isRuning){
			String command = scann.nextLine();
			parseCommand(command);
			if(this.command.equalsIgnoreCase(COM_EXIT)){
				if(argvs!=null&&argvs.length>0&&argvs[0].equals("-")){
					break;
				}else{
					
				}
				break LABLE;
			}else {
				break LABLE;
			}
		}
	}
	
	private void parseCommand(String command){
		String[] comm = command.split("\\s");
		
		if(comm.length>0)
		{
			argvs = new String[comm.length-1];
			for(int i = 0 ;i < comm.length;i++)
			{
				if(i==0)
				{
					command=comm[i];
				}else{
					argvs[i-1] = comm[i];
				}
			}
		}
	}
}
