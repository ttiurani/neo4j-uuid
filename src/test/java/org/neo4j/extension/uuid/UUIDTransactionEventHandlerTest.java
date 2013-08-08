package org.neo4j.extension.uuid;

import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class UUIDTransactionEventHandlerTest extends UUIDTestBase{

  @Test
  public void shouldCreateUUIDToNewNode() {
    GraphDatabaseService graphdb = new GraphDatabaseFactory()
        .newEmbeddedDatabaseBuilder(TEST_DATA_STORE_DESTINATION).newGraphDatabase();

    graphdb.registerTransactionEventHandler(new UUIDTransactionEventHandler<String>());

    Transaction tx = graphdb.beginTx();
    Node node = graphdb.createNode();
    node.setProperty("test", "test");
    long id = node.getId();
    tx.success();
    tx.finish();
    
    tx = graphdb.beginTx();
    node = graphdb.getNodeById(id);
    node.getProperty("test");
    // New nodes should have a "uuid" property
    node.getProperty("uuid");
    tx.success();
    tx.finish();
  }
}