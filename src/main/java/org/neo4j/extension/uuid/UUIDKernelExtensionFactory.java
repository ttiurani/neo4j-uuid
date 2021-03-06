package org.neo4j.extension.uuid;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.extension.KernelExtensionFactory;
import org.neo4j.kernel.lifecycle.Lifecycle;

public class UUIDKernelExtensionFactory extends KernelExtensionFactory<UUIDKernelExtensionFactory.Dependencies>
{
    private boolean updateRelationships = false;
    private boolean checkForUuidChanges = false;
    private boolean setupAutoIndexing = false;

    
    public interface Dependencies{
        GraphDatabaseService getDatabase();
    }

    public UUIDKernelExtensionFactory(boolean updateRelationships, boolean checkForUuidChanges, boolean setupAutoIndexing){
        super( "uuid" );
        this.updateRelationships = updateRelationships;
        this.checkForUuidChanges = checkForUuidChanges;
        this.setupAutoIndexing = setupAutoIndexing;
    }

    @Override
    public Lifecycle newKernelExtension( Dependencies dependencies ) throws Throwable {
        return new UUIDKernelExtension(dependencies.getDatabase(), updateRelationships, checkForUuidChanges, setupAutoIndexing);
    }
}
