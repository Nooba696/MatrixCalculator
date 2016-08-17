
import java.sql.SQLException;


public class EquationHandler extends linkedStack
{
    MatrixBasics obj=new MatrixBasics();
    String st="";
    int k=0;
    public String infixtoPostfix(String infixString,String op)
    {
        String postfixString="";
        for(int i=0;i<infixString.length();i++)
        {
            if(infixString.charAt(i)=='[')
            {
                postfixString=postfixString+infixString.substring(i,infixString.indexOf("]",i)+1);
                i=infixString.indexOf("]",i);
            }
            else
            {
                String opr="";
                while(op.indexOf("|"+opr+"|")==-1)
                {
                    opr=opr+infixString.charAt(i++);
                }
                i--;
                if(super.isEmpty()==true || (super.peek()).equals("(") || opr.equals("("))
                {
                    super.push(opr);
                }
                else 
                {
                    if(opr.equals(")"))
                    {
                        while(((super.peek()).equals("("))==false)
                        {
                           postfixString=postfixString+super.pop();
                        }
                        super.pop();  
                    } 
                    else if(precedence(opr,super.peek(),op)==true)
                    {
                        super.push(opr);
                    }
                    else
                    {
                        while((super.peek()).equals("(")==false && (precedence(opr,super.peek(),op)!=true))
                        {
                            postfixString=postfixString+super.pop();
                            if(super.isEmpty()==true)
                            {
                                break;
                            }
                        }
                        super.push(opr);
                    }
                }
            }
        }       
        while(super.isEmpty()==false)
        {
            postfixString=postfixString+super.pop();
        }
        return postfixString;
    }
    public void infixtoPostfixInStack(String infixString,String op)
    {
        linkedStack postfixStack=new linkedStack();
        for(int i=0;i<infixString.length();i++)
        {
            if(infixString.charAt(i)=='[')
            {
                postfixStack.push(infixString.substring(i,infixString.indexOf("]",i)+1));
                i=infixString.indexOf("]",i);
            }
            else
            {
                String opr="";
                while(op.indexOf("|"+opr+"|")==-1)
                {
                    opr=opr+infixString.charAt(i++);
                }
                i--;
                if(super.isEmpty()==true || (super.peek()).equals("(") || opr.equals("("))
                {
                    super.push(opr);
                }
                else 
                {
                    if(opr.equals(")"))
                    {
                        while(((super.peek()).equals("("))==false)
                        {
                           postfixStack.push(super.pop());
                        }
                        super.pop();  
                    } 
                    else if(precedence(opr,super.peek(),op)==true)
                    {
                        super.push(opr);
                    }
                    else
                    {
                        while((super.peek()).equals("(")==false && (precedence(opr,super.peek(),op)!=true))
                        {
                            postfixStack.push(super.pop());
                            if(super.isEmpty()==true)
                            {
                                break;
                            }
                        }
                        super.push(opr);
                    }
                }
            }
        }       
        while(super.isEmpty()==false)
        {
            postfixStack.push(super.pop());
        }
        
    }
    public boolean precedence(String o1,String o2, String operators)
    {
        if(operators.indexOf("|"+o1+"|")<operators.indexOf("|"+o2+"|"))
        {
            return true;
        }
        else
            return false; 
    }
    
    public String MatrixPostfixEqEval(String s,String op) throws SQLException, InterruptedException  
    {  
        MatrixAlgebra mA=new MatrixAlgebra();
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='[')
            {
                super.push(s.substring(i+1,s.indexOf("]",i)));
                i=s.indexOf("]",i);
            }
            else
            {
                String opr="";
                while(op.indexOf("|"+opr+"|")==-1)
                {
                    opr=opr+s.charAt(i++);
                }
                i--;
                switch(opr)
                {
                    case "+":
                    {
                        String x1=super.pop();
                        String x2=super.pop();       
                        super.push(mA.add(x1,x2)+"");
                    }
                }
            }  
        }
        return super.pop();
    }
}
