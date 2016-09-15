package uk.co.jambirch.jersey.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

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

    public PromotionalSpace queryOne() {
        DynamoDBQueryExpression<PromotionalSpace> queryExpression = new DynamoDBQueryExpression<PromotionalSpace>()
                .withHashKeyValues(this);

        DynamoDBMapper mapper = DBConnection.getInstance().getMapper();

        List<PromotionalSpace> cycles = mapper.query(PromotionalSpace.class, queryExpression);
        if (cycles.size() != 1) {
            System.out.println("returned = " + cycles.size());
            return null;
        } else {
            return cycles.get(0);
        }
    }
}
