import java.util.EmptyStackException;

public class IntLinkedStack implements Cloneable
{
   // Invariant of the IntLinkedStack class:
   //   1. The items in the stack are stored in a linked list, with the top of
   //      the stack stored at the head node, down to the bottom of the stack
   //      at the final node.
   //   2. The instance variable top is the head reference of the linked list
   //      of items.
   private IntNode top; 
   
   
   public static void main(String[] args) {
      IntLinkedStack stack1 = new IntLinkedStack();
      System.out.println("stack1 isEmpty : " + stack1.isEmpty());
      stack1.push(1);
      stack1.push(2);
      stack1.push(3);
      stack1.push(4);
      stack1.push(5);
      System.out.print("stack1 : ");
      stack1.Print(stack1.top);
      System.out.println("size of stack1 : " + stack1.size());
      System.out.println("stack1 isEmpty : " + stack1.isEmpty());
      System.out.println("peek of stack1 : " + stack1.peek());
      System.out.println("stack1 pop : " + stack1.pop());
      System.out.print("stack1 : ");
      stack1.Print(stack1.top);
      IntLinkedStack stack2 = stack1.clone();
      System.out.print("stack2 clone of stack1 : ");
      stack2.Print(stack2.top);
   }

   // LinkedList출력 메소드
   // 만약 node.link가 null이면(rear라면)
   // 해당 node의 data까지 출력하고 return
   public void Print(IntNode node) {
      if (node.link == null) {
         System.out.print(node.data + " ");
         System.out.println();
         return;
      } else {
         System.out.print(node.data + " ");
         Print(node.link);
      }
   }
   public IntLinkedStack( )
   {
      top = null;
   }

   
   /**
   * Generate a copy of this stack.
   * param - none
   * return
   *   The return value is a copy of this stack. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to a IntLinkedStack before it can be used.
   * exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public IntLinkedStack clone( )       
   {  // Clone a IntLinkedStack.
      IntLinkedStack answer;
      
      try
      {
         answer = (IntLinkedStack) super.clone( );
      }
      catch (CloneNotSupportedException e)
      { 
         // This exception should not occur. But if it does, it would probably indicate a
         // programming error that made super.clone unavailable. The most comon error
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
     }
      
      answer.top = IntNode.listCopy(top);
      
      return answer;
   }        
 
   
   /**
   * Determine whether this stack is empty.
   * param - none
   * return
   *   true if this stack is empty;
   *   false otherwise. 
   **/
   public boolean isEmpty( )
   {
      return (top == null);
   }
   

   /**
   * Get the top item of this stack, without removing the item.
   * param - none
   * Precondition:
   *   This stack is not empty.
   * return
   *   the top item of the stack
   * exception EmptyStackException
   *   Indicates that this stack is empty.
   **/   
   public int peek( )   
   {
      if (top == null)
         // EmptyStackException is from java.util and its constructor has no argument.
         throw new EmptyStackException( );
      return top.getData( );
   }

   
   /**
   * Get the top item, removing it from this stack.
   * param - none
   * Precondition:
   *   This stack is not empty.
   * Postcondition:
   *   The return value is the top item of this stack, and the item has
   *   been removed.
   * exception EmptyStackException
   *   Indicates that this stack is empty.
   **/    
   public int pop( )
   {
      int answer;
      
      if (top == null)
         // EmptyStackException is from java.util and its constructor has no argument.
         throw new EmptyStackException( );
      
      answer = top.getData( );
      top = top.getLink( );
      return answer;
   }    


   /**
   * Push a new item onto this stack. The new item may be the null
   * reference.
   * param item
   *   the item to be pushed onto this stack 
   * Postcondition:
   *   The item has been pushed onto this stack.
   * exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the stack's capacity.
   **/    
   public void push(int item)
   {
      top = new IntNode(item, top);
   }
              

   /**
   * Accessor method to determine the number of items in this stack.
   * param - none
   * return
   *   the number of items in this stack
   **/ 
   public int size( )   
   {
      return IntNode.listLength(top);
   }
 
}

