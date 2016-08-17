
import java.util.NoSuchElementException;

class linkedStack
{
    protected LinkList top ;
    protected int size ;
 
    /*  Constructor  */
    public linkedStack()
    {
        top = null;
        size = 0;
    }    
    /*  Function to check if stack is empty */
    public boolean isEmpty()
    {
        return top == null;
    }    
    /*  Function to get the size of the stack */
    public int getSize()
    {
        return size;
    }    
    /*  Function to push an element to the stack */
    public void push(String data)
    {
        LinkList nptr = new LinkList (data, null);
        if (top == null)
            top = nptr;
        else
        {
            nptr.setLink(top);
            top = nptr;
        }
        size++ ;
    }    
    /*  Function to pop an element from the stack */
    public String pop()
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception") ;
        LinkList ptr = top;
        top = ptr.getLink();
        size-- ;
        return ptr.getData();
    }    
    /*  Function to check the top element of the stack */
    public String peek()
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception") ;
        return top.getData();
    }    
    /*  Function to display the status of the stack */
    public void display()
    {
        System.out.print("\nlinkedStack = ");
        if (size == 0) 
        {
            System.out.print("Empty\n");
            return ;
        }
        LinkList ptr = top;
        while (ptr != null)
        {
            System.out.print(ptr.getData()+" ");
            ptr = ptr.getLink();
        }
        System.out.println();        
    }
}