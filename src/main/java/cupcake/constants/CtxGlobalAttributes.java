package cupcake.constants;

import cupcake.databases.entities.extended.BottomExtended;
import cupcake.databases.entities.extended.TopExtended;
import cupcake.databases.entities.tables.*;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.mappers.tables.*;

import java.util.LinkedHashMap;
import java.util.Map;

public interface CtxGlobalAttributes
{
    
    //Should be global but we suck
    String bottomMap = "bottomMap";
    Map< Integer, BottomExtended > BOTTOM_MAP = new LinkedHashMap<>();
    
    String topMap = "topMap";
    Map< Integer, TopExtended > TOP_MAP = new LinkedHashMap<>();
    
    
    
    String roleMap = "roleMap";
    Map< Integer, Role > ROLE_MAP = new LinkedHashMap<>();
    
    String statusMap = "statusMap";
    Map< Integer, Status > STATUS_MAP = new LinkedHashMap<>();
    
    String userMap = "userMap";
    Map< Integer, User > USER_MAP = new LinkedHashMap<>();
    
    
    
    
    static void startUp()
    {
        
        try {
            Map< Integer, Bottom > bottomMap = BottomMapper.readAll();
            
            for ( Bottom bottom : bottomMap.values() ) {
                BOTTOM_MAP.put( bottom.getBottomId(), new BottomExtended( bottom ) );
            }
            
        } catch ( DatabaseException e ) {
            throw new RuntimeException( e );
        }
        
        try {
            Map< Integer, Top > topMap = TopMapper.readAll();
            
            for ( Top top : topMap.values() ) {
                TOP_MAP.put( top.getTopId(), new TopExtended( top ) );
            }
            
        } catch ( DatabaseException e ) {
            throw new RuntimeException( e );
        }
        
        try {
            ROLE_MAP.putAll( RoleMapper.readAll() );
            
        } catch ( DatabaseException e ) {
            throw new RuntimeException( e );
        }
        
        try {
            STATUS_MAP.putAll( StatusMapper.readAll() );
            
        } catch ( DatabaseException e ) {
            throw new RuntimeException( e );
        }
        
        try {
            USER_MAP.putAll( UserMapper.readAll() );
            
        } catch ( DatabaseException e ) {
            throw new RuntimeException( e );
        }
    }
    
}
