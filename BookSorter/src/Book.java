public class Book implements Comparable<Book>{
    private String bookName;
    private int pageNumber;
    private String authorName;
    private String publicationDate;

    public Book(String bookName, int pageNumber, String authorName, String publicationDate) {
        this.bookName = bookName;
        this.pageNumber = pageNumber;
        this.authorName = authorName;
        this.publicationDate = publicationDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public int compareTo(Book o) {
        return this.getBookName().compareTo(o.getBookName());
    }

    @Override
    public String toString() {
        return "Kitap Adı: " + this.bookName + ", Sayfa Sayısı: " + this.pageNumber +
                ", Yazar: " + this.authorName + ", Yayın Tarihi: " + this.publicationDate;
    }
}
