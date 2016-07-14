package controller;

public class GameWriter {
	private static int count = 0;
	
	public static void writeLine( String i_line )
	{
		System.out.println(i_line);
		++count;
	}
	
	public static void outputCount()
	{
		System.out.println(count + " count");
	}
}
