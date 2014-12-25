package packComicator;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.itextpdf.text.BadElementException;

public class ComicatorFull {

	public static void main(String[] args)
	{
		if (args.length == 0 || args.length > 3)
		{
			help();
		}
		else
		{
			boolean forceNSplit = false, dirC = false;
			if(args.length > 1)
			{
				if (args[1].equals("-NSplit"))
					forceNSplit = true;
				if (args[1].equals("-dir"))
					dirC = true;
			}
			if (args.length == 3)
			{
				if (args[2].equals("-NSplit"))
					forceNSplit = true;
				if (args[2].equals("-dir"))
					dirC = true;
			}

			File dir = new File(args[0]);
			String[] k = {};
			LinkedList<String> ruta = new LinkedList<String>();
			if (dirC)
			{
				File[] ficheros = dir.listFiles();
				for (int i = 0; i<ficheros.length; i++)
				{
					//If one of the files is directory then
					//loads that directory recursively
					if (ficheros[i].isDirectory())
					{
						if (forceNSplit)
						{
							ruta.addLast(ficheros[i].getAbsolutePath());
							ruta.addLast("-NSplit");
						}
						else
						{
							ruta.addLast(ficheros[i].getAbsolutePath());
						}	
						try 
						{	
							Comicator.main(ruta.toArray(k));
						} 
						catch (BadElementException | IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}
			else
			{
				if (forceNSplit)
				{
					ruta.addLast(dir.getAbsolutePath());
					ruta.addLast("-NSplit");
				}
				else
				{
					ruta.addLast(dir.getAbsolutePath());
				}
				try 
				{	
					Comicator.main(ruta.toArray(k));
				} 
				catch (BadElementException | IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}
	private static void help() {
		System.out.println("This is ComicatorFull, Version 1.0, by Alberto Fernandez");
		System.out.println("Usage:");
		System.out.println("java -jar Comicator.jar <path to the directory or help> [Options]\n");
		System.out.println("Options:\n \t -Dir: converts each directory in the paht into a pdf file"
				+ "\n \t -NSplit: turns off the auto splitting process");
		System.out.println("Be careful with the name of the images");
	}
}
