public class ExpressionEvaluator implements IExpressionEvaluator{
    /**
     * takes two operators compared and head of stack and compares their precedence
     * and returns true or false accordingly
     ** @param comp
     *      the operator you want to compare in the infix notation
     * @param headofstack
     *      the head of the stack compared with
     * @return
     *      true if comp precedes head of stack false otherwise
     */
    private boolean isPrecedant(char comp,char headofstack){
        if(comp=='+'||comp=='-'){
            if(headofstack=='*'){return true;}
            else if(headofstack=='/'){return true;}
            else if(headofstack=='-'){return true;}
            else if(headofstack=='+'){return true;}
            else if (headofstack=='('){return false;}
        }

        if(comp=='*'||comp=='/'){
            if(headofstack=='+'){return false;}
            else if(headofstack=='/'){return true;}
            else if(headofstack=='*'){return true;}
            else if(headofstack=='-'){return false;}
            else if (headofstack=='('){return false;}
        }

        return false;

    }

    /**
     * takes the expression entered by the user and return the correct format for the next function
     * @param expression the expression user has entered
     * @return expression format modified for the next function
     */
    private  String formatHandler(String expression){
        int j,i=0;
        while(i<expression.length()){j=1;
            while(j+i<expression.length()&&expression.charAt(j+i)==' '){j++;}
            if(j+i<expression.length()&&expression.charAt(i+j)!=' '){expression=expression.substring(0,i+1)+expression.substring(i+j);}
            i++;
        }
        for(i=0;i<expression.length();i++){
            if(expression.charAt(i)=='-'){
                if(!(i==0||isoperator(expression.charAt(i-1)))){expression=expression.substring(0,i+1)+' '+expression.substring(i+1);}
            }
        }
        return expression;
    }

    /**
     * Takes a symbolic/numeric infix expression as input and converts it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     *
     * @param expression
     * infix expression
     * @return postfix expression
     */

    public String infixToPostfix(String expression){
        expression=formatHandler(expression);
        Stack s=new Stack();
        String output=new String();
        //expression=expression.replaceAll("[ ]","");
        char[] e=expression.toCharArray();
        for(int i=0;i<e.length;i++){
            if(e[i]=='+'||(e[i]=='-'&&e[i+1]==' ')||e[i]=='*'||e[i]=='/'){
                if(s.isEmpty()){s.push(e[i]);}
                else{
                    while(!s.isEmpty()&&isPrecedant(e[i],(char)s.peek())) {
                        output = output+" " + (char) s.pop();

                    }
                    s.push(e[i]);
                }
            }
            else if(e[i]=='('){s.push(e[i]);}
            else if(e[i]==')'){
                while((char)s.peek()!='('){
                    output=output+" "+(char)s.pop();

                }
                s.pop();
            }
            else if(e[i]==' '){continue;}
            else{
                if(i-1>=0&&(Character.isDigit(e[i-1])||e[i-1]=='-')){output=output+e[i];}
                else{output=output+" "+e[i];}
            }
        }
        while(!s.isEmpty()){
            output=output+" "+(char)s.pop();
        }
        output=output.substring(1);
        return output;
    }

    /**
     * takes a character and returns true if it is an operator and false otherwise
     * @param inp
     *      character checked
     * @return
     * true if it is an operator false otherwise
     */
    private boolean isoperator(char inp){
        if(inp=='+'||inp=='-'||inp=='*'||inp=='/'){return true;}
        else return false;
    }

    /**
     * takes two floats a,b amd a character that is guaranteed to be an operator and returns a operator b
     * @param a
     * first operand
     * @param b
     * second operand
     * @param c
     * operator
     * @return
     * the output of a operator b
     */

    private float evaluator(float a,float b, char c){
        switch (c){
            case '+':return (float)a+b;
            case '-':return (float)a-b;
            case'*':return (float)a*b;
            case'/':return (float)a/b;
        }
        return 0;
    }

    /**
     * Evaluate a postfix numeric expression, with a single space separator
     *
     * @param expression
     * postfix expression
     * @return the expression evaluated value
     */

    public int evaluate(String expression){
        Stack storage=new Stack();String d;float b,a;
       for(int i=0;i<expression.length();i++){
           if(isoperator(expression.charAt(i))&&((i+1<expression.length()&&expression.charAt(i+1)==' ')||i==expression.length()-1)){
               if(storage.size()<2){System.out.println("INVALID EXPRESSION");throw new IllegalArgumentException("Invalid expression");}
               b=(float)storage.pop();a=(float)storage.pop();
               if(expression.charAt(i)=='/'&&b==0){throw new IllegalArgumentException("Cannot Divide By zero");}
               storage.push(evaluator(a,b,expression.charAt(i)));
           }
           else if(expression.charAt(i)==' '){continue;}
           else {d="";
               while((isoperator(expression.charAt(i))&&(i==0||Character.isDigit(expression.charAt(i+1))))||(!isoperator(expression.charAt(i)))&&expression.charAt(i)!=' '){
                   d=d+expression.charAt(i);i++;}
               a=Float.parseFloat(d);
               storage.push(a);
           }

       }
       if(storage.size()>1){throw new IllegalArgumentException("Invalid expression");}
       return Math.round((float)storage.pop());
    }
}
