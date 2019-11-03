import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DuplicateCounter
{
	private Integer wordCounter;
	private Map<String, Integer> map;
	  
	public DuplicateCounter()
	{
		this.wordCounter = 0;
		this.map = new HashMap<>();
	}
	  
	public void count(String dataFile)
	{
		Scanner fileReader;
		try
		{
			fileReader = new Scanner(new File(dataFile));

			while(fileReader.hasNextLine())
			{
				String line = fileReader.nextLine().trim();
				String[] data = line.split("[\\W]+");
				for(String word : data)
				{
					this.wordCounter = map.get(word);
					this.wordCounter = (this.wordCounter == null) ? 1 : ++this.wordCounter;
					map.put(word, this.wordCounter);
				}
			}
			
			fileReader.close();
		}

		catch(FileNotFoundException fnfe)
		{
			System.out.println("File " + dataFile + " cannot be found!");
			System.exit(1);
		}
	}

	public void write(String outputFile)
	{
			FileWriter fw;
			PrintWriter pw;
		try
		{
			fw = new FileWriter(new File(outputFile));
			pw = new PrintWriter(fw);
			
			for(Map.Entry<String, Integer> entry : map.entrySet())
			{
				pw.write(entry.getKey() + " = " + entry.getValue() + " times\n");
			}
			
			System.out.println("Done");
			pw.flush();
			fw.close();
			pw.close();
		}
		catch (IOException ex) 
		{
			System.out.println("Error writing to " + outputFile + ": " + ex.getMessage());
			System.exit(1);
		}
	}
}


class Application 
{
	private static final String INPUT_FILE = "problem2.txt";
	private static final String OUTPUT_FILE = "unique_word_counts.txt";
  
	public static void main(String[] args)
	{
		DuplicateCounter d = new DuplicateCounter();
		d.count(INPUT_FILE);
		d.write(OUTPUT_FILE);
	}
}