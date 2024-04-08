package cupcake.databases.mappers.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface TemplateDtoCreator
{
    /**
     *Should always follow this rule
     *
     * NOTE: DTO = (user, wallet, orderLines ect.)
     *
     * @param rs
     * @return DTO
     * @throws SQLException
     */
    
    Object createDto( ResultSet rs ) throws SQLException;
    
    
    /**
     *
     *
     *Should always follow this rule
     *
     * NOTE: DTO = (user, wallet, orderLines ect.)
     *
     * @param rs
     * @return LinkedHashMap<Integer,DTO>
     * @throws SQLException
     */
    Map<Integer, ?> createDtoMultiple( ResultSet rs ) throws SQLException;
    
    
    
    
    
    /**
     * Casting baby!
     *
     * @param entity (DTO)
     * @param id (ID to Set on DTO)
     * @return DTO with ID set
     */
    Object setId (Object entity, Integer id);
    
}
