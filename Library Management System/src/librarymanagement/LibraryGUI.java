package librarymanagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class LibraryGUI extends JFrame {
	
	// String name, String author, String genre, int pageCount, int stockCount
	private TreeNode book1 = new TreeNode("To Kill a Mockingbird","Harper Lee", "Classics", 323 , 34);
	private TreeNode book2 = new TreeNode("1984","George Orwell", "Classics", 368 , 83);
	private TreeNode book3 = new TreeNode("The Great Gatsby","F. Scott Fitzgerald", "Classics", 180, 59);
	private TreeNode book4 = new TreeNode("Animal Farm","George Orwell", "Dystopia", 141, 13);
	private TreeNode book5 = new TreeNode("Fahrenheit 451", "J.R.R. Tolkien", "Fantasy", 194, 35);
	private TreeNode book6 = new TreeNode("The Hobbit", "Ray Bradbury", "Fiction", 366, 103);
	private TreeNode book7 = new TreeNode("The Little Prince","Antoine de Saint-Exupery", "Fiction", 96, 70);
	private TreeNode book8 = new TreeNode("You Know What You Did","K.T. Nguyen", "Mystery", 384 , 120);
	private TreeNode book9 = new TreeNode("Funny Story","Emily Henry", "Romance", 400, 49);
	
    private BinarySearchTree bookLibrary = new BinarySearchTree();
    private CircularDoublyLinkedList bookList = new CircularDoublyLinkedList();
   
    private JTextField txtSearch, txtName, txtAuthor, txtGenre, txtPageCount, txtStockCount;
    private JButton btnSearch, btnSave, btnShowAll, btnSort;
    private JRadioButton rbAdd, rbEdit;
    private JComboBox<String> cbSortOptions;
    private JComboBox<String> cbSearchOptions;
    
    private JList<String> listBooks;
    private DefaultListModel<String> listModel;

    public LibraryGUI() {
    	createDatabase();
    	setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 100, 815, 620);
        setLocationRelativeTo(null);  
        initUI();
        layoutComponents();
        bindEvents();
        setupListSelectionListener();
    }

    private void initUI() {    	
        txtSearch = new JTextField(20);
        txtName = new JTextField(20);
        txtAuthor = new JTextField(20);
        txtGenre = new JTextField(20);
        txtPageCount = new JTextField(20);
        txtStockCount = new JTextField(20);
        btnSearch = new JButton("Search");
        btnShowAll = new JButton("Show All Books");
        btnSort = new JButton("Sort");
        rbAdd = new JRadioButton("Add", true);
        rbEdit = new JRadioButton("Edit");
        cbSortOptions = new JComboBox<>(new String[]{"Book Name", "Author Name", "Genre", "Page Count"});
        cbSearchOptions = new JComboBox<>(new String[]{"Name", "Author", "Genre"});
        listModel = new DefaultListModel<>();
        listBooks = new JList<>(listModel);
        rbAdd = new JRadioButton("Add", true);
        rbEdit = new JRadioButton("Edit");
        btnSave = new JButton("Save");
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbAdd);
        bg.add(rbEdit);
    }

    private void layoutComponents() {
        setLayout(null);
        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblSearch.setBounds(50, 30, 100, 20);
        add(lblSearch);
        
        cbSearchOptions.setFont(new Font("Montserrat", Font.PLAIN, 14));
        cbSearchOptions.setBounds(50, 53, 75, 40);
        add(cbSearchOptions);
        
        txtSearch.setFont(new Font("Montserrat", Font.PLAIN, 14));
        txtSearch.setBounds(150, 53, 435, 40);
        add(txtSearch);
        
        btnSearch.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnSearch.setBounds(610, 53, 155, 40);
        add(btnSearch);
        
        listBooks.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        listBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane spListBooks = new JScrollPane(listBooks);
        JLabel lblSp = new JLabel("Books");
        lblSp.setFont(new Font("Montserrat", Font.PLAIN, 14));
        spListBooks.setFont(new Font("Montserrat", Font.PLAIN, 14));
        spListBooks.setBounds(50, 120, 535, 300);
        spListBooks.setColumnHeaderView(lblSp);
        add(spListBooks);
        
        btnShowAll.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnShowAll.setBounds(610, 120, 155, 40);
        add(btnShowAll);
        
        cbSortOptions.setFont(new Font("Montserrat", Font.PLAIN, 14));
        cbSortOptions.setBounds(610, 328, 155, 40);
        add(cbSortOptions);
        
        btnSort.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnSort.setBounds(610, 378, 155, 40);
        add(btnSort);
        
        JLabel lblAddEdit = new JLabel("Add/Edit:");
        lblAddEdit.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblAddEdit.setBounds(50, 430, 75, 40);
        add(lblAddEdit);
        
        rbAdd.setFont(new Font("Montserrat", Font.PLAIN, 14));
        rbAdd.setBounds(140, 432, 55, 40);
        add(rbAdd);
        
        rbEdit.setFont(new Font("Montserrat", Font.PLAIN, 14));
        rbEdit.setBounds(200, 432, 75, 40);
        add(rbEdit);
        
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Montserrat", Font.PLAIN, 14));
        lblName.setBounds(50, 478, 75, 20);
        add(lblName);
        
        txtName.setFont(new Font("Montserrat", Font.PLAIN, 14));
        txtName.setBounds(100, 480, 200, 20);
        add(txtName);
        
        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setFont(new Font("Montserrat", Font.PLAIN, 14));
        lblAuthor.setBounds(330, 478, 75, 20);
        add(lblAuthor);
        
        txtAuthor.setFont(new Font("Montserrat", Font.PLAIN, 14));
        txtAuthor.setBounds(385, 480, 200, 20);
        add(txtAuthor);
        
        JLabel lblStockCount = new JLabel("Stock Count:");
        lblStockCount.setFont(new Font("Montserrat", Font.PLAIN, 14));
        lblStockCount.setBounds(610, 478, 125, 20);
        add(lblStockCount);
        
        txtStockCount.setFont(new Font("Montserrat", Font.PLAIN, 14));
        txtStockCount.setBounds(700, 480, 65, 20);
        add(txtStockCount);
        
        JLabel lblGenre = new JLabel("Genre:");
        lblGenre.setFont(new Font("Montserrat", Font.PLAIN, 14));
        lblGenre.setBounds(50, 518, 125, 20);
        add(lblGenre);
        
        txtGenre.setFont(new Font("Montserrat", Font.PLAIN, 14));
        txtGenre.setBounds(100, 520, 200, 20);
        add(txtGenre);
        
        JLabel lblPageCount = new JLabel("Page Count:");
        lblPageCount.setFont(new Font("Montserrat", Font.PLAIN, 14));
        lblPageCount.setBounds(330, 518, 85, 20);
        add(lblPageCount);
        
        txtPageCount.setFont(new Font("Montserrat", Font.PLAIN, 14));
        txtPageCount.setBounds(420, 520, 165, 20);
        add(txtPageCount);
        
        btnSave.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnSave.setBounds(610, 510, 155, 40);
        add(btnSave);
        
    }

    private void bindEvents() {
        btnSearch.addActionListener(e -> searchBooks());
        btnSave.addActionListener(e -> saveBook());
        btnShowAll.addActionListener(e -> showAllBooks());
        btnSort.addActionListener(e -> sortBooks());
        listBooks.addListSelectionListener(e -> selectBookFromList());
        rbAdd.addActionListener(e -> rbAddAction());
        rbEdit.addActionListener(e -> enableTextFields(false));
    }

    private void searchBooks() {
    	String searchText = txtSearch.getText().trim().toLowerCase();
    	String searchBy = (String) cbSearchOptions.getSelectedItem();
    	 
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter text to search.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        var currentResults = bookLibrary.searchByCriteria(searchText, searchBy);
        listModel.clear();
        currentResults.forEach(listModel::addElement);
        clearTextFields();
        
    }
    
    private void saveBook() {
    	if(rbAdd.isSelected()) {
    		addBook();
    	} else {
    		editBook();
    	} showAllBooks();
    }
    
    private void rbAddAction() {
    	enableTextFields(true);
    	clearTextFields();
    }

    private void addBook() {
        try {
        	if (!(rbAdd.isSelected())) {
            	JOptionPane.showMessageDialog(this, "Add button is not selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        	
        	String name = txtName.getText().trim();
            String author = txtAuthor.getText().trim();
            String genre = txtGenre.getText().trim();
            int pageCount = Integer.parseInt(txtPageCount.getText().trim());  // Parsing may throw NumberFormatException
            int stockCount = Integer.parseInt(txtStockCount.getText().trim());

            TreeNode newNode = new TreeNode(name, author, genre, pageCount, stockCount);
            boolean added = bookLibrary.insert(newNode);
            
            if (added) {
                bookList.insertEnd(name, author, genre, pageCount, stockCount);  // Assume this method manages adding to a visual component or another structure
                JOptionPane.showMessageDialog(this, "Book added successfully.");
                clearTextFields(); // Optionally clear fields after successful addition
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add book (duplicate may exist).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for page count and stock count.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editBook() {
        String selectedBookName = listBooks.getSelectedValue();
        if (selectedBookName == null) {
            JOptionPane.showMessageDialog(this, "No book selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!(rbEdit.isSelected())) {
        	JOptionPane.showMessageDialog(this, "Edit button is not selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        TreeNode bookNode = bookLibrary.treeNodeSearch(selectedBookName.split(" - ")[0]); // Assuming the name is the first part
        if (bookNode == null) {
            JOptionPane.showMessageDialog(this, "Book not found in the library.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            bookNode.setStockCount(Integer.parseInt(txtStockCount.getText().trim())); // Update stock count

            // Update the list model to reflect changes
            int index = listBooks.getSelectedIndex();
            if (index != -1) {
                listModel.set(index, bookNode.toString()); // Assumes TreeNode has a suitable toString method
            }

            JOptionPane.showMessageDialog(this, "Book updated successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for page count and stock count.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAllBooks() {
        java.util.List<String> books = bookLibrary.inorderList();
        listModel.clear();
        books.forEach(listModel::addElement);
        clearTextFields();
    }

    private void sortBooks() {
        String sortBy = (String) cbSortOptions.getSelectedItem();
        java.util.List<String> sortedBooks = bookLibrary.sortBy(sortBy);
        listModel.clear();
        sortedBooks.forEach(listModel::addElement);
        clearTextFields();
    }

    private void selectBookFromList() {
    	if(rbEdit.isSelected()) {
    		String selectedBook = listBooks.getSelectedValue();
            TreeNode bookNode = bookLibrary.treeNodeSearch(selectedBook.split(" - ")[0]);
            if (bookNode != null) {
                txtName.setText(bookNode.getName());
                txtAuthor.setText(bookNode.getAuthor());
                txtGenre.setText(bookNode.getGenre());
                txtPageCount.setText(String.valueOf(bookNode.getPageCount()));
                txtStockCount.setText(String.valueOf(bookNode.getStockCount()));
            }
    	}
        
    }
    
    private void setupListSelectionListener() {
    	if(rbEdit.isSelected()) {
	        listBooks.addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) { // Prevents double events
	                String selectedValue = listBooks.getSelectedValue();
	                if (selectedValue != null && !selectedValue.isEmpty()) {
	                    String[] parts = selectedValue.split(" - ");
	                    if (parts.length >= 3) {
	                        txtName.setText(parts[0]);
	                        txtAuthor.setText(parts[1]);
	                        txtGenre.setText(parts[2]);
	                        txtPageCount.setText(parts[3]);
	                        txtStockCount.setText(parts[4]);
	                    }
	                }
	            }
	        });
    	}
    }
    private void clearTextFields() {
        txtName.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");
        txtPageCount.setText("");
        txtStockCount.setText("");
    }

    private void enableTextFields(boolean enable) {
        txtName.setEnabled(enable);
        txtAuthor.setEnabled(enable);
        txtGenre.setEnabled(enable);
        txtPageCount.setEnabled(enable);
    }
    
    private void createDatabase() {
    	bookLibrary.insert(book1);
    	bookLibrary.insert(book2);
    	bookLibrary.insert(book3);
    	bookLibrary.insert(book4);
    	bookLibrary.insert(book5);
    	bookLibrary.insert(book6);
    	bookLibrary.insert(book7);
    	bookLibrary.insert(book8);
    	bookLibrary.insert(book9);
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
            new LibraryGUI().setVisible(true);
        });
    	System.out.println("a");
    }
}
