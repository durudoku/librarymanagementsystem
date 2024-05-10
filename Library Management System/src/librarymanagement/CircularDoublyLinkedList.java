package librarymanagement;

public class CircularDoublyLinkedList {

	private String name;
	private Node first;
	private int size;
	
	public CircularDoublyLinkedList() {
		first = null;
		name = null;
		size = 0;
	}
	
	public void createSingleNode(String name, String author, String genre, int pageCount, int stockCount) {
			Node newNode = new Node(name, author, genre, pageCount, stockCount);
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			first = newNode;
	}
	
	public void insertEnd(String name, String author, String genre, int pageCount, int stockCount) {
		if (first == null) {
			createSingleNode(name, author, genre, pageCount, stockCount);
			size++;
			return;
		}

		Node last = first.getPrev();
		Node newNode = new Node(name, author, genre, pageCount, stockCount);
		newNode.setNext(first);
		first.setPrev(newNode);
		newNode.setPrev(last);
		last.setNext(newNode);
		size++;
	}
	
	public void insertBegin(String name, String author, String genre, int pageCount, int stockCount) {
		if (first == null) {
			createSingleNode(name, author, genre, pageCount, stockCount);
			size++;
			return;
		}
		//If list is not empty
		Node last = first.getPrev();
		Node newNode = new Node(name, author, genre, pageCount, stockCount);
		newNode.setNext(first);
		newNode.setPrev(last);
		first.setPrev(newNode);
		last.setNext(newNode);
		first = newNode;
		size++;
	}
	
	public void deleteNode(String key) {
		//If list is empty
		if (first == null)
			return;
		//If list is not empty
		Node current = first;
		Node prev = null;
		//Find the required node
		while (current.getName() != key) {
			// If the node is not present in the list
			if (current.getNext() == first) {
				System.out.println("List doesn't have a node with value " + key);
				return;
			}
			prev = current;
			current = current.getNext();
		}
		//Check if node is the only node in the list
		if (current.getNext() == first && prev == null) {
			first = null;
			size--;
			return;
		}
		//If list has more than one node,
		//check if it is the first node
		if (current == first) {
			prev = first.getPrev();
			first = first.getNext();
			prev.setNext(first);
			first.setPrev(prev);
			size--;
		}
		//check if it is the last node
		else if (current.getNext() == first) {
			prev.setNext(first);
			first.setPrev(prev);
			size--;
		}
		else {
			Node temp = current.getNext();
			prev.setNext(temp);
			temp.setPrev(prev);
			size--;
		}
	}
	
	public void display() {
		Node temp = first;
		while (temp.getNext() != first) {
			System.out.println(temp.getName());
			temp = temp.getNext();
		}
		System.out.println(temp.getName());
	}
	
	public boolean search(String e) {
		if (first == null)
			return false;
		else if (this.getSize() == 1)
			return (first.toString().equals(e));
		else {
			Node current = first;
			while (current.getNext() != first) {
				if (current.toString().equals(e))
					return true;
				current = current.getNext();
			}
			return current.toString().equals(e);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
