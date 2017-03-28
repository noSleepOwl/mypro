package demo_211;

import java.util.spi.CurrencyNameProvider;

import mainbody.command;

public class TestInMyString {
	public static void main(String[] args) {
		
	}
	
	/**
	 * @author uigsw
	 *	控制台模块
	 */
	public class MainConsole{
		public <T extends Model> T TestInFo(String command){
			Command comm = new Command(command);
			comm.getCurrentCommand();
			
			return null;
		}
	}
	/**
	 * @author uigsw
	 *功能模块
	 */
	public abstract class Model{
		private Command command;
		public Model(Command command) {
			this.command = command;
		}
		public abstract void run();
	}
	/**
	 * @author uigsw
	 *命令模块
	 */
	public class Command{
		public static final String COMMAND_END = "END";
		private String currentCommand;
		public Command(String currentCommand) { 
			this.currentCommand = currentCommand;
		}
		public String getCurrentCommand() {
			switch (currentCommand) {
			case COMMAND_END:
				return COMMAND_END;
			default:
				break;
			}
			return currentCommand;
		}
	}
}
