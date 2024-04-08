package cupcake.languages.english;

import static cupcake.languages.english.Console_Print_Prefix.DATABASE;
import static cupcake.languages.english.Error_Prefix.DATABASE_ERROR;

public interface Lang_Crud
{
    
    //Locations
    String METH_CREATE = "at Create: ";
    String METH_READ_ALL = "at readAll: ";
    String METH_READ_SINGLE = "at readSingle: ";
    String METH_UPDATE = "at update: ";
    String METH_DELETE = "at delete: ";
    
    //Generic-----------------------------------
    static String sqlErrorUser()
    {
        return "Fatal database exception. Is the Database down? ";
    }
    
    
    
    //Create----------------------------------------
    
    static String createErrorUser_queryResult()
    {
        return DATABASE_ERROR + "Inserting into database returned a query result, your input was likely not inserted into the database correctly";
    }
    
    static String createErrorSys_queryResult( Object object )
    {
        return DATABASE_ERROR + METH_CREATE + "Insert CRUD had a return set for DTO=" + object;
    }
    
    static String createErrorUser_noRowsAdded()
    {
        return DATABASE_ERROR + "Nothing was added to database";
    }
    
    static String createErrorSys_noRowsAdded( Object object )
    {
        return DATABASE_ERROR + METH_CREATE + "No rows were affected, nothing was added for DTO=" + object;
    }
    
    static String createErrorUser_rowsAdded()
    {
        return DATABASE_ERROR + "Created multiple copies in database";
    }
    
    static String createErrorSys_rowsAdded( Object object )
    {
        return DATABASE_ERROR + METH_CREATE + "More than 1 row affected for DTO=" + object;
    }
    
    static void createRowsConsoleMessage( Object object )
    {
        System.out.println( DATABASE + "Added row for DTO=" + object );
    }
    
    static String createErrorUser_noIdKeyReturned()
    {
        return DATABASE_ERROR + METH_CREATE + "Could not get the new ID from database";
    }
    
    static String createErrorSys_noIdKeyReturned( Object object )
    {
        return DATABASE_ERROR + METH_CREATE + "Failed to retrieve generated key (ID) for DTO=" + object;
    }
    
    
    
    
    
    
    //Update---------------------------------
    static String updateSqlErrorUser_setablesAndValuesToSetWithNotEqual()
    {
        return DATABASE_ERROR + "A problem with the SQL in this code means it doesn't work";
    }
    static String updateSqlErrorSys_setablesAndValuesToSetWithNotEqual(Object object)
    {
        return DATABASE_ERROR + METH_UPDATE+ "SQL invalid, SQL="+object;
    }
    static String updateErrorUser_tooManyRowsAffected()
    {
        return DATABASE_ERROR + "Update error, multiple objects were changes, Changes were made!";
    }
    
    static String updateErrorSys_tooManyRowsAffected( Object object )
    {
        return DATABASE_ERROR + METH_UPDATE + "Update error, affected multiple rows! Changes were made! For DTO=" + object;
    }
    
    static String updateErrorUser_noRowsAffected()
    {
        return DATABASE_ERROR + "Update failed, could not find object in database, no changes were made";
    }
    
    static String updateErrorSys_noRowsAffected( Object object )
    {
        return DATABASE_ERROR + METH_UPDATE + "Update failed, no matching ID found for DTO=" + object;
    }
    
    static void updateRowsConsoleMessage( Object object )
    {
        System.out.println( DATABASE + "Updated DTO=" + object );
    }
    
    
    //Delete--------------------
    
    static String deleteErrorUser_tooManyRowsAffected()
    {
        return DATABASE_ERROR + "Deletion error, multiple objects were changes, Deletions were made!";
    }
    
    static String deleteErrorSys_tooManyRowsAffected( String TableName, int id )
    {
        return DATABASE_ERROR + METH_UPDATE + "Deletion error, affected multiple rows! Deletions were made! For '" + TableName + "' row with id=" + id;
    }
    
    static String deleteErrorUser_noRowsAffected()
    {
        return DATABASE_ERROR + "Deletion failed, could not find object in database, no changes were made";
    }
    
    static String deleteErrorSys_noRowsAffected( String TableName, int id )
    {
        return DATABASE_ERROR + METH_UPDATE + "Deletion failed, no matching ID found for '" + TableName + "' row with id=" + id;
    }
    
    static void deleteRowsConsoleMessage( String TableName, int id )
    {
        System.out.println( DATABASE + "Deleted '" + TableName + "' row with id=" + id );
    }
    
}
