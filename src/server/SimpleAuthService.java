package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService{

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private class UserData {
        String login;
        String password;
        String nickName;

        public UserData(String login, String password, String nickName) {
            this.login = login;
            this.password = password;
            this.nickName = nickName;

        }
    }

    List<UserData> users;

    public SimpleAuthService() {
        users = new ArrayList<>();
//========== Cоздаю таблицу базы данных ==  в sqlLite созданная таблица не подключается ========
//        connect();
//        try {
//            stmt.executeUpdate("CREATE TABLE UserList1 (" +
//                    " Num INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    " Login TEXT," +
//                    " Password TEXT," +
//                    " Nick TEXT)");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            stmt.executeUpdate("INSERT INTO UserList1 (Num, Login, Password, Nick) " +
//                    "VALUES (1, 'qwe', 'qwe', 'qwe')");
//            stmt.executeUpdate("INSERT INTO UserList1 (Num, Login, Password, Nick) " +
//                    "VALUES (2, 'asd', 'asd', 'asd')");
//            stmt.executeUpdate("INSERT INTO UserList1 (Num, Login, Password, Nick) " +
//                    "VALUES (3, 'zxc', 'zxc', 'zxc')");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        disconnect();
//    }
//===============================================


//        for (int i = 0; i < 10; i++) {
//            users.add(new UserData("login" + i, "pass" + i, "nick" + i));
//        }
connect();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserList1");
            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString("name")
//                        + " " + rs.getString("score"));
            users.add(new UserData(rs.getString("Login"), rs.getString("Password"), rs.getString("Nick")));
//            users.add(new UserData("asd", "asd", "asd"));
//            users.add(new UserData("zxc", "zxc", "zxc"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
disconnect();
    }
//=================================================
    @Override
    public String getNickByLoginAndPassword(String login, String password) {
        for(UserData user : users){
            if(user.login.equals(login) && user.password.equals(password)){
                return user.nickName;
            }
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        for (UserData user : users) {
            if(user.login.equals(login) || user.nickName.equals(nickname)){
                return false;
            }
        }
        users.add(new UserData(login, password, nickname));
        return true;
    }
}
