package mytools.main;

import java.util.Scanner;

public class StartMain {

	private static Scanner scanner;
	private static String input = "";
	private static String currentCommand = "";

	public static void main(String[] args) {
		new newChoose().commandRun();

		/*
		 * ChooseMethod cm = new ChooseMethod(); cm.Spack(Choose.GN_ALL);
		 * scanner = new Scanner(System.in); while (true) { input =
		 * scanner.nextLine(); input = input.trim(); if (cm.isCommand(input)) {
		 * currentCommand = input; cm.chooseAblit(input); } else {
		 * cm.chooseAblit(currentCommand, input); }
		 * 
		 * }
		 * 
		 */}

	// 输入的判断 , 判断 其实不是命令 ??

}
