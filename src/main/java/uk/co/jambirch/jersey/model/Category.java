package uk.co.jambirch.jersey.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

/**
 * Created by peter.jamieson on 14/09/2016.
 */
@DynamoDBTable(tableName = "Category")
public class Category {
    private String name;

    public Category() {
    }

    public Category(String name) {
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

        Category category = (Category) o;

        return name.equals(category.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public Category queryOne() {
        DynamoDBQueryExpression<Category> queryExpression = new DynamoDBQueryExpression<Category>()
                .withHashKeyValues(this);

        DynamoDBMapper mapper = DBConnection.getInstance().getMapper();

        List<Category> cycles = mapper.query(Category.class, queryExpression);
        if (cycles.size() != 1) {
            System.out.println("returned = " + cycles.size());
            return null;
        } else {
            return cycles.get(0);
        }
    }
}
