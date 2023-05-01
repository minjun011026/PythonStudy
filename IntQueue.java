import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntQueue implements Cloneable
{
   // Invariant of the IntQueue class:
   //   1. The number of items in the queue is in the instance variable manyItems.
   //   2. For a non-empty queue, the items are stored in a circular array
   //      beginning at data[front] and continuing through data[rear].
   //   3. For an empty queue, manyItems is zero and data is a reference to an
   //      array, but we don't care about front and rear.
   private int[ ] data;
   private int manyItems; 
   private int front;
   private int rear;
   public static void main(String[] args) {
      IntQueue queue1 = new IntQueue();
      System.out.println("queue1 isEmpty : "+queue1.isEmpty());
      IntQueue queue2 = new IntQueue(15);
      queue1.insert(1);
      queue1.insert(2);
      queue1.insert(3);
      queue1.insert(4);
      System.out.print("queue1 : ");
      System.out.println(Arrays.toString(queue1.data));
      System.out.println("queue1 isEmpty : "+queue1.isEmpty());
      queue2.insert(5);
      queue2.insert(6);
      queue2.insert(7);
      queue2.insert(8);
      queue2.insert(9);
      System.out.print("queue2 : ");
      System.out.println(Arrays.toString(queue2.data));
      queue1.ensureCapacity(20);
      System.out.print("queue1 after ensurecapacity : ");
      System.out.println(Arrays.toString(queue1.data));
      IntQueue queue3 = queue1.clone();
      System.out.print("queue3 clone of queue1 : ");
      System.out.println(Arrays.toString(queue3.data));
      System.out.println("queue1 capacity : "+queue1.getCapacity());
      System.out.println("queue1 size : "+queue1.size());
      System.out.println("getfront of queue1 : "+queue1.getFront());
      System.out.println("queue1 size : "+queue1.size());
      System.out.print("queue1 after trimtosize(front<=rear) : ");
      queue1.trimToSize();
      System.out.println(Arrays.toString(queue1.data));
      queue1.insert(5);
      queue1.insert(6);
      queue1.insert(7);
      queue1.getFront();
      queue1.insert(8);
      queue1.insert(9);
      queue1.getFront();
      queue1.trimToSize();
      System.out.print("queue1 after trimtosize(rear<front) : ");
      System.out.println(Arrays.toString(queue1.data));
   }

   /**
   * Initialize an empty queue with an initial capacity of 10.  Note that the
   * insert method works efficiently (without needing more
   * memory) until this capacity is reached.
   * @param - none
   * Postcondition:
   *   This queue is empty and has an initial capacity of 10.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   new int[10].
   **/   
   public IntQueue( )
   {
      final int INITIAL_CAPACITY = 10;
      manyItems = 0;
      data = new int[INITIAL_CAPACITY];
      // We don't care about front and rear for an empty queue.
   }

   
   /**
   * Initialize an empty queue with a specified initial capacity. Note that the
   * <CODE>insert</CODE> method works efficiently (without needing more
   * memory) until this capacity is reached.
   * @param initialCapacity
   *   the initial capacity of this queue
   * Precondition:
   *   initialCapacity is non-negative.
   * Postcondition:
   *   This queue is empty and has the given initial capacity.
   * @exception IllegalArgumentException
   *   Indicates that initialCapacity is negative.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for:
   *   new int[initialCapacity].
   **/   
   public IntQueue(int initialCapacity)
   {
      if (initialCapacity < 0)
         throw new IllegalArgumentException
         ("initialCapacity is negative: " + initialCapacity);
      manyItems = 0;
      data = new int[initialCapacity];
      // We don't care about front and rear for an empty queue.
   }

   
   /**
   * Generate a copy of this queue.
   * @param - none
   * @return
   *   The return value is a copy of this queue. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an IntQueue before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public IntQueue clone( )       
   {  // Clone an IntQueue.
      IntQueue answer;
      
      try
      {
         answer = (IntQueue) super.clone( );
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
      
      answer.data = (int [ ]) data.clone( );
      
      return answer;
   }        
 
   
   /**
   * Change the current capacity of this queue.
   * @param minimumCapacity
   *   the new capacity for this queue
   * Postcondition:
   *   This queue's capacity has been changed to at least <CODE>minimumCapacity</CODE>.
   *   If the capacity was already at or greater than <CODE>minimumCapacity</CODE>,
   *   then the capacity is left unchanged.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: new int[minimumCapacity]. 
   **/
   public void ensureCapacity(int minimumCapacity)
   {
      int biggerArray[ ];
      int n1, n2;
      
      if (data.length >= minimumCapacity)
         // No change needed.
         return;
      else if (manyItems == 0)
         // Just increase the size of the array because the queue is empty.
         data = new int[minimumCapacity];
      else if (front <= rear)
      {  // Create larger array and copy data[front]...data[rear] into it.
         biggerArray = new int[minimumCapacity];
         System.arraycopy(data, front, biggerArray, front, manyItems);
         data = biggerArray;
      }
      else
      {  // Create a bigger array, but be careful about copying items into it. The queue items
         // occur in two segments. The first segment goes from data[front] to the end of the 
         // array, and the second segment goes from data[0] to data[rear]. The variables n1 
         // and n2 will be set to the number of items in these two segments. We will copy
         // these segments to biggerArray[0...manyItems-1].
         biggerArray = new int[minimumCapacity];
         n1 = data.length - front;
         n2 = rear + 1;
         System.arraycopy(data, front, biggerArray, 0, n1);
         System.arraycopy(data, 0, biggerArray, n1, n2);
         front = 0;
         rear = manyItems-1;  
         data = biggerArray;
      }
   }


   /**
   * Accessor method to get the current capacity of this queue. 
   * The insert method works efficiently (without needing
   * more memory) until this capacity is reached.
   * @param - none
   * @return
   *   the current capacity of this queue
   **/
   public int getCapacity( )   
   {
      return data.length;
   }

 
   /**
   * Get the front item, removing it from this queue.
   * @param - none
   * Precondition:
   *   This queue is not empty.
   * Postcondition:
   *   The return value is the front item of this queue, and the item has
   *   been removed.
   * @exception NoSuchElementException
   *   Indicates that this queue is empty.
   **/    
   public int getFront( )
   {
      int answer;
      
      if (manyItems == 0)
         throw new NoSuchElementException("Queue underflow");
      answer = data[front];
      data[front]= 0;
      front = nextIndex(front);
      manyItems--;
      return answer;
   }
   
   
   /**
   * Insert a new item in this queue.  If the addition
   * would take this queue beyond its current capacity, then the capacity is 
   * increased before adding the new item. The new item may be the null
   * reference.
   * @param item
   *   the item to be pushed onto this queue 
   * Postcondition:
   *   The item has been pushed onto this queue.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the queue's capacity.
   * Note:
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE</CODE> will cause the queue to fail with an
   *   arithmetic overflow.
   **/    
   public void insert(int item)
   {
      if (manyItems == data.length)
      {
         // Double the capacity and add 1; this works even if manyItems is 0. However, in
         // case that manyItems*2 + 1 is beyond Integer.MAX_VALUE, there will be an 
         // arithmetic overflow and the bag will fail.
         ensureCapacity(manyItems*2 + 1);
      }
      
      if (manyItems == 0)
      {
         front = 0;
         rear = 0;
      }
      else
         rear = nextIndex(rear);
         
      data[rear] = item;
      manyItems++;
   }
              

   /**
   * Determine whether this queue is empty.
   * @param - none
   * @return
   *   true if this queue is empty;
   *   false otherwise. 
   **/
   public boolean isEmpty( )
   {
      return (manyItems == 0);
   }


   private int nextIndex(int i)
   // Precondition: 0 <= i and i < data.length
   // Postcondition: If i+1 is data.length, 
   // then the return value is zero; otherwise
   // the return value is i+1.
   {
      if (++i == data.length)
         return 0;
      else
         return i;
   }

       
   /**
   * Accessor method to determine the number of items in this queue.
   * @param - none
   * @return
   *   the number of items in this queue
   **/ 
   public int size( )   
   {
      return manyItems;
   }

   
   /**
   * Reduce the current capacity of this queue to its actual size (i.e., the
   * number of items it contains).
   * @param - none
   * Postcondition:
   *   This queue's capacity has been changed to its current size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for altering the capacity. 
   **/   
   public void trimToSize( )
   {
      int trimmedArray[ ];
      int n1, n2;
      
      if (data.length == manyItems)
         // No change needed.
         return;
      else if (manyItems == 0)
         // Just change the size of the array to 0 because the queue is empty.
         data = new int[0];
      else if (front <= rear)
      {  // Create trimmed array and copy data[front]...data[rear] into it.
         trimmedArray = new int[manyItems];
         System.arraycopy(data, front, trimmedArray, 0, manyItems);
         front = 0;
         rear = manyItems-1;
         data = trimmedArray;
      }
      else
      {  // Create a trimmed array, but be careful about copying items into it. The queue items
         // occur in two segments. The first segment goes from data[front] to the end of the 
         // array, and the second segment goes from data[0] to data[rear]. The variables n1 
         // and n2 will be set to the number of items in these two segments. We will copy
         // these segments to trimmedArray[0...manyItems-1].
         trimmedArray = new int[manyItems];
         n1 = data.length - front;
         n2 = rear + 1;
         System.arraycopy(data, front, trimmedArray, 0, n1);
         System.arraycopy(data, 0, trimmedArray, n1, n2);
         front = 0;
         rear = manyItems-1;  
         data = trimmedArray;
      }
   } 
}
