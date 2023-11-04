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
import ru.liga.utils.LocalDateTimeTypeHandler;

import java.util.List;
import java.util.Optional;


@Mapper
public interface OrderMapper {

    @Select("SELECT id, status FROM orders WHERE status = #{status}")
    @Results(value ={@Result(property = "id", column = "id"),
            @Result(property = "status", column = "status")})
    Optional<Order> getByStatus(String status);

    @Update("Update Order set status= #{status} where orderId=#{orderId}")
    void updateStatus(String status, Long orderId);

    @Select("SELECT * FROM orders WHERE id = #{id}")
    @Results(value = {@Result(property = "customer", column = "customer_id", javaType = Customer.class, one =@One(select = "getCustomer")),
            @Result(property = "restaurant", column = "restaurant_id", javaType = Restaurant.class, one = @One(select = "getRestaurant")),
            @Result(property = "courier", column = "courier_id", javaType = Courier.class, one = @One(select = "getCourier")),
            @Result(property = "timestamp", column = "timestamp", typeHandler = LocalDateTimeTypeHandler.class)})
    Optional<Order> getById(long id);

    @Select("SELECT * FROM orders")
    @Results(value = {@Result(property = "restaurant", column = "restaurant_id", javaType = Restaurant.class, one = @One(select = "getRestaurant")),
            @Result(property = "timestamp", column = "timestamp", typeHandler = LocalDateTimeTypeHandler.class)})
    List<Order> getAllOrders();

    @Select("SELECT * FROM customers WHERE id = #{id}")
    Customer getCustomer(Long id);

    @Select("SELECT * FROM restaurants WHERE id = #{id}")
    Restaurant getRestaurant(Long id);

    @Select("SELECT * FROM couriers WHERE id = #{id}")
    Courier getCourier(Long id);

}
