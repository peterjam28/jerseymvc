package uk.co.jambirch.jersey.model;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Created by peter.jamieson on 15/09/2016.
 */
public class DBConnection {
    private final DynamoDBMapper mapper;
    private static final DBConnection connection = new DBConnection();

    private DBConnection() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
        client.setEndpoint("http://localhost:9020");
        mapper = new DynamoDBMapper(client);
    }

    public static DBConnection getInstance() {
        return connection;
    }

    public DynamoDBMapper getMapper() {
        return mapper;
    }
}
