package user;
import java.sql.*;
import java.io.*;

class insertImage {
   
   public static void main(String[] args) throws SQLException {
   // declare a connection by using Connection interface 
   Connection connection = null;
   /* Create string of connection url within specified format with machine 
   name, port number and database name. Here machine name id 10.179.0.5 
   and database name is mahendra. */
//   String connectionURL = "jdbc:oracle:thin:@10.179.0.5:1523:orcl";
//      String connectionURL = "jdbc:postgresql:@10.179.0.5:1523:orcl";
         String connectionURL = "jdbc:postgresql:@localhost:5432/MegVat";


  /*declare a resultSet that works as a table resulted by execute a specified 
    sql query. */
  ResultSet rs = null;
  // Declare prepare statement.
  PreparedStatement psmnt = null;
  // declare FileInputStream object to store binary stream of given image.
  FileInputStream fis;
  try {
 // Load JDBC driver "com.mysql.jdbc.Driver"
// Class.forName("oracle.jdbc.OracleDriver").newInstance();
 Class.forName("org.postgresql.Driver").newInstance();


/* Create a connection by using getConnection() method that takes 
parameters of string type connection url, user name and password to 
connect to database. */
connection = DriverManager.getConnection(connectionURL, "vat", "vat");
// create a file object for image by specifying full path of image as parameter.
File image = new File("D:/070909/ewaybill/web/signseal/seal2.jpg");
File image1 = new File("D:/070909/ewaybill/web/signseal/sign2.jpg");
/* prepareStatement() is used for create statement object that is 
used for sending sql statements to the specified database. */
psmnt = connection.prepareStatement
("insert into vat.circle_image(circle_cd,st_nm,seal,sign) "+ "values(?,?,?,?)");
psmnt.setString(1,"02");
psmnt.setString(2,"Mr. B. D. Nath");
//psmnt.setString(4,"170200108846469");
//psmnt.setString(5,"17020010018");
fis = new FileInputStream(image);
psmnt.setBinaryStream(3, (InputStream)fis,(int)(image.length()));
fis = new FileInputStream(image1);
psmnt.setBinaryStream(4, (InputStream)fis,(int)(image1.length()));
/* executeUpdate() method execute specified sql query. Here this query 
 insert data and image from specified address. */ 
int s = psmnt.executeUpdate();
if(s>0) {
  System.out.println("Uploaded successfully !");
 }
else {
System.out.println("unsucessfull to upload image.");
  }
}

// catch if found any exception during rum time.
     catch (Exception ex) {
System.out.println("Found some error : "+ex);
}
finally {
// close all the connections.
connection.close();
psmnt.close();
  }
 }
}