package librarymanagement;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree {
	private TreeNode root;
	private int size;
	private List<String> books;
	
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	private TreeNode createNewNode(String e) {
		return new TreeNode(e);
	}
	
	public int getSize() {
		return size;
	}
	
	public TreeNode getRoot() {
		return root;
	}
	
	public boolean insert(String e) {
		
		if (root == null)
			root = createNewNode(e);
		else {
			TreeNode parent = null;
			TreeNode current = root;
			while (current != null) {
				if (e.compareTo(current.getName()) < 0) {
					parent = current;
					current = current.getLeft();
				}
				else if (e.compareTo(current.getName()) > 0) {
					parent = current;
					current = current.getRight();
				}
				else
					return false;
			}

			if (e.compareTo(parent.getName()) < 0)
				parent.setLeft(createNewNode(e));
			else
				parent.setRight(createNewNode(e));
		}
		size++;
		return true;
	}
	
	public boolean insert(TreeNode e) {
		if (root == null)
			root = e;
		else {
			TreeNode parent = null;
			TreeNode current = root;
			while (current != null) {
				if (e.getName().compareTo(current.getName()) < 0) {
					parent = current;
					current = current.getLeft();
				}
				else if (e.getName().compareTo(current.getName()) > 0) {
					parent = current;
					current = current.getRight();
				}
				else
					return false;
			}
			if (e.getName().compareTo(parent.getName()) < 0)
				parent.setLeft(e);
			else
				parent.setRight(e);
		}
		size++;
		return true;
	}

	public List<String> inorderList() {
	    List<String> books = new ArrayList<>();
	    inorder(root, books);
	    return books;
	}

	private void inorder(TreeNode node, List<String> books) {
	    if (node != null) {
	        inorder(node.getLeft(), books);
	        books.add(formatBookDetails(node));  // Use the formatBookDetails for full book information
	        inorder(node.getRight(), books);
	    }
	}
	
	public boolean delete(String e) {
		TreeNode parent = null;
		TreeNode current = root;

		while (current != null) {
			if (e.compareTo(current.getName()) < 0) {
				parent = current;
				current = current.getLeft();
			}
			else if (e.compareTo(current.getName()) > 0) {
				parent = current;
				current = current.getRight();
			}
			else
				break;
		}
		if (current == null)
			return false;
		
		if (current.getLeft() == null) {
			if (parent == null)
				root = current.getRight();
			else {
				if (e.compareTo(parent.getName()) < 0)
					parent.setLeft(current.getRight());
				else
					parent.setRight(current.getRight());
			}
		}
		else {
			TreeNode parentOfRightMost = current;
			TreeNode rightMost = current.getLeft();
			while (rightMost.getRight() != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.getRight();
			}
			current.setName(rightMost.getName());
			if (parentOfRightMost.getRight() == rightMost)
				parentOfRightMost.setRight(rightMost.getLeft());
			else // Special case: parentOfRightMost == current
				parentOfRightMost.setLeft(rightMost.getLeft());
		}
		size--;
		return true;
	}
	
	public boolean booleanSearch(String e) {
		TreeNode current = root;
		while (current != null) {
			if (e.compareTo(current.getName()) < 0)
				current = current.getLeft();
			else if (e.compareTo(current.getName()) > 0)
				current = current.getRight();
			else
				return true;
		}
		return false;
	}
	
	public TreeNode treeNodeSearch(String name) {
	    if (name == null) {
	        return null; // Avoid searching with a null name
	    }
	    TreeNode current = root;
	    while (current != null) {
	        if (current.getName() == null) {
	            current = current.getRight(); // Continue search if current node's name is null
	        } else if (name.compareTo(current.getName()) < 0) {
	            current = current.getLeft();
	        } else if (name.compareTo(current.getName()) > 0) {
	            current = current.getRight();
	        } else {
	            return current; // Found
	        }
	    }
	    return null;
	}

	public List<String> searchByCriteria(String searchText, String searchBy) {
	    List<String> results = new ArrayList<>();
	    searchRecursive(root, searchText.toLowerCase(), searchBy, results);
	    return results;
	}
	
	private void searchRecursive(TreeNode node, String searchText, String searchBy, List<String> results) {
	    if (node == null) {
	        return;
	    }
	    String nodeValue = getNodeValueByCriteria(node, searchBy);
	    if (nodeValue != null && nodeValue.toLowerCase().contains(searchText)) {
	        results.add(formatBookDetails(node));
	    }
	    searchRecursive(node.getLeft(), searchText, searchBy, results);
	    searchRecursive(node.getRight(), searchText, searchBy, results);
	}

	private String getNodeValueByCriteria(TreeNode node, String criteria) {
	    if (node == null) {
	        return null;
	    }
	    switch (criteria.toLowerCase()) {
	        case "name":
	            return node.getName();
	        case "author":
	            return node.getAuthor();
	        case "genre":
	            return node.getGenre();
	        default:
	            return null;
	    }
	}

	private String formatBookDetails(TreeNode node) {
	    return String.format("%s - %s - %s - %s - %s", node.getName(), node.getAuthor(), node.getGenre(), node.getPageCount(), node.getStockCount());
	}

    
    public List<String> sortBy(String sortBy) {
        List<TreeNode> nodeList = new ArrayList<>();
        collectNodes(root, nodeList);
        
        Comparator<TreeNode> comparator;
        switch (sortBy) {
            case "Book Name":
                comparator = Comparator.comparing(TreeNode::getName);
                break;
            case "Author Name":
                comparator = Comparator.comparing(TreeNode::getAuthor);
                break;
            case "Genre":
                comparator = Comparator.comparing(TreeNode::getGenre);
                break;
            case "Page Count":
                comparator = Comparator.comparing(TreeNode::getPageCount);
                break;
            default:
                return new ArrayList<>();
        }
        
        nodeList.sort(comparator);
        List<String> sortedNames = new ArrayList<>();
        for (TreeNode node : nodeList) {
            sortedNames.add(formatBookDetails(node));
        }
        return sortedNames;
    }

    private void collectNodes(TreeNode node, List<TreeNode> nodeList) {
        if (node != null) {
            collectNodes(node.getLeft(), nodeList);
            nodeList.add(node);
            collectNodes(node.getRight(), nodeList);
        }
    }

}
