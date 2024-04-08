package cupcake.databases.services;

import cupcake.databases.entities.tables.OrderLine;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.languages.english.Lang_Service;

public class FrontPageService
{
    
    public static OrderLine addOrderLine( String bottomIdAsString, String topIdAsString, String amountAsString ) throws WebInvalidInputException
    {
        StringBuilder stringBuilderErrMsg = new StringBuilder();
        boolean isError = false;
        
        if ( bottomIdAsString == null ) {
            isError = true;
            addErrMsg( stringBuilderErrMsg, Lang_Service.frontpageErrorUser_invalidBottom() );
        }
        
        if ( topIdAsString == null ) {
            isError = true;
            addErrMsg( stringBuilderErrMsg, Lang_Service.frontpageErrorUser_invalidTop() );
        }
        
        if ( amountAsString == null ) {
            isError = true;
            addErrMsg( stringBuilderErrMsg, Lang_Service.frontpageErrorUser_invalidAmount() );
        }
        
        Integer bottomId = null;
        Integer topId = null;
        Integer amount = null;
        
        if ( bottomIdAsString != null ) {
            try {
                bottomId = Integer.parseInt( bottomIdAsString );
            } catch ( NumberFormatException e ) {
                isError = true;
                stringBuilderErrMsg.append( Lang_Service.frontpageErrorUser_invalidBottomNotNumber()  );
            }
        }
        
        if ( topIdAsString != null ) {
            try {
                topId = Integer.parseInt( topIdAsString );
            } catch ( NumberFormatException e ) {
                isError = true;
                stringBuilderErrMsg.append( Lang_Service.frontpageErrorUser_invalidTopNotNumber()  );
            }
        }
        
        if ( amountAsString != null ) {
            try {
                amount = Integer.parseInt( amountAsString );
            } catch ( NumberFormatException e ) {
                isError = true;
                stringBuilderErrMsg.append( Lang_Service.frontpageErrorUser_invalidAmountNotNumber()  );
            }
        }
        
        if ( isError ) {
            throw new WebInvalidInputException( stringBuilderErrMsg.toString() );
        }
        
        OrderLine orderLine = new OrderLine();
        orderLine.setBottomId(bottomId);
        orderLine.setTopId( topId );
        orderLine.setAmount(amount);
        
        return orderLine;
        
    }
    
    public static StringBuilder addErrMsg( StringBuilder stringBuilderErrMsg, String errMsg )
    {
        stringBuilderErrMsg.append( errMsg ).append( System.lineSeparator() );
        return stringBuilderErrMsg;
    }
    
}
