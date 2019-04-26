import java.util.Scanner;

public class stackUI {
    public static void main(String args[]){
        Stack storage=new Stack();int choice;
        Scanner input=new Scanner(System.in);
        while(true){
            System.out.print("Please Choose an action\n" +
                    "1: Push\n" +
                    "2: Pop\n" +
                    "3: Peek\n" +
                    "4: Get size\n" +
                    "5: Check if empty\n"
            );
            choice=input.nextInt();
            if(choice==1){
                System.out.println("Enter the number you want to push");
                choice=input.nextInt();
                storage.push(choice);
            }
            if(choice==2){if(storage.isEmpty()){System.out.println("stack is empty");}
                else {
                    System.out.println(storage.pop());
                }

            }
            if(choice==3){if(storage.isEmpty()){System.out.println("stack is empty");}
                else {
                     System.out.println(storage.peek());
                }

            }
            if(choice==4){
                System.out.println(storage.size());

            }
            if(choice==5){
                System.out.println(storage.isEmpty());

            }

        }
    }
}
