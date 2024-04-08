package cupcake.databases.mappers.tables;

import cupcake.databases.persistence.ConnectionPoolAccessIf;

public interface TemplateMappersStartUp
{
    static void startUpSetConnectionPoolAccess( ConnectionPoolAccessIf connectionPoolAccess ) {
        TemplateSharedCrud.setConnectionPoolAccess( connectionPoolAccess );
    }
}
