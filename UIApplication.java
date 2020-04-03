package eg.edu.alexu.csd.datastructure.stack.cs31;
import java.util.Scanner;
public class UIApplication {
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		ExpressionEvaluator evaluator = new ExpressionEvaluator();
		char choice;
		boolean flag = true;
		String infix="";
		String postfix = "";
		while(flag)
		{
			System.out.println("choose an action\n"+"**********************\n"+"1-Enter an Expression \n"+"2-Convert to postfix\n"+"3-Evaluate \n"+"4-Exit\n"+"************************\n");
			choice = scanner.next().charAt(0);
			switch(choice)
			{
			case '1':
				System.out.println("Write the expression : \n");
				scanner.nextLine();
				infix = scanner.nextLine();
				break;
			case '2':
				postfix = evaluator.infixToPostfix(infix);
				System.out.println("Your Postfix Expression is : " + postfix);
				break;
			case '3':
				boolean checkletter = false;
				int result;
				try
				{
					for(int i=0;i<infix.length(); i++)
					{
						if(evaluator.checkAlpha(infix.charAt(i)))
						{
							checkletter= true;
							break;
						}
					}
					if(checkletter)
					{
						System.out.println("Letters NOT ACCEPTED!!");
					}
					else
					{
						result = evaluator.evaluate(postfix);
						System.out.println("Your result is" + result);
					}
					break;
				}
				catch(Exception e )
				{
					System.out.println("Enter an expression" );
					break;
				}
			case '4':
				flag = false;
				break;
			default:
				System.out.println("Invalid choice");
				
				
			}
		}
	}
}
