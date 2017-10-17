package Databases;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.UUID;

public class DatabaseInsert {

    private static String url = "jdbc:postgresql://humaninweb.ru/findpeoples?";
    private static String user = "user=postgresreaduser";
    private static String pass = "password=postgresreaduser";
    private static String ssl = "ssl=false";
    private static Connection c;
    private static Connection c1;
    private static String url1 = url+user+"&"+pass+"&"+ssl;
    private static String insertpeoples = "INSERT INTO peoples (id,surname,name1,name2,city) VALUES(?,?,?,?,?)";
    private static String insertcars = "INSERT INTO cars(id,car) VALUES(?,?)";
    private static String insertadmins = "INSERT INTO admins(id,login,pass) VALUES(?,?,?)";

    public static void main(String[] args){
        addToPeoples(insertpeoples, insertcars);
        addAdmins();
    }

    private static void addToPeoples(String insertpeoples, String insertcars){
        try{
            Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection(url1);
            c1 = DriverManager.getConnection(url1);

            PreparedStatement statement = c.prepareStatement(insertpeoples);
            PreparedStatement statement1 = c1.prepareStatement(insertcars);

                for (int i = 0; i < 10; i++) {
                    People people = new People();
                    statement.setObject(1, people.id);
                    statement.setString(2, people.surname+i);
                    statement.setString(3, people.name1+i);
                    statement.setString(4, people.name2+i);
                    statement.setString(5, people.city+i);
                    statement.addBatch();
                    System.out.println("INSERT PEOPLE "+i);
                    for (int j = 0; j < i; j++){
                        statement1.setObject(1,people.id);
                        statement1.setString(2, people.car+j);
                        statement1.addBatch();
                    }
                }
                statement.executeBatch();
                statement1.executeBatch();
                statement.close();
                statement1.close();
                c.close();
                c1.close();


        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    private static void addAdmins(){
        String passAdmin = DigestUtils.md5Hex("passAdmin");
        String passUser1 = DigestUtils.md5Hex("passUser");
        String passUser2 = DigestUtils.md5Hex("passUserAdmin");

        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("INSERT USERS ");
            c = DriverManager.getConnection(url1);

            PreparedStatement statement = c.prepareStatement(insertadmins);

                statement.setObject(1, UUID.randomUUID());
                statement.setString(2, "admin");
                statement.setString(3, passAdmin);
                statement.addBatch();

                statement.setObject(1, UUID.randomUUID());
                statement.setString(2, "user");
                statement.setString(3, passUser1);
                statement.addBatch();

                statement.setObject(1, UUID.randomUUID());
                statement.setString(2, "useradmin");
                statement.setString(3, passUser2);
                statement.addBatch();

            statement.executeBatch();
            statement.close();
            c.close();
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    private static class People {
        private UUID id = UUID.randomUUID();
        private String surname = "Иванов";
        private String name1 = "Иван";
        private String name2 = "Иванович";
        private String city = "Омск";
        private String car = "Мазда";
    }
}