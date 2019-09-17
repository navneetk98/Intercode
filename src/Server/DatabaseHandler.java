package Server;

import Client.Credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
    private static Connection connection;

    static {
        connection = DatabaseConnectivity.connection;
        System.out.println("Connection type in db handler: "+connection);
    }

     static boolean verifyCredentials(Credentials credentials) {
        String uid=credentials.getUid();
        String password=credentials.getPassword();
        ResultSet resultSet;

        if(credentials.getToggle().compareTo("1")==0)//login as interviewer
        {
            System.out.println("login as interviewer");
            String query1="Select uid,password from Interviewer where uid="+"\""+uid+"\"";
            PreparedStatement preparedStatement= null;
            try {
                preparedStatement = connection.prepareStatement(query1);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next())
                {
                    System.out.println(uid+" found ");
                    if(resultSet.getString("password").compareTo(password)==0) {
                        System.out.println("login successful as an interviewer");
                        return true;
                    }
                }
                else
                {
                    System.out.println("uid not found");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else//login as candidate
        {
            System.out.println("login as candidate");
            String query2="Select uid,password from Candidate where uid="+uid;
            try
            {
                PreparedStatement preparedStatement=connection.prepareStatement(query2);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next())
                {
                    System.out.println(uid+" found ");
                    if(resultSet.getString("password").compareTo(password)==0)
                    {
                        System.out.println("login successful as an candidate");
                        return true;
                    }

                }
                else {
                    System.out.println("user id not found");
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    static boolean registerCredentials(Credentials credentials) {
        String uid=credentials.getUid();
        String password=credentials.getPassword();
        String name=credentials.getName();


        if(credentials.getToggle().compareTo("1")==0)
        {
            String query1="INSERT INTO Interviewer values(?,?,?)";
            try {
                PreparedStatement preparedStatement=connection.prepareStatement(query1);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,uid);
                preparedStatement.setString(3,password);
                int f=preparedStatement.executeUpdate();
                return  true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        else
        {
            String branch=credentials.getBranch();
            double cpi=Double.parseDouble(credentials.getCpi());
            String query2="INSERT INTO Candidate values(?,?,?,?,?)";
            try {
                PreparedStatement preparedStatement=connection.prepareStatement(query2);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,uid);
                preparedStatement.setString(3,password);
                preparedStatement.setString(4,branch);
                preparedStatement.setDouble(5,cpi);
                preparedStatement.executeUpdate();
                return true;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

}
