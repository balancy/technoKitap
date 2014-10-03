package kz.technovision.technokitap;

/**
 *
 * @author balancy
 */
public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        Book book = new Book("Қазақ", "D:\\books\\");
        //packagify
        book.packagify();
        //imagify
        book.imagify("D:\\example.pdf");
        book.copyBig2Thumbs();
        //rename
        book.rename(book.big);
        book.rename(book.thumbs);
        //resize
        book.resize(book.big);
        book.resize(book.thumbs);
        //encode
        book.xor(book.big);
    }

}
