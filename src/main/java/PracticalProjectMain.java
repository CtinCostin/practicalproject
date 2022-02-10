import com.sda.practicalproject.dao.AuthorDao;
import com.sda.practicalproject.dao.BookDao;
import com.sda.practicalproject.dao.ReviewDao;
import com.sda.practicalproject.service.AuthorService;
import com.sda.practicalproject.service.BookService;
import com.sda.practicalproject.service.ReviewService;
import com.sda.practicalproject.ui.AppMenuUI;
import com.sda.practicalproject.ui.PracticalProjectAuthorUI;
import com.sda.practicalproject.ui.PracticalProjectBookUI;
import com.sda.practicalproject.ui.PracticalProjectReviewUI;
import org.hibernate.SessionFactory;

public class PracticalProjectMain {

    public static void main(String[] args) {

        SessionSettings sessionSettings = new SessionSettings();
        SessionFactory sessionFactory = sessionSettings.getSessionFactory();

        BookDao bookDao = new BookDao(sessionFactory);
        AuthorDao authorDao = new AuthorDao(sessionFactory);
        ReviewDao reviewDao = new ReviewDao(sessionFactory);

        AuthorService authorService = new AuthorService(authorDao, bookDao);
        BookService bookService = new BookService(bookDao, authorDao);
        ReviewService reviewService = new ReviewService(reviewDao, bookDao);

        PracticalProjectAuthorUI practicalProjectAuthorUI = new PracticalProjectAuthorUI(authorService, bookService);
        PracticalProjectBookUI practicalProjectBookUI = new PracticalProjectBookUI(bookService, authorService);
        PracticalProjectReviewUI practicalProjectReviewUI = new PracticalProjectReviewUI(reviewService, bookService);

        AppMenuUI appMenuUI = new AppMenuUI(practicalProjectAuthorUI, practicalProjectBookUI, practicalProjectReviewUI);

        appMenuUI.startApp();


    }
}
