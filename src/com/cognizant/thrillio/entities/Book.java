package com.cognizant.thrillio.entities;

import com.cognizant.thrillio.constants.BookGenre;
import com.cognizant.thrillio.partner.Shareable;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Book extends Bookmark implements Shareable {
    private int publicationYear;
    private String publisher;
    private String[] authors;
    private BookGenre genre;
    private double amazonRating;

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public double getAmazonRating() {
        return amazonRating;
    }

    public void setAmazonRating(double amazonRating) {
        this.amazonRating = amazonRating;
    }

    @Override
    public String toString() {
        return  "Book{" + super.toString() +
                " publicationYear=" + publicationYear +
                ", publisher='" + publisher + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", genre='" + genre + '\'' +
                ", amazonRating=" + amazonRating +
                "} ";
    }

    @Override
    public boolean isKidFriendlyEligible() {
        if(getGenre().equals(BookGenre.PHILOSOPHY) || getGenre().equals(BookGenre.SELF_HELP)) {
            return false;
        }
        return true;
    }

    @Override
    public String getItemData() {
        StringBuilder sb = new StringBuilder();
        sb.append("<item>");
            sb.append("<type>Book</type>");
            sb.append("<title>").append(getTitle()).append("</title>");
            sb.append("<publication_year>").append(publicationYear).append("</publication_year>");
            sb.append("<publisher>").append(publisher).append("</publisher>");
            sb.append("<authors>").append(StringUtils.join(authors, ",")).append("</authors>");
//          sb.append("<authors>").append(Arrays.toString(getAuthors())).append("</authors>");
            sb.append("<genre>").append(genre).append("</genre>");
            sb.append("<amazon_rating>").append(amazonRating).append("</amazon_rating>");
        sb.append("</item>");
        return sb.toString();
    }
}
