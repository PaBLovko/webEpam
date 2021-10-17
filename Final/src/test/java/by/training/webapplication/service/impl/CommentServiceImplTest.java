package by.training.webapplication.service.impl;

import by.training.webapplication.bean.Comment;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.service.exception.ValidatorException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommentServiceImplTest {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @DataProvider
    public Object[][] forFindCorrectLimitCommentOnPage() {
        List<Comment> comments1 = new ArrayList<>(Arrays.asList(new Comment(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk", "8-029-965-31-32",
                        "Always friendly staff"), LocalDateTime.parse("2021-01-03T11:00:00"), "dishes always wonderful"),
                new Comment(2, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk", "8-029-598-23-31",
                        "Live music complemented the festive evening well"), LocalDateTime.parse("2021-01-02T13:00:00"), "excellently")));
        List<Comment> comments2 = new ArrayList<>(Collections.singletonList(new Comment(1, new User(3,"user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well"), LocalDateTime.parse("2021-01-01T11:00:00"), "tasty")));
        int start1 = 0;
        int start2 = 2;
        int amount = 2;
        return new Object[][]{
                {comments1, start1, amount},
                {comments2, start2, amount}
        };
    }
    @Test(dataProvider = "forFindCorrectLimitCommentOnPage")
    public void testFindCorrectLimitCommentOnPage(List<Comment> comments, int start, int amount) throws ServiceException {
        Assert.assertEquals(serviceFactory.getCommentService().findLimitComment(start, amount), comments);
    }

    @DataProvider
    public Object[][] forFindWrongLimitCommentOnPage() {
        List<Comment> comments1 = new ArrayList<>(Arrays.asList(new Comment(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk", "8-029-965-31-32",
                        "Always friendly staff"), LocalDateTime.parse("2021-01-03T11:00:00"), "dishes always wonderful"),
                new Comment(2, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk", "8-029-598-23-31",
                        "Live music complemented the festive evening well"), LocalDateTime.parse("2021-01-02T13:00:00"), "excellently")));
        List<Comment> comments2 = new ArrayList<>(Collections.singletonList(new Comment(1, new User(3,"user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well"), LocalDateTime.parse("2021-01-01T11:00:00"), "tasty")));
        int start1 = 1;
        int start2 = 3;
        int amount = 2;
        return new Object[][]{
                {comments1, start1, amount},
                {comments2, start2, amount}
        };
    }
    @Test(dataProvider = "forFindWrongLimitCommentOnPage")
    public void testWrongFindWrongLimitCommentOnPage(List<Comment> comments, int start, int amount) throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getCommentService().findLimitComment(start, amount), comments);
    }

    @DataProvider
    public Object[][] forFindCorrectCommentPageAmount() {
        int commentsOnPage1 = 1;
        int commentsOnPage2 = 2;
        int commentsOnPage3 = 3;
        int pageAmount1 = 3;
        int pageAmount2 = 2;
        int pageAmount3 = 1;
        return new Object[][]{
                {commentsOnPage1, pageAmount1},
                {commentsOnPage2, pageAmount2},
                {commentsOnPage3, pageAmount3}
        };
    }

    @Test(dataProvider = "forFindCorrectCommentPageAmount")
    public void testFindCorrectCommentPageAmount(int commentsOnPage, int pageAmount) throws ServiceException {
        Assert.assertEquals(serviceFactory.getCommentService().findCommentPageAmount(commentsOnPage), pageAmount);
    }

    @DataProvider
    public Object[][] forFindWrongCommentPageAmount() {
        int commentsOnPage1 = 1;
        int commentsOnPage2 = 2;
        int commentsOnPage3 = 3;
        int pageAmount1 = 1;
        int pageAmount2 = 1;
        int pageAmount3 = 2;
        return new Object[][]{
                {commentsOnPage1, pageAmount1},
                {commentsOnPage2, pageAmount2},
                {commentsOnPage3, pageAmount3}
        };
    }

    @Test(dataProvider = "forFindWrongCommentPageAmount")
    public void testFindWrongCommentPageAmount(int commentsOnPage, int pageAmount) throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getCommentService().findCommentPageAmount(commentsOnPage), pageAmount);
    }

    @DataProvider
    public Object[][] forDeleteComment() {
        List<Comment> comments1 = new ArrayList<>(Arrays.asList(new Comment(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk", "8-029-965-31-32",
                        "Always friendly staff"), LocalDateTime.parse("2021-01-03T11:00:00"), "dishes always wonderful"),
                new Comment(1, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"), LocalDateTime.parse("2021-01-01T11:00:00"), "tasty")));
        List<Comment> comments2 = new ArrayList<>(Collections.singletonList(new Comment(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk", "8-029-965-31-32",
                "Always friendly staff"), LocalDateTime.parse("2021-01-03T11:00:00"), "dishes always wonderful")));
        int id1 = 2;
        int id2 = 1;
        int start = 0;
        int amount = 3;
        return new Object[][]{
                {comments1, id1, start, amount},
                {comments2, id2, start, amount}
        };
    }

    @Test(priority = 2, dataProvider = "forDeleteComment")
    public void testDeleteComment(List<Comment> comments, int id, int start, int amount) throws ServiceException {
        serviceFactory.getCommentService().deleteComment(id);
        Assert.assertEquals(serviceFactory.getCommentService().findLimitComment(start, amount), comments);
    }
    @DataProvider
    public Object[][] forSaveComment() {
        List<Comment> comments1 = new ArrayList<>(Arrays.asList(new Comment(4, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"), LocalDateTime.parse("2021-01-01T11:00:00"), "tasty"),
                new Comment(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk", "8-029-965-31-32",
                        "Always friendly staff"), LocalDateTime.parse("2021-01-03T11:00:00"), "dishes always wonderful")));
        List<Comment> comments2 = new ArrayList<>(Arrays.asList(new Comment(5, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk", "8-029-598-23-31",
                        "Live music complemented the festive evening well"), LocalDateTime.parse("2021-01-02T13:00:00"), "excellently"),
                new Comment(4, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"), LocalDateTime.parse("2021-01-01T11:00:00"), "tasty"),
                new Comment(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk", "8-029-965-31-32",
                        "Always friendly staff"), LocalDateTime.parse("2021-01-03T11:00:00"), "dishes always wonderful")));
        int userId = 3;
        LocalDateTime localDateTime1 = LocalDateTime.parse("2021-01-01T11:00:00");
        LocalDateTime localDateTime2 = LocalDateTime.parse("2021-01-02T13:00:00");
        String review1 = "tasty";
        String review2 = "excellently";
        int start = 0;
        int amount = 3;
        return new Object[][]{
                {comments1, userId, localDateTime1, review1, start, amount},
                {comments2, userId, localDateTime2, review2, start, amount}
        };
    }

    @Test(priority =3, dataProvider = "forSaveComment")
    public void testSaveComment(List<Comment> comments, int userId, LocalDateTime localDateTime, String review, int start, int amount) throws ServiceException, ValidatorException {
        serviceFactory.getCommentService().save(userId, localDateTime, review);
        Assert.assertEquals(serviceFactory.getCommentService().findLimitComment(start, amount), comments);
    }
    @AfterTest
    void tearDown() throws SQLException, IOException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost:3306/test_restaurant_db";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "9142danii");
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/resources/sql/test_database.sql"));
        sr.runScript(reader);
        con.close();
        reader.close();
        sr.closeConnection();
    }
}

