package librarymanagement;

public class TreeNode {
	
	private String name;
	private String author;
	private String genre;
	private int pageCount;
	private int stockCount;
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode(String name) {
		this.name = name;
		left = null;
		right = null;
	}
	
	public TreeNode(String name, String author, String genre, int pageCount, int stockCount) {
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.pageCount = pageCount;
		this.stockCount = stockCount;
		left = null;
		right = null;
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

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	@Override
    public String toString() {
        return name + " - " + author + " - " + genre + " - " + pageCount + " - " + stockCount;
    }
	
	
}
