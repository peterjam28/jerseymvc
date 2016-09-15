package uk.co.jambirch.jersey.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.time.LocalDate;
import java.util.List;

@DynamoDBTable(tableName = "Cycle")
public class Cycle {
    private String name = "";
    private long startDate = 0;

    public Cycle() {
    }

    public Cycle(String name) {
        this.name = name;
    }

    @DynamoDBHashKey(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "startDate")
    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cycle cycle = (Cycle) o;

        return name.equals(cycle.name) && startDate == cycle.startDate;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (int) (startDate ^ (startDate >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Cycle{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                '}';
    }

    public Cycle queryOne() {
        DynamoDBQueryExpression<Cycle> queryExpression = new DynamoDBQueryExpression<Cycle>()
                .withHashKeyValues(this);

        DynamoDBMapper mapper = DBConnection.getInstance().getMapper();

        List<Cycle> cycles = mapper.query(Cycle.class, queryExpression);
        if (cycles.size() != 1) {
            System.out.println("returned = " + cycles.size());
            return null;
        } else {
            return cycles.get(0);
        }
    }
}
