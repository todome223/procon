package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameReader {
	public static String readLine()
	{
		
		BufferedReader l_readStream = new BufferedReader( new InputStreamReader( System.in ) );
		String l_line = new String("");
		try
		{
			l_line = l_readStream.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return l_line;
	}
}
