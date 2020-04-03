package eg.edu.alexu.csd.datastructure.stack.cs31;
public class ExpressionEvaluator {
	public String infixToPostfix(String expression)
	{
		StringBuffer c = new StringBuffer(expression); 
		for (int i=0;i<c.length();i++) 
		{
			//check negative number
			if (c.charAt(i)=='-') 
			{
				int j=i-1;
				while (j>=0&&c.charAt(j)==' ') 
					{
					j--;
					}
				if ((j>=0&&!Character.isDigit(c.charAt(j))&&!checkAlpha(c.charAt(j))&&c.charAt(j)!=')')||i==0) 
				{
					c.insert(i,"(0");
					i+=3;
					while (c.charAt(i)==' ')  
					{
						i++;
					}
					while (i<c.length()&&(Character.isDigit(c.charAt(i))||checkAlpha(c.charAt(i))))i++;
					c.insert(i,')');
					i++;
				}
			}
			if (i<c.length()&&(c.charAt(i)==')'||checkAlpha(c.charAt(i)))) 
			{
				int j=i+1;
				while (j<c.length()&&c.charAt(j)==' ')j++; //jump
				if (j<c.length()&&(c.charAt(j)=='('||checkAlpha(c.charAt(j)))) 
				{
					c.insert(j,'*');
				}
			}
		}
		String S = "";
		Stack stack = new Stack(); 
		Stack parenthesis = new Stack(); 
		for (int i=0;i<S.length();i++) 
		{
			if (Character.isDigit(S.charAt(i))||checkAlpha(S.charAt(i))|| S.charAt(i)==' ' || S.charAt(i)=='.')
			{
				S+=S.charAt(i);
			}
			else if (S.charAt(i)=='(')
			{
				parenthesis.push('(');
				int temp=++i;
				while (i<S.length()&&!parenthesis.isEmpty()) 
				{
					if (S.charAt(i)=='(') 
						parenthesis.push('(');
					else if (S.charAt(i)==')') 
						parenthesis.pop();
					i++;
				} 
				if (parenthesis.isEmpty())
					S += infixToPostfix(S.substring(temp, --i));
				else
					throw new RuntimeException ("Close the parenthesis");
			}
			else if (CheckOperation(S.charAt(i)))
			{
				S += ' ';
				while (!stack.isEmpty()&&!checkfirst(S.charAt(i),(char)stack.peek())) 
				{
					S += stack.pop();
					S += ' ';
				}
				stack.push(S.charAt(i)); 
			}
			else
				throw new RuntimeException("Invalid parameter");
		}
		while (!stack.isEmpty()) 
		{
			S+=" " +stack.pop();
		}
		return S;
	}
	public int evaluate(String expression)
	{
		Stack numbs = new Stack();
		float digit;
		for(int i=0;i<expression.length();i++)
		{
			if(Character.isDigit(expression.charAt(i)))
			{
				digit = expression.charAt(i)-'0'; 
				while(Character.isDigit(expression.charAt(i))&&Character.isDigit(expression.charAt(i+1)))
				{
					digit*=10;
					i++;
					digit=digit+expression.charAt(i)-'0';
				}
				if(expression.charAt(i+1)=='.'|| expression.charAt(i+1)==',')
				{
					i=i+2;
					int temp = 10;
					while(Character.isDigit(expression.charAt(i))) 
					{
						digit+=(float)(expression.charAt(i)-'0')/temp; 
						i++;
						temp*=10;
					}
				}
				numbs.push(digit);
			}
			else
			{
				if(CheckOperation(expression.charAt(i)))
				{
					float num2 = (float)numbs.pop();
					float num1 = (float)numbs.pop();
					numbs.push(result(num1,num2,expression.charAt(i)));
				}
				else if(expression.charAt(i)!=' ') 
				{
					throw new RuntimeException("invalid parameters");
				}
				if(numbs.size()!=1) 
				{
					throw new RuntimeException("invalid");
				}
			}
		}
		return (int)((float)numbs.pop());
	}
	private boolean checkfirst(char first , char last)
	{
		return ((first=='*'||first=='/')&&(last=='+'||last =='-'));
	}
	private boolean CheckOperation(char c)
	{
		return (c=='+'||c=='-'||c=='*'||c=='/');
	}
	private float result(float num1,float num2,char s) 
	{
		switch(s)
		{
		case '+':
			return num1+num2;
		case '-':
			return num1-num2;
		case '*':
			return num1*num2;
		case '/':
			if(num2==0)
			{
				throw new RuntimeException("not allowed");
			}
			return num1/num2;
		default:
			return 0;
		}
	}
	public boolean checkAlpha(char s)
	{
		return (s >= 'A' && s <= 'z');
	}
}

