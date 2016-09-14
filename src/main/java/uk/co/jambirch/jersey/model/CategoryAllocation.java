package uk.co.jambirch.jersey.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * TODO: merge with other tables.
 */
@DynamoDBTable(tableName = "CategoryAllocation")
public class CategoryAllocation {
    private String name;
    private String cycleName;
    private String categoryName;
    private String promotionalSpaceName;

    public CategoryAllocation() {
    }
    public CategoryAllocation(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBHashKey(attributeName = "cycleName")
    public String getCycleName() {
        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    @DynamoDBRangeKey(attributeName = "categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @DynamoDBAttribute(attributeName = "promotionalSpaceName")
    public String getPromotionalSpaceName() {
        return promotionalSpaceName;
    }

    public void setPromotionalSpaceName(String promotionalSpaceName) {
        this.promotionalSpaceName = promotionalSpaceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryAllocation that = (CategoryAllocation) o;

        if (!name.equals(that.name)) return false;
        if (!cycleName.equals(that.cycleName)) return false;
        if (!categoryName.equals(that.categoryName)) return false;
        return promotionalSpaceName.equals(that.promotionalSpaceName);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + cycleName.hashCode();
        result = 31 * result + categoryName.hashCode();
        result = 31 * result + promotionalSpaceName.hashCode();
        return result;
    }
}
