package intro.bookservice.dto;

import java.util.Arrays;

public record BookSearchParameters (
        String[] title,
        String[] author,
        String[] isbn){
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSearchParameters that = (BookSearchParameters) o;
        return Arrays.equals(title, that.title)
                && Arrays.equals(author, that.author)
                && Arrays.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(title);
        result = 31 * result + Arrays.hashCode(author);
        result = 31 * result + Arrays.hashCode(isbn);
        return result;
    }

    @Override
    public String toString() {
        return "BookSearchParameters{"
                + "title=" + Arrays.toString(title)
                + ", author=" + Arrays.toString(author)
                + ", isbn=" + Arrays.toString(isbn)
                + '}';
    }
}
