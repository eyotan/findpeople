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
    private static Connection c2;
    private static String url1 = url+user+"&"+pass+"&"+ssl;
    private static String insertpeoples = "INSERT INTO peoples (id,surname,name,parentname) VALUES(?,?,?,?)";
    private static String insertcity = "INSERT INTO city (id,cityname) VALUES(?,?)";
    private static String insertcars = "INSERT INTO cars(id,car) VALUES(?,?)";
    private static String insertadmins = "INSERT INTO admins(id,login,pass) VALUES(?,?,?)";

    public static void main(String[] args){
        addToPeoples(insertpeoples, insertcity, insertcars);
        addAdmins();
    }

    private static void addToPeoples(String insertpeoples, String insertcity, String insertcars){
        try{
            Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection(url1);
            c1 = DriverManager.getConnection(url1);
            c2 = DriverManager.getConnection(url1);

            PreparedStatement statementpeople = c.prepareStatement(insertpeoples);
            PreparedStatement statementcity = c1.prepareStatement(insertcity);
            PreparedStatement statementcars = c2.prepareStatement(insertcars);

                for (int i = 0; i < 10; i++) {
                    People people = new People();
                    statementpeople.setObject(1, people.id);
                    statementpeople.setString(2, people.surname+i);
                    statementpeople.setString(3, people.name+i);
                    statementpeople.setString(4, people.parentname+i);
                    statementcity.setObject(1, people.id);
                    statementcity.setString(2, people.cityname+i);
                    statementpeople.addBatch();
                    statementcity.addBatch();
                    System.out.println("INSERT PEOPLE "+i);
                    for (int j = 0; j <= i; j++){
                        statementcars.setObject(1,people.id);
                        statementcars.setString(2, people.car+j);
                        statementcars.addBatch();
                    }
                }
                statementpeople.executeBatch();
                statementcity.executeBatch();
                statementcars.executeBatch();

                statementpeople.close();
                statementcity.close();
                statementcars.close();

                c.close();
                c1.close();
                c2.close();


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
        private String name = "Иван";
        private String parentname = "Иванович";
        private String cityname = "Омск";
        private String car = "Мазда";
    }
}
