
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseElements 
{
    String jdbcDriver ="com.mysql.jdbc.Driver";
    String dbmsUrl="jdbc:mysql://localhost/";
    String dbmsUsername="root";
    String dbmsPassword="password";
    String dbmsName="MatrixCalculator";
    
    public void registerDriver(String jdbcDriverUrl) throws ClassNotFoundException
    {
        Class.forName(jdbcDriverUrl);
    }
    public Connection openConnection(String dbUrl, String username, String password) throws SQLException
    {
        return (DriverManager.getConnection(dbUrl, username, password));
    }
    public void createDB(String dbUrl, String username, String password, String dbName) throws SQLException
    {
        (openConnection(dbUrl,username,password)).createStatement().executeUpdate("CREATE DATABASE IF NOT EXISTS "+ dbName);
    }
    public Connection selectDB(String dbUrlwithName, String username, String password) throws SQLException
    {
       return (DriverManager.getConnection(dbUrlwithName, username, password));
    }
    public void deleteDB(String dbUrl, String username, String password, String dbName) throws SQLException
    {
        (openConnection(dbUrl,username,password)).createStatement().executeUpdate("DROP DATABASE "+ dbName);
    }
    
    public void createTable(String jdbcDBMSUrlWithName,String Username,String Password, String TableCreateString) throws SQLException
    {
      selectDB(jdbcDBMSUrlWithName,Username,Password).createStatement().executeUpdate(TableCreateString);
    }
    public void InsertTableData(String jdbcDBMSUrlWithName,String Username,String Password, String DataEntryString) throws SQLException
    {
      selectDB(jdbcDBMSUrlWithName,Username,Password).createStatement().executeUpdate(DataEntryString);
    }
    public void deleteTable(String jdbcDBMSUrlWithName,String Username,String Password, String TableDeleteString) throws SQLException
    {
      selectDB(jdbcDBMSUrlWithName,Username,Password).createStatement().executeUpdate(TableDeleteString);
    }
    
    public ResultSet getTableData(String jdbcDBMSUrlWithName,String Username,String Password, String TableRetreiveString) throws SQLException
    {
      return selectDB(jdbcDBMSUrlWithName,Username,Password).createStatement().executeQuery(TableRetreiveString);
    }
    
    public void EditTableData(String jdbcDBMSUrlWithName,String Username,String Password, String DataEditString) throws SQLException
    {
      selectDB(jdbcDBMSUrlWithName,Username,Password).createStatement().executeUpdate(DataEditString);
    }
    public void DeleteTableData(String jdbcDBMSUrlWithName,String Username,String Password, String DataDeleteString) throws SQLException
    {
      selectDB(jdbcDBMSUrlWithName,Username,Password).createStatement().executeUpdate(DataDeleteString);
    }
    

   public String createTableStatement(String tableName, String[][] tableHeadings, String PrimaryKey)
   {
       String Statement="CREATE TABLE IF NOT EXISTS "+tableName+" (";
       for(int i=0;i<tableHeadings[0].length;i++)
       {
           if(PrimaryKey.equals("")==true)
           {
                if(i==tableHeadings[0].length-1)
                {
                    Statement=Statement+tableHeadings[0][i]+" "+tableHeadings[1][i]+")";
                }
                else
                {
                    Statement=Statement+tableHeadings[0][i]+" "+tableHeadings[1][i]+", ";
                }
           }
           else
               Statement=Statement+tableHeadings[0][i]+" "+tableHeadings[1][i]+", ";
           
       }
       if(PrimaryKey.equals("")==false)    
       Statement=Statement+"PRIMARY KEY ( "+PrimaryKey+" )) ";
       return Statement;
   }
   public String insertTableStatement(String tableName, String Data[],int rowNo, int columnNo)
   {
        String Statement;
        Statement="INSERT INTO "+tableName+" VALUES (";
        int c=0;
        for(int i=0;i<rowNo;i++)
        {
            for(int j=0;j<columnNo;j++)
            {
                if(j!=columnNo-1)
                    Statement=Statement+Data[c++]+",";
                else
                    Statement=Statement+Data[c++];
            }
            if(i!=rowNo-1)
                Statement=Statement+"),(";
            else
                Statement=Statement+")";
        }
        return Statement;
   }
   public String insertTableStatement(String tableName, String Data[])
   {
        String Statement;
        Statement="INSERT INTO "+tableName+" VALUES (";
        for(int i=0;i<Data.length;i++)
        {
            Statement=Statement+Data[i];
            if(i!=Data.length-1)
                Statement=Statement+",";
        }
        Statement=Statement+")";
        return Statement;
   }
   public String editTableStatement(String tableName, String Data[][], String Clause)
   {
        String Statement;
        Statement="UPDATE "+tableName+" SET ";
        for(int i=0;i<Data[0].length;i++)
        {
            Statement=Statement+Data[0][i]+" = "+Data[1][i];
            if(i!=Data[0].length-1)
                Statement=Statement+", ";
        }
        Statement=Statement+" "+Clause;
        return Statement;
   }
   public String getTableDataStatement(String tableName, String[] tableHeadings, String Clause)
   {
        String Statement;
        Statement="SELECT ";
        for(int i=0;i<tableHeadings.length;i++)
        {
            Statement=Statement+tableHeadings[i]; 
            if(i!=tableHeadings.length-1)
                Statement=Statement +", ";
        }
        Statement=Statement+" FROM "+tableName+" "+Clause;
        return Statement;
   }
   public String createClauseStatement(String ClauseType, String[][] Arg)
   {
       String Statement=ClauseType+" ";
       for(int i=0;i<Arg[0].length;i++)
       {
            Statement=Statement+Arg[0][i]+" "+Arg[1][i]+" "+"'"+Arg[2][i]+"'";
            if(i!=Arg[0].length-1)
                Statement=Statement+", ";
       }
       return Statement;
   }
}
