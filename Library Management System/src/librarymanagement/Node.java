package librarymanagement;

public class Node {
	
	private String name;
	private String author;
	private String genre;
	private int pageCount;
	private int stockCount;
	private Node prev;
	private Node next;
	
	public Node() {
		prev = null;
		next = null;
		name = null;
	}
	
	public Node(String name) {
		prev = null;
		next = null;
		this.name = name;
	}

	public Node(String name, String author, String genre, int pageCount, int stockCount) {
		super();
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.pageCount = pageCount;
		this.stockCount = stockCount;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public Node getPrev() {
		return prev;
	}


	public void setPrev(Node prev) {
		this.prev = prev;
	}


	public Node getNext() {
		return next;
	}


	public void setNext(Node next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return name + "-" + author + "-" + genre + "-" + pageCount + "-" + stockCount;
	}
	
	
}
