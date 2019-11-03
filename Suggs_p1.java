import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DuplicateRemover 
{
	private Set<String> uniqueWords;
	public void remove(String dataFile) throws FileNotFoundException
	{
		String w;
		uniqueWords = new HashSet<String>();
		Scanner sc =new Scanner(new File(dataFile));
		while(sc.hasNext())
		{
			w = sc.next();
			uniqueWords.add(w);
		}
		sc.close();
	}

	public void Write(String outputFile) throws IOException
	{
		File f;
		FileWriter fw = null;
		f = new File(outputFile);
		
		if(f.exists())
		{
			fw = new FileWriter(f,true);
			Iterator itr = uniqueWords.iterator();

			while(itr.hasNext())
			{
				String str = (String)itr.next();
				fw.write(str+"\n");
			}
			
			fw.close();
			System.out.println("Done");
		}
		
		else
		{
			f.createNewFile();
			fw=new FileWriter(f);
			Iterator itr = uniqueWords.iterator();
			
			while(itr.hasNext())
			{
				String str = (String)itr.next();
				fw.write(str+"\n");
			}
			
			fw.close();
			System.out.println("Done");
		}
	}
}



class Application 
{
	private static final String INPUT_FILE = "problem1.txt";
	private static final String OUTPUT_FILE = "unique_words.txt";
  
	public static void main(String[] args)
	{
		DuplicateRemover d = new DuplicateRemover();
		d.count(INPUT_FILE);
		d.write(OUTPUT_FILE);
	}
}