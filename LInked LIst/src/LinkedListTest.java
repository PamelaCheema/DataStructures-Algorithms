import org.junit.Test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/*
 Note that this is by no means an exhaustive testing suite for a LinkedList implementation.
 Your LinkedList implementation will subject to a more rigorous testing suite.
 It is highly recommended that you add to these tests, or create your own to make sure your class is robust.
 */

public class LinkedListTest {

    static DoublyLinkedList emptyListy;
    static DoublyLinkedList nonEmptyListy;
    static int COMMON_HEAD_TESTING_VALUE = 5;

    /*
    This method is run prior to every unit test to ensure a fresh state for both lists.
     */
    @org.junit.Before
    public void setUp(){
        emptyListy = new DoublyLinkedList();
        nonEmptyListy = new DoublyLinkedList(COMMON_HEAD_TESTING_VALUE);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        return;
    }

    /*
    Examine the fields of the LinkedList class and ensure that there is at least one called head.
     */
    @org.junit.Test
    public void testFields(){
        Class c = DoublyLinkedList.class;
        Field[] fields = c.getDeclaredFields();
        assertTrue("Must have at least one field.", fields.length >= 1);

        for (Field field : fields)
            if (!Modifier.isPrivate(field.getModifiers()))
                fail("All fields should be declared as private.");

        assertTrue("head should be your first field.", fields[0].getName().equals("head"));

        assertEquals("head should be a nested static class called Node.", "DoublyLinkedList$Node", fields[0].getType().getName());
    }

    private void testConstructor(int constructorNumber){
        // inspect the fields to ensure that head is null
        try {
            Class c = DoublyLinkedList.class;
            // test that the constructor has no parameters. note it must be the first constructor in LinkedList.
            Constructor constructor = c.getDeclaredConstructors()[constructorNumber];

            //load the fields into c and set the appropriate accessibility
            c.getDeclaredFields();
            Field f = c.getDeclaredField("head");
            f.setAccessible(true);
            try{
                switch (constructorNumber){
                    case 0:
                        // create an instance of LinkedList whose field we wish to get
                        DoublyLinkedList emptyList = new DoublyLinkedList();
                        Object headField = f.get(emptyList);
                        assertEquals("The first constructor should be a zero-parameter constructor.", 0, constructor.getParameterTypes().length);
                        assertNull(headField);
                        break;

                    case 1:
                        // create an instance of LinkedList whose field we wish to get
                        DoublyLinkedList notEmptyList = new DoublyLinkedList(COMMON_HEAD_TESTING_VALUE);
                        Object notEmptyHeadField = f.get(notEmptyList);
                        assertEquals("The second constructor should be a one-parameter constructor.", 1, constructor.getParameterTypes().length);
                        assertNotNull(notEmptyHeadField);
                        break;

                    default:
                        break;
                }

            }
            catch (IllegalAccessException e){
                fail("IllegalAccessException.");
            }
        }
        catch (NoSuchFieldException e){
            fail("Your DoublyLinkedList class does not contain a field called head.");
        }
    }

    /*
    Test the zero-parameter constructor for LinkedList
     */
    @org.junit.Test
    public void testZeroParameterConstructor(){
        testConstructor(0);
    }

    /*
    Test the one-parameter constructor for LinkedList
     */
    @org.junit.Test
    public void testOneParameterConstructor(){
        testConstructor(1);
    }

    /*
   This test ensures that author() has been defined, returns a String, and that there is a comma in the returned String.
    */
    @org.junit.Test
    public void testAuthor() {
        String student_author = DoublyLinkedList.author();
        assertNotNull("You didn't replace a null author value.", student_author);
        String[] names = student_author.split(",");
        assertEquals("There should be exactly one comma separating your last name from your first name.",
                2, names.length);
    }

    /*
    This test ensures that isEmpty() works for both an empty and non-empty list.
     */
    @org.junit.Test
    public void testIsEmpty() {
        assertTrue("A new empty Linked List should be empty after instantiation.", emptyListy.isEmpty());
        assertFalse("Building a Linked List with a value for head should not be empty.", nonEmptyListy.isEmpty());
    }

    /*
    This test ensures that size() works immediately after instantiating a list.
     */
    @org.junit.Test
    public void testSize() {
        assertEquals("A new empty Linked List should have a size of 0.", 0, emptyListy.size());
        assertEquals("Building a Linked List with a value for head should have a non-zero size.", 1, nonEmptyListy.size());
    }

    /*
    This test ensures that toString works for an empty and a one-element list.
     */
    @org.junit.Test
    public void testToString() {
        assertEquals("Incorrect toString for an empty Linked List.", "null", emptyListy.toString());
        assertEquals("Incorrect toString for a Linked List with a single value for head.", COMMON_HEAD_TESTING_VALUE + " --> null", nonEmptyListy.toString());
    }

    /*
    This test ensures that an IndexOutOfBoundsException is thrown when attempting to get an invalid index.
     */
    @org.junit.Test (expected = IndexOutOfBoundsException.class)
    public void testGetIncorrectlyFromEmptyList() {
        emptyListy.get(0);
    }

    /*
    This test ensures that an IndexOutOfBoundsException is thrown when attempting to get an invalid index from a non-empty list.
     */
    @org.junit.Test (expected = IndexOutOfBoundsException.class)
    public void testGetIncorrectlyFromNonEmptyList() {
        nonEmptyListy.get(1);
    }

    /*
    This test ensures that the data is correctly retrieved from the head of a one-element list.
     */
    @org.junit.Test
    public void testGetFromNonEmptyList(){
        assertEquals("Did not correctly get the first element from a non-empty list.", COMMON_HEAD_TESTING_VALUE, nonEmptyListy.get(0));
    }

    /*
    This test ensures that contains works for an empty and a one-element list.
     */
    @org.junit.Test
    public void testContains() {
        assertFalse("Failed to correctly identify an integer not contained in a list.", emptyListy.contains(COMMON_HEAD_TESTING_VALUE));
        assertTrue("Failed to correctly identify an integer contained in a Linked List.", nonEmptyListy.contains(COMMON_HEAD_TESTING_VALUE));
    }

    /*
    This test ensures that appending to the end of a list works correctly.
     */
    @org.junit.Test
    public void testAddAtEnd() {
        int firstNodeValue = 3;
        emptyListy.add(firstNodeValue);
        assertEquals("Did not correctly add to an empty list.", firstNodeValue, emptyListy.get(0));

        int secondNodeValue = 7;
        emptyListy.add(secondNodeValue);
        assertEquals("Did not correctly add to a one-element list.", secondNodeValue, emptyListy.get(1));

        int thirdNodeValue = -4;
        emptyListy.add(thirdNodeValue);
        assertEquals("Did not correctly add to a two-element list.", thirdNodeValue, emptyListy.get(2));
    }

    /*
    This test ensures that a elements are inserted correctly and that other nodes are shifted down after inserting a node.
     */
    @org.junit.Test
    public void testAddAtIndex() {
        // attempt to make head --> 7 --> 8 --> 9
        int firstNodeValue = 9;
        emptyListy.add(0, firstNodeValue);
        assertEquals("Did not correctly add at index 0 to an empty list.", firstNodeValue, emptyListy.get(0));

        int secondNodeValue = 8;
        emptyListy.add(0, secondNodeValue);
        assertEquals("Did not correctly add at index 0 to a one-element list.", secondNodeValue, emptyListy.get(0));
        assertEquals("Did not correctly move down elements after adding at index 0 to a one-element list.", firstNodeValue, emptyListy.get(1));

        int thirdNodeValue = 7;
        emptyListy.add(0, thirdNodeValue);
        assertEquals("Did not correctly add at index 0 to a two-element list.", thirdNodeValue, emptyListy.get(0));
        assertEquals("Did not correctly move down elements after adding at index 0 to a two-element list.", secondNodeValue, emptyListy.get(1));
        assertEquals("Did not correctly move down elements after adding at index 0 to a two-element list.", firstNodeValue, emptyListy.get(2));
    }

    /*
    This test ensures that a IndexOutOfBoundsException is thrown when index is invalid.
     */
    @org.junit.Test (expected = IndexOutOfBoundsException.class)
    public void testAddAtIncorrectIndex() {
        emptyListy.add(1, 7);
        nonEmptyListy.add(7, 7);
    }

    /*
    This test ensures that size() still works after adding an element to the linked list.
     */
    @org.junit.Test
    public void testSizeAfterAdd() {
        emptyListy.add(3);
        assertEquals("size() is incorrect after adding an element to an empty list.", 1, emptyListy.size());
        emptyListy.add(7);
        assertEquals("size() is incorrect after adding an element to a list.", 2, emptyListy.size());
    }

    /*
    This test checks that the correct value is returned when the head is removed.
     */
    @org.junit.Test
    public void testRemoveAtHead() {
        assertEquals("Did not return the correct value after removing the head.", COMMON_HEAD_TESTING_VALUE, nonEmptyListy.remove());
    }

    /*
    This test checks that the correct value is returned when a Node's index is specified.
     */
    @org.junit.Test
    public void testRemoveAtIndex() {
        assertEquals("Did not return the correct value after removing the Node at index 0.", COMMON_HEAD_TESTING_VALUE, nonEmptyListy.remove());
    }
}
