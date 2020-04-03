package eg.edu.alexu.csd.datastructure.stack.cs31;
public class Stack implements IStack {
	private class Node
	{
		Object element;
		Node next;
		Node(Object element) //constructor 
		{
			this.element = element;
		}
	}
	private Node top; 
	int size;
	public Stack()//constructor 
	{
		
		this.top=null;
		this.size=0;   
	}
	public Object pop()
	{
		Object temp = null;
		if(isEmpty())
		{
			throw new RuntimeException("the stack is empty");
		}
		else
		{
			temp = this.top.element; 
			this.top = this.top.next; 
			this.size--;
		}
		return temp;
	}
	public Object peek()
	{
		Object temp = this.top.element;	
		return temp;
	}
	public void push(Object element)
	{
	
		Node Node1 = new Node(element); 
		Node1.next = this.top; 
		this.top = Node1;
		this.size++;
	}
	public boolean isEmpty() 
	{
		return this.size==0;
	}
	public int size()
	{
		return this.size;
	}
}

