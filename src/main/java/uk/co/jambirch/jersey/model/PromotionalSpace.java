package uk.co.jambirch.jersey.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Created by peter.jamieson on 14/09/2016.
 */
@DynamoDBTable(tableName = "PromotionalSpace")
public class PromotionalSpace {
    private String name;

    public PromotionalSpace() {
    }
    public PromotionalSpace(String name) {
        this.name = name;
    }

    @DynamoDBHashKey(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionalSpace that = (PromotionalSpace) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
