import java.io.*;
import java.util.*;
import java.lang.*;
import java.time.*;
import java.time.temporal.*;

interface Admin
{
	void countBooks();
	void newScreen();
}
class Books extends Object implements Admin,Serializable 
{
	ArrayList<String> list = new ArrayList<>();
	
	String sub1,sub2,sub3,sub4;
	String sub[][] = new String[4][];
	int size = 10;
	int count=0;
	LocalDate date1 = LocalDate.now();
	LocalDate date2 = date1.plusDays(7);
	int countSub1,countSub2,countSub3,countSub4;
	int countS1,countS2,countS3,countS4;
	long x=0;
	long fineAmount,paidAmount;
	Books()
	{
		sub[0] = new String[5];
	    sub[1] = new String[3];
  		sub[2] = new String[4];
  		sub[3] = new String[1];
  		for(int i =0;i<4;i++)
    	{
      		for(int j=0;j<sub[i].length;j++)
      		{
        		if(i==0)
					sub[i][j] = "MATHS";
        		else if(i==1)
					sub[i][j] = "PHYSICS";
       			else if(i==2)
					sub[i][j] = "BIOLOGY";
				else if(i==3)
					sub[i][j] = "SOCIAL";
      		}
      	}
    }
	public void countBooks()
	{
		//Count No. of Books Available for eacj subject.
		for(int i=0;i<sub.length;i++)
		{
			for(int j=0;j<sub[i].length;j++)
			{
				if(i==0&&j==0)
				{
					sub1 = sub[i][j];
					countSub1 = sub[i].length;
				}
				else if(i==1&&j==0)
				{
					sub2 = sub[i][j];
					countSub2 = sub[i].length;
				}
				else if(i==2&&j==0)
				{
					sub3 = sub[i][j];
					countSub3 = sub[i].length;
				}
				else if(i==3&&j==0)
				{
					sub4 = sub[i][j];
					countSub4 = sub[i].length;
				}
			}
		}
	}

	  
	
	
	
	String s1[] = new String[30];
	String issued[] = new String[30];	
	int countBooks;
	public void issueBook() throws IOException,ClassNotFoundException
	{
		Books robj = getObject();
	try
	{
		System.out.println("________________________________________________________________________________");
		System.out.println("________________________________ISSUE BOOKS_____________________________________");
		

		Scanner in = new Scanner(System.in);
		
		boolean quit = true;
		
		do
		{
			System.out.println("\n");
			System.out.print("How many books do u want to issue (Max. 3):");
			countBooks = in.nextInt();
			System.out.println("");
			
		
			if(countBooks==0)
			{
				throw new MyException("You selected 0 books to be issued!!     ):\nPlease select valid number of books.");
			}
			if(robj.list.size()>3 || (robj.list.size()+countBooks)>3 || countBooks>3 || robj.countBooks+countBooks>3)
			{
				
				if(robj.list.size()==0)
					System.out.println("You can't issue more than 3 books.Even though u didn't issue any book till now.");
				else
				{
					System.out.println("You can't issue more than 3 books. You already issued:");
					for(int i=0;i<robj.list.size();i++)
						System.out.print(robj.list.get(i)+"  ");
				
					System.out.print(" book(s).\n");
				}
			}
			else
			{
				robj.countBooks = robj.countBooks+countBooks;
				quit = false;
			}

		}while(quit);
		
		for(int i=0;i<countBooks;i++)
		{
			System.out.print("Enter the "+(i+1)+"th sub name u want to issue:");
			robj.s1[i] = in.next().toUpperCase();
			System.out.println("\n");
			robj.issued[i] = s1[i];

			if(robj.s1[i].equals(sub1) && robj.countSub1!=0)
			{

				robj.countSub1--;
				robj.issued[i] = robj.s1[i];
			}

			else if(robj.s1[i].equals(sub2) && robj.countSub2!=0)	
			{
				robj.countSub2--;
				robj.issued[i] = robj.s1[i];
			}
			else if(robj.s1[i].equals(sub3) && robj.countSub3!=0)
			{
				robj.countSub3--;
				robj.issued[i] = robj.s1[i];
			}
			else if(robj.s1[i].equals(sub4) && robj.countSub4!=0)
			{
				robj.countSub4--;
				robj.issued[i] = robj.s1[i];
			}
			else
			{
				throw new MyException(robj.s1[i]+"is not available.Please check the available books and enter correct book name.\nSo, you are not issued any books right now.");
			}
			robj.list.add(robj.s1[i]);
		}
	
		//Setting Issuing and Returning Dates
		robj.date1 = LocalDate.now();
		robj.date2 = robj.date1.plusDays(7);

		//Printing the Issued Books List
		if(robj.countBooks==0 || countBooks ==0)
			System.out.print("");
		else
		{
			System.out.println("You have been issued:");
			for(int i=0;i<countBooks;i++)
			{
				System.out.print(robj.issued[i]+"\t");
			}
			System.out.print("on date: "+date1);
			System.out.println("\nThese Books should be returned before "+date2);
		}

		//Storing the changes made to books after issuing the book(s).
		robj.storeBooks(robj);

	}
	catch(Exception  e)
	{
		System.out.println(e);
	}

	finally
	{
		System.out.println("________________________________________________________________________________");
		System.out.println("__________________________________BOOKS ISSUED__________________________________");
	}
		
	}

	String fileName = "";
	String filePath = "";
	public void storeBooks(Books obj) throws IOException 	// As we are working with IOStreams, it is advised to supress the IOException bcz it doesnot effect our program.
	{
		File f = new File("Books.txt");
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		fileName = f.getName();
		filePath = f.getAbsolutePath();
		oos.writeObject(obj);
	}
	public Books getBooksDetails() throws IOException,ClassNotFoundException   		// Eventhough we supress this 2 exceptions, program runs in a desired way.
	{
		FileInputStream fis = new FileInputStream("Books.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Books o1 = (Books)ois.readObject();
		System.out.println("********************************************************************");
	
		System.out.println("The Following books are available in our Library :)\n".toUpperCase());	
	

		System.out.println(o1);
		System.out.println("********************************************************************");
	
		return o1;
	}
	public Books getObject() throws IOException,ClassNotFoundException 			// Eventhough we supress this 2 exceptions, program runs in a desired way.

	{
		
		FileInputStream fis = new FileInputStream("Books.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Books o1 = (Books)ois.readObject();		
		return o1;
	}

	public void returnBook() throws IOException 		// As we are working with IOStreams, it is advised to supress the IOException bcz it doesnot effect our program.
	{
		System.out.println("________________________________________________________________________________");
		System.out.println("_________________________________RETURN BOOKS___________________________________");
		System.out.println("\n");
	try
	{
		Books obj = getObject();
		boolean quit = true;
		Scanner in = new Scanner(System.in);
		
		if(obj.list.size()==0)
		{
			throw new MyException("You have not issued any books till now");
		}

		else
		{
			System.out.println("Books issued to you are");
			System.out.println("----------------------------");
			for(int i=0;i<obj.list.size();i++)
			{
				System.out.println(obj.list.get(i)+" on date: "+obj.date1);
			}
			System.out.println("----------------------------");

		}
		do
		{
		System.out.print("How many books do you want to return:");
		int cb = in.nextInt();
		if(cb>obj.list.size())
		{
			quit = true;
			throw new MyException("You have issued only " +obj.list.size()+" books. So you can't return "+cb+" books.");
			
		}
		else
			quit = false;
		for(int k=0;k<cb;k++)
		{
			
			System.out.print("\nEnter the book u want to return:");
			String s1 = in.next().toUpperCase();
			int cs1=0;
			//System.out.println("You entered "+s1);
			for(int i=0;i<obj.list.size();i++)
			{
				String s2 = obj.list.get(i);
				if(s2.equals(s1))
				{	
					if(sub1.equals(s2))
					{
						obj.countSub1++;
						obj.countBooks--;
						System.out.print(s2+" has been returned successfully :)");
						cs1++;
					}
					else if(sub2.equals(s2))
					{
						obj.countSub2++;
						obj.countBooks--;
						System.out.print(s2+" has been returned successfully :)");
						cs1++;
					}
					else if(sub3.equals(s2))
					{
						obj.countSub3++;
						obj.countBooks--;						
						System.out.print(s2+" has been returned successfully :)");
						cs1++;
					}
					else if(sub4.equals(s2))
					{
						obj.countSub4++;
						obj.countBooks--;
						System.out.print(s2+" has been returned successfully :)");
						cs1++;
					}
					obj.list.remove(i);
				}
			}
			obj.storeBooks(obj);

			if(cs1==0)
				throw new MyException(s1+" is not issued by you :(");

		}

		System.out.println("Please check your fine!! ");
		}while(quit);
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	finally// As we are working with IOStreams, it is advised to supress the IOException bcz it doesnot effect our program.
	{
		System.out.println("________________________________________________________________________________");
		System.out.println("_________________________________BOOKS RETURNED_________________________________");	
	}
 
	}

	
	public void payFine() throws IOException,ClassNotFoundException 	// Eventhough we supress this 2 exceptions, program runs in a desired way.
	{
		Books obj = getObject();
		Scanner in = new Scanner(System.in);

		LocalDate date3 = LocalDate.now();
		LocalDate date4 = obj.date2;

		//Displaying the issued date and (returning date i.e today)
		System.out.println("Issued Date: "+date4+" Today's Date: "+date3);

		//Counting the diff. b/w return date and issued date after week. bcz fine is not compelled if the book is returned in a week.
		obj.x = ChronoUnit.DAYS.between(date4,date3) - 7;
			
		if(date3.compareTo(date4)>0)
		{
			//Calculating fineAmount by Mutltplying Rs. 5/Day with diff. b/w one week after issued date and return date and No. of books.
			obj.fineAmount = (obj.fineAmount + (5*obj.x*obj.countBooks)) - obj.paidAmount;
	
			System.out.println("Do you want to pay the fine[Yes/No]?");
			String s1 = in.next().toUpperCase();

			if(s1.equals("YES"))
			{
				System.out.println("You have to pay Rs."+obj.fineAmount);
				System.out.println("Enter the amount you want to pay:");
				int amount = in.nextInt();
				obj.paidAmount = obj.paidAmount + amount;
				obj.fineAmount = obj.fineAmount - amount;
			}

			else
			{
				System.out.println("It is advised to clear your fine as soon as possible.");
			}
		}
		else
			System.out.println("There is no fine copelled on you.");
		obj.storeBooks(obj);
	}



	public void newScreen()
	{
		for(int i=0;i<55;i++)
			System.out.println("");
	}


	@Override
	public String toString()
	{

		return ("Subject: "+sub1+"\t\tCopies: "+this.countSub1+"\nSubject: "+sub2+"\tCopies: "+this.countSub2+"\nSubject: "+sub3+"\tCopies: "+this.countSub3+"\nSubject: "+sub4+"\t\tCopies: "+this.countSub4);
	}
	
}



//User Defined Exception Class
class MyException extends Exception
{
	public MyException(String s)
	{
		System.out.println("My Exception: "+s);
		System.exit(-1); //Exit(-1) indicates termination because of exception.
	}	
}
class Demo
{
	public static void main(String args[]) throws IOException  // As we are working with IOStreams, it is advised to supress the IOException bcz it doesnot effect our program.
	{
		Books obj = new Books(); //Creating instance of Books Class.
		int i=1;
		obj.newScreen();
		System.out.println("\t\t\t\tLIBRARY MANAGEMENT SYSTEM");
		try
		{
		obj.countBooks();
		boolean quit = true;
		do
		{
		System.out.println("\n\n");

		//Displaying the options to user.
		System.out.println("\t\t\t\t1)Display Avaialble Books\n\t\t\t\t2)To Issue Books\n\t\t\t\t3)To Return Books\n\t\t\t\t4)To Pay Fine\n\t\t\t\t5)To Quit\n\n");
		if(i==1)
		{
			System.out.println("If you are running the program for the first time, it is advised to PRESS '''0''' at first to store the data into your system's file\n");
			i++;	
		}
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your choice:");
		int choice = in.nextInt();
		switch(choice)
		{
			case 0:
				obj.storeBooks(obj);  //It stores the object of class Books in the file.
				obj.newScreen();
				System.out.println("Data Stored into file '"+obj.fileName+"' at location '"+obj.filePath+"'"); //Prints the fileName and filePath where the object of Books class is stored.
				break;
			case 1:
				obj.newScreen(); //control shifts after 50 lines so that its looks like a new window for the user.
				obj.getBooksDetails(); //Displays all the books and their quantity available in the Library.
				break;
			case 2:
				obj.newScreen();
				obj.issueBook(); //Book(s) can be issued if it is available in the library.
				break;
			case 3:
				obj.newScreen();
				obj.returnBook(); //Book(s) can be returned if it is issued by user.
				break;
			case 4:
				obj.newScreen();
				obj.payFine();  //fine will be counted and can be paid.				
				break;
			case 5:
				obj.newScreen();
				System.out.println("\t\t\t\t\t\t\tLogged out Successfully!!\n\n\n\n\n\n\n\n\n\n\n");
				System.exit(0);							//System.exit(0) indicates SuccessFull Termination.
				break;
			default:
				throw new MyException("Please select a valid option ):"); // throw's ans user-defined exception
		}
		}while(quit);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
