package tugas2.models;

/**
 * Book model
 * self reminder: similar to mongoose model
 */
public class Book {
	private long bookID;
	private String bookTitle;
	private String bookAuthor;

	/**
	 * Gets the book ID
	 * @return bookID
	 */
	public long getBookID() {
		return bookID;
	}

	/**
	 * Sets the book ID
	 * @param id the `Book` id
	 */
	public void setBookID(long id) {
		this.bookID = id;
	}

	/**
	 * Gets the book title
	 * @return bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * Sets the book title
	 * @param title the `Book` title
	 */
	public void setBookTitle(String title) {
		this.bookTitle = title;
	}

	/**
	 * Gets the book author
	 * @return bookAuthor
	 */
	public String getBookAuthor() {
		return bookAuthor;
	}

	/**
	 * Sets the book author
	 * @param author the `Book` author
	 */
	public void setBookAuthor(String author) {
		this.bookAuthor = author;
	}
}
