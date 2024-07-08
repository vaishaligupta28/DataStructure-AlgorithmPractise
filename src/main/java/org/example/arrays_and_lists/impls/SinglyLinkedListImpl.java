package org.example.arrays_and_lists.impls;

/**
 * Linked Lists
 *
 * Types:
 * Singly, Doubly, Circular, Circular Doubly, Header
 */
public class SinglyLinkedListImpl {
    private Node headNode;

    SinglyLinkedListImpl() {
    }

    static class Node {
        String item;
        Node next;

        public void setItem(String item) {
            this.item = item;
        }

        public String getItem() {
            return item;
        }

        public void setNextNode(Node next) {
            this.next = next;
        }

        public Node getNextNode() {
            return this.next;
        }
    }


    public static void main(String[] args) {
        SinglyLinkedListImpl list = new SinglyLinkedListImpl();
        list.addFirst("good");// at beginning
        list.addFirst("to"); // at beginning

        list.add("to"); // at end
        list.add("true"); // at end
        list.add("true2"); // at end
        list.add("true3"); // at end

        list.add("be", 3); // at an ith position
        list.add("be1", 3); // at an ith position
        list.add("be1", 8); // at an ith position

        list.addFirst("phrase:");// at beginning again

        list.printList();

    }


    private void addFirst(String element) {
        if(headNode == null) {

            // Important to note here is that we created two things-->>>
            // - One is the actual head node data that is stored in heap memory
            // - Another is the reference variable which references
            //  the value stored in heap and the reference variable is stored in stack

            // Unlike primitive types like int and float that are directly stored in stack
            // and not in heap and hence they have no reference stored.
            headNode = new Node();
            headNode.setItem(element);
            return;
        }

        Node newNode = new Node();
        newNode.setItem(element);
        newNode.setNextNode(headNode);//setting head node object value
        headNode = newNode;//changing the reference headNode to new node's reference
    }

    void printList()
    {
        Node node = headNode;
        while (node.getNextNode() != null) {
            System.out.print(node.getItem() + " -> ");
            node = node.getNextNode();
        }
        System.out.println(node.getItem());
    }

    private void add(String element) {
        if(headNode == null) {
            addFirst(element);
        }

        Node newNode = new Node();
        newNode.setItem(element);

        Node tempNode = headNode;
        while(tempNode.getNextNode() != null) {
            tempNode = tempNode.getNextNode();
        }

        tempNode.setNextNode(newNode);
    }

    private void add(String element, int position) {
        if(headNode == null || position == 0) {
            addFirst(element);
        }

        Node newNode = new Node();
        newNode.setItem(element);

        int c=0;
        Node tempNode = headNode;

        while(tempNode.getNextNode()!= null) {
            tempNode = tempNode.getNextNode();
            c++;
            if(c == (position-1)) {
                break;
            }
        }
        newNode.setNextNode(tempNode.getNextNode());
        tempNode.setNextNode(newNode);
    }
}



