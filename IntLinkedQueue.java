import java.util.NoSuchElementException;

public class IntLinkedQueue implements Cloneable
{
   // Invariant of the IntLinkedQueue class:
   //   1. The number of items in the queue is stored in the instance variable
   //      manyNodes.
   //   2. The items in the queue are stored in a linked list, with the front 
   //      of the queue stored at the head node, and the rear of the queue at 
   //      the final node.
   //   3. For a non-empty queue, the instance variable front is the head 
   //      reference of the linked list of items and the instance variable rear
   //      is the tail reference of the linked list. For an empty queue, both 
   //      front and rear are the null reference.
   private int manyNodes;
   private IntNode front;
   private IntNode rear;
   /**
   * Initialize an empty queue.
   * @param - none
   * Postcondition:
   *   This queue is empty.
   **/   
   public static void main(String[] args) {
      IntLinkedQueue queue1 = new IntLinkedQueue();
      System.out.println("queue1 isEmpty : "+queue1.isEmpty()); 
      queue1.insert(1);
      queue1.insert(2);
      queue1.insert(3);
      queue1.insert(4);
      System.out.print("queue1 : ");
      queue1.Print(queue1.front);
      System.out.println("queue1 isEmpty : " + queue1.isEmpty());
      System.out.println("queue1 getFront : "+queue1.getFront());
      System.out.print("queue1 after getFront : ");
      queue1.Print(queue1.front);
      IntLinkedQueue queue2 = queue1.clone();
      System.out.println("queue2 clone of queue1 size : "+queue2.size());
   }
   //LinkedList출력 메소드
   //만약 node.link가 null이면(rear라면)
   //해당 node의 data까지 출력하고 return
   public void Print(IntNode node){
      if(node.link==null){
         System.out.print(node.data + " ");
         System.out.println();
         return;
      }else{
         System.out.print(node.data+" ");
         Print(node.link);
      }
   }
   public IntLinkedQueue( )
   {
      front = null;
      rear = null;
   }

   
   /**
   * Generate a copy of this queue.
   * @param - none
   * @return
   *   The return value is a copy of this queue. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an IntLinkedQueue before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public IntLinkedQueue clone( )       
   {  // Clone an IntLinkedQueue.
      IntLinkedQueue answer;
      IntNode[ ] cloneInfo;
      
      try
      {
         answer = (IntLinkedQueue) super.clone( );
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
      
      cloneInfo = IntNode.listCopyWithTail(front);
      answer.front = cloneInfo[0];
      answer.rear = cloneInfo[1];
      
      return answer;
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

      if (manyNodes == 0)
         // NoSuchElementException is from java.util and its constructor has no argument.
         throw new NoSuchElementException("Queue underflow");
      answer = front.getData( );
      front = front.getLink( );
      manyNodes--;
      if (manyNodes == 0)
         rear = null;
      return answer;
   }
   
   
   /**
   * Put a new a new item in this queue. 
   * @param item
   *   the item to be pushed onto this queue 
   * Postcondition:
   *   The item has been pushed onto this queue.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the queue's capacity.
   * Note:
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the queue to fail with an
   *   arithmetic overflow.
   **/    
   public void insert(int item)
   {
       if (isEmpty( ))
       {  // Insert first item.
	  front = new IntNode(item, null);
	  rear = front;
       }
       else
       {  // Insert an item that is not the first.
          rear.addNodeAfter(item);
          rear = rear.getLink( );          
       }
       manyNodes++;
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
      return (manyNodes == 0);
   }


   /**
   * Accessor method to determine the number of items in this queue.
   * @param - none
   * @return
   *   the number of items in this queue
   **/ 
   public int size( )   
   {
      return manyNodes;
   }

}
class IntNode{
   int data;
   IntNode link;

   /**
    * Initialize a node with a specified initial data and link to the next
    * node. Note that the initialLink may be the null reference,
    * which indicates that the new node has nothing after it.
    * 
    * @param initialData
    *                    the initial data of this new node
    * @param initialLink
    *                    a reference to the node after this new node--this
    *                    reference may be null
    *                    to indicate that there is no node after this new node.
    * @postcondition
    *                This node contains the specified data and link to the next
    *                node.
    **/
   public IntNode(int initialData, IntNode initialLink) {
      data = initialData;
      link = initialLink;
   }
   

public void addNodeAfter(int item)
{
link = new IntNode(item, link);
}

/**
* Accessor method to get the data from this node.
* @param - none
* @return
* the data from this node
**/
public int getData( )
{
return data;
}

/**
* Accessor method to get a reference to the next node after this node.
* @param - none
* @return
* a reference to the node after this node (or the null reference if there
* is nothing after this node)
**/
public IntNode getLink( )
{
return link;
}
/**
* Copy a list.
* @param source
* the head of a linked list that will be copied (which may be
* an empty list in where source is null)
* @return
* The method has made a copy of the linked list starting at
* source. The return value is the head reference for the
* copy.
* @exception OutOfMemoryError
* Indicates that there is insufficient memory for the new list.
**/
public static IntNode listCopy(IntNode source)
{
IntNode copyHead;
IntNode copyTail;
// Handle the special case of the empty list.
if (source == null)
return null;
// Make the first node for the newly created list.
copyHead = new IntNode(source.data, null);
copyTail = copyHead;
// Make the rest of the nodes for the newly created list.
while (source.link != null)
{
source = source.link;
copyTail.addNodeAfter(source.data);
copyTail = copyTail.link;
}
// Return the head reference for the new list.
return copyHead;
}
/**
* Copy a list, returning both a head and tail reference for the copy.
* @param source
* the head of a linked list that will be copied (which may be
* an empty list in where source is null)
* @return
* The method has made a copy of the linked list starting at
* source. The return value is an
* array where the [0] element is a head reference for the copy and the [1]
* element is a tail reference for the copy.
* @exception OutOfMemoryError
* Indicates that there is insufficient memory for the new list.
**/
public static IntNode[ ] listCopyWithTail(IntNode source)
{
IntNode copyHead;
IntNode copyTail;
IntNode[ ] answer = new IntNode[2];
// Handle the special case of the empty list.
if (source == null)
return answer; // The answer has two null references .
// Make the first node for the newly created list.
copyHead = new IntNode(source.data, null);
copyTail = copyHead;
// Make the rest of the nodes for the newly created list.
while (source.link != null)
{
source = source.link;
copyTail.addNodeAfter(source.data);
copyTail = copyTail.link;
}
// Return the head and tail references.
answer[0] = copyHead;
answer[1] = copyTail;
return answer;
}
/**
* Compute the number of nodes in a linked list.
* @param head
* the head reference for a linked list (which may be an empty list
* with a null head)
* @return
* the number of nodes in the list with the given head
* @note
* A wrong answer occurs for lists longer than Int.MAX_VALUE.
**/
public static int listLength(IntNode head)
{
IntNode cursor;
int answer;
answer = 0;
for (cursor = head; cursor != null; cursor = cursor.link)
answer++;
return answer;
}

/**
* Copy part of a list, providing a head and tail reference for the new copy.
* @param start/end
* references to two nodes of a linked list
* @param copyHead/copyTail
* the method sets these to refer to the head and tail node of the new
* list that is created
* @precondition
* start and end are non-null references to nodes
* on the same linked list,
* with the start node at or before the end node.
* @return
* The method has made a copy of the part of a linked list, from the
* specified start node to the specified end node. The return value is an
* array where the [0] component is a head reference for the copy and the
* [1] component is a tail reference for the copy.
* @exception IllegalArgumentException
* Indicates that start and end are not references
* to nodes on the same list.
* @exception NullPointerException
* Indicates that start is null.
* @exception OutOfMemoryError
* Indicates that there is insufficient memory for the new list.
**/
public static IntNode[ ] listPart(IntNode start, IntNode end)
{
IntNode copyHead;
IntNode copyTail;
IntNode cursor;
IntNode[ ] answer = new IntNode[2];
// Make the first node for the newly created list. Notice that this will
// cause a NullPointerException if start is null.
copyHead = new IntNode(start.data, null);
copyTail = copyHead;
cursor = start;
// Make the rest of the nodes for the newly created list.
while (cursor != end)
{
cursor = cursor.link;
if (cursor == null)
throw new IllegalArgumentException
("end node was not found on the list");
copyTail.addNodeAfter(cursor.data);
copyTail = copyTail.link;
}
// Return the head and tail references
answer[0] = copyHead;
answer[1] = copyTail;
return answer;
}
/**
* Find a node at a specified position in a linked list.
* @param head
* the head reference for a linked list (which may be an empty list in
* which case the head is null)
* @param position
* a node number
* @precondition
* position > 0.
* @return
* The return value is a reference to the node at the specified position in
* the list. (The head node is position 1, the next node is position 2, and
* so on.) If there is no such position (because the list is too short),
* then the null reference is returned.
* @exception IllegalArgumentException
* Indicates that position is not positive.
**/
public static IntNode listPosition(IntNode head, int position)
{
IntNode cursor;
int i;
if (position <= 0)
throw new IllegalArgumentException("position is not positive");
cursor = head;
for (i = 1; (i < position) && (cursor != null); i++)
cursor = cursor.link;
return cursor;
}

/**
* Search for a particular piece of data in a linked list.
* @param head
* the head reference for a linked list (which may be an empty list in
* which case the head is null)
* @param target
* a piece of data to search for
* @return
* The return value is a reference to the first node that contains the
* specified target. If there is no such node, the null reference is
* returned.
**/
public static IntNode listSearch(IntNode head, int target)
{
IntNode cursor;
for (cursor = head; cursor != null; cursor = cursor.link)
if (target == cursor.data)
return cursor;
return null;
}
/**
* Modification method to remove the node after this node.
* @param - none
* @precondition
* This node must not be the tail node of the list.
* @postcondition
* The node after this node has been removed from the linked list.
* If there were further nodes after that one, they are still
* present on the list.
* @exception NullPointerException
* Indicates that this was the tail node of the list, so there is nothing
* after it to remove.
**/
public void removeNodeAfter( )
{
link = link.link;
}

/**
* Modification method to set the data in this node.
* @param newData
* the new data to place in this node
* @postcondition
* The data of this node has been set to newData.
**/
public void setData(int newData)
{
data = newData;
}

/**
* Modification method to set the link to the next node after this node.
* @param newLink
* a reference to the node that should appear after this node in the linked
* list (or the null reference if there is no node after this node)
* @postcondition
* The link to the node after this node has been set to newLink.
* Any other node (that used to be in this link) is no longer connected to
* this node.
**/
public void setLink(IntNode newLink)
{
link = newLink;
}
}
