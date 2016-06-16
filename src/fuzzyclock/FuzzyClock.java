package fuzzyclock;

import java.io.Console;
import java.io.IOError;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author juha
 *
 */
public class FuzzyClock {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FuzzyClock clock = new FuzzyClock();
		clock.clock();
	}
	
	private void clock(){
		String inputString = "";
		Pattern pattern = Pattern.compile("[a-zA-Z]");
		
		do {
			printHelp();
			
			inputString = readLine();
			if ("".equalsIgnoreCase(inputString)){
				return;
			}
			
			Matcher matcher = pattern.matcher(inputString);
			if (matcher.find()){
				System.out.println("Only numbers are allowed");
				continue;
			}
			
			int input = Integer.parseInt(inputString);
			if (input > 7){
				System.out.println("only integer less than 8 is allowed");
				return;
			}
			
			GregorianCalendar now = new GregorianCalendar();
			
			System.out.println(formatTime(input,now));
			
		} while(!"".equalsIgnoreCase(inputString));
	}
	
	/**
	 * Format reply according to accuracy level
	 * @param input
	 * @return
	 */
	private String formatTime(int input, GregorianCalendar now){
		String returnValue = "";
		SimpleDateFormat dateFormat = null;
		int hour = 0;
		switch (input) {
		case 1: //01.01.01.001
			dateFormat = new SimpleDateFormat("HH.mm.ss.SSS");
			returnValue = dateFormat.format(now.getTime());
			break;
		case 2: //01.01
			dateFormat = new SimpleDateFormat("HH.mm");
			returnValue = dateFormat.format(now.getTime());
			break;
		case 3: //five past one
			hour = now.get(Calendar.HOUR_OF_DAY);
			int minutes = getNextFiveMinute(now.get(Calendar.MINUTE));
			if (minutes > 30){
				hour++;
			}
			
			if (minutes == 0 || minutes == 60){
				returnValue = getHourWord(hour) + " o'clock"; 
			}
			else {
				returnValue = getMinuteWord(minutes) + " " + getHourWord(hour);
			}
			break;
		case 4: //five o'clock
			hour = now.get(Calendar.HOUR_OF_DAY);
			if (now.get(Calendar.MINUTE) > 30){
				hour++;
			}
			returnValue = getHourWord(hour) + " o'clock";
			break;
		case 5: //noon
			hour = now.get(Calendar.HOUR_OF_DAY);
			
			if (hour >= 6 && hour < 10){ 
				returnValue = "Morning";
			}
			else if (hour >= 10 && hour < 14){
				returnValue = "Noon";
			}
			else if (hour >= 14 && hour < 18){
				returnValue = "Afternoon";
			}
			else if (hour >= 18 && hour < 22){
				returnValue = "Evening";
			}
			else if (hour >= 22 && hour < 2){
				returnValue = "Midnight";
			}
			else if (hour >= 2 && hour < 6){
				returnValue = "Night";
			}
			break;
		case 6: //day
			if (now.get(Calendar.HOUR) < 9 && now.get(Calendar.HOUR) > 18){
				returnValue = "Night";
			}
			else {
				returnValue = "Day";
			}
			break;
		case 7: //Monday
			returnValue = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(now.getTime());
			break;
		}
		
		return returnValue;
	}

	/**
	 * Interpret minute number to word. Value should be divisible with 5.
	 * @param minute
	 * @return
	 */
	private String getMinuteWord (int minute){
		String returnValue = "";
		
		switch (minute) {
		case 0:
		case 60:
			returnValue = "";
			break;
		case 5:
			returnValue = "five past";
			break;
		case 10:
			returnValue = "ten past";
			break;
		case 15:
			returnValue = "fifteen past";
			break;
		case 20:
			returnValue = "twenty past";
			break;
		case 25:
			returnValue = "twenty-five past";
			break;
		case 30:
			returnValue = "half past";
			break;
		case 35:
			returnValue = "twenty-fife to";
			break;
		case 40:
			returnValue = "twenty to";
			break;
		case 45:
			returnValue = "fifteen to";
			break;
		case 50:
			returnValue = "ten to";
			break;
		case 55:
			returnValue = "five to";
			break;
		default:
			returnValue = "error";
			break;
		}
		
		return returnValue;
	}
	
	/**
	 * Return next minute value which is divisible with 5. For example 42 -> 45.
	 * @param minute
	 * @return
	 */
	private int getNextFiveMinute(int minute){
		int returnMinute = 0;
		int modOfFive = minute % 5;
		int untilNextFiveMinute = 0;
		
		if (modOfFive != 0){
			untilNextFiveMinute = 5 - modOfFive;
		}
		
		return returnMinute + untilNextFiveMinute;
	}
	
	/**
	 * Interpret hour number to word.
	 * @param hour
	 * @return
	 */
	private String getHourWord(int hour){
		String returnValue = "";
		switch (hour){
		case 0:
			returnValue = "";
			break;
		case 1:
			returnValue = "one";
			break;
		case 2:
			returnValue = "two";
			break;
		case 3:
			returnValue = "three";
			break;
		case 4:
			returnValue = "four";
			break;
		case 5:
			returnValue = "five";
			break;
		case 6:
			returnValue = "six";
			break;
		case 7:
			returnValue = "seven";
			break;
		case 8:
			returnValue = "eight";
			break;
		case 9:
			returnValue = "nine";
			break;
		case 10:
			returnValue = "ten";
			break;
		case 11:
			returnValue = "eleven";
			break;
		case 12:
			returnValue = "twelve";
			break;
		case 13:
			returnValue = "thirteen";
			break;
		case 14:
			returnValue = "fourteen";
			break;
		case 15:
			returnValue = "fifteen";
			break;
		case 16:
			returnValue = "sixteen";
			break;
		case 17:
			returnValue = "seventeen";
			break;
		case 18:
			returnValue = "eighteen";
			break;
		case 19:
			returnValue = "nineteen";
			break;
		case 20:
			returnValue = "twenty";
			break;
		case 21:
			returnValue = "twenty-one";
			break;
		case 22:
			returnValue = "twenty-two";
			break;
		case 23:
			returnValue = "twenty-three";
			break;
		}
		return returnValue;
	}
	
	/**
	 * Print instructions for user.
	 */
	private void printHelp(){
		StringBuffer helpBuffer = new StringBuffer("Choose accuracy level:" + "\n");
		helpBuffer.append("1 - accurate" + "\n");
		helpBuffer.append("2" + "\n");
		helpBuffer.append("3" + "\n");
		helpBuffer.append("4" + "\n");
		helpBuffer.append("5" + "\n");
		helpBuffer.append("6" + "\n");
		helpBuffer.append("7 - fuzzy" + "\n");
		System.out.println(helpBuffer.toString());
	}

	/**
	 * Returns input from console.
	 * @return
	 */
	private String readLine(){
		Console console=System.console();
		
		try{
		    return console.readLine();
		}
		catch (IOError ioe){
			return "";
		}
	}
}
