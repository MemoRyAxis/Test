package test.test.test.test.test;
import com.couchbase.client.CouchbaseClient;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @author MemoRyAxis
 * @todo
 * @date 2014/7/19 12:48
 */
public class MrCouchbase {
    public static void main(String[] args) throws Exception{

        // (Subset) of nodes in the cluster to establish a connection
        List<URI> hosts = Arrays.asList(
                new URI("http://182.92.103.149:8091/pools")
        );

        // Name of the Bucket to connect to
        String bucket = "default";

        // Password of the bucket (empty) string if none
        String password = "";

        // Connect to the Cluster
        CouchbaseClient client = new CouchbaseClient(hosts, bucket, password);

        // Store a Document
        client.set("my-first-document", "Couchbase!").get();

        // Retreive the Document and print it
        System.out.println(client.get("my-first-document"));

        // Shutting down properly
        client.shutdown();
    }
}
