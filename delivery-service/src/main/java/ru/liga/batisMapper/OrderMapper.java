package ru.liga.batisMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.liga.models.Courier;
import ru.liga.models.Customer;
import ru.liga.models.Order;
import ru.liga.models.Restaurant;
import ru.liga.models.enums.OrderStatus;
import ru.liga.utils.LocalDateTimeTypeHandler;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Update("Update orders set status= #{status} where orderId=#{orderId}")
    void updateStatus(OrderStatus status, Long orderId);

    @Select(value = "select * from orders as o " +
            "inner join restaurants r on o.restaurant_id = r.id " +
            "inner join customers c on o.customer_id = c.id " +
            "inner join couriers co on o.courier_id = co.id " +
            "where o.status = #{status} ")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "customer", column = "customer_id", javaType = Customer.class, one = @One(select = "getCustomer")),
            @Result(property = "restaurant", column = "restaurant_id", javaType = Restaurant.class, one = @One(select = "getRestaurant")),
            @Result(property = "courier", column = "courier_id", javaType = Courier.class, one = @One(select = "getCourier")),
            @Result(property = "status", column = "status"),
            @Result(property = "timestamp", column = "timestamp", typeHandler = LocalDateTimeTypeHandler.class)})
    List<Order> getOrderByStatus(OrderStatus status);

    @Select("select * from restaurants where id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "address", column = "address"),
            @Result(property = "status", column = "status")
    })
    Restaurant getRestaurant(Long id);

    @Select("select * from customers where id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email"),
            @Result(property = "address", column = "address")
    })
    Customer getCustomer(Long id);

    @Select("select * from couriers where id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "status", column = "status"),
            @Result(property = "coordinates", column = "coordinates")
    })
    Courier getCourier(Long id);

}
