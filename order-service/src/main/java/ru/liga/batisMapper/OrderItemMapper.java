package ru.liga.batisMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.liga.models.Courier;
import ru.liga.models.Customer;
import ru.liga.models.Order;
import ru.liga.models.OrderItem;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.utils.LocalDateTimeTypeHandler;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderItemMapper {

    @Select("SELECT id, restaurant_menu_item_id, price, quantity FROM order_items WHERE id = #{id}")
    @Results(value = {@Result(property = "restaurantMenuItem", column = "restaurant_menu_item_id",
            javaType = RestaurantMenuItem.class, one = @One(select = "getRestaurantMenu"))})
    Optional<OrderItem> getById(Long id);

    @Select("SELECT id, restaurant_menu_item_id, price, quantity FROM order_items WHERE order_id = #{id}")
    @Results(value ={@Result(property = "id", column = "id"),
            @Result(property = "restaurantMenuItem", column = "restaurant_menu_item_id", javaType = RestaurantMenuItem.class, one = @One(select = "getRestaurantMenu")),
    @Result(property = "price", column = "price"),
    @Result(property = "quantity", column = "quantity")})
    List<OrderItem> getByOrderId(Long orderId);

    @Select("SELECT * FROM restaurant_menu_items WHERE id = #{id}")
    @Results(value = {@Result(property = "restaurant", column = "restaurant_id", javaType = Restaurant.class, one = @One(select = "getRestaurant"))})
    RestaurantMenuItem getRestaurantMenu(Long id);

    @Select("SELECT * FROM restaurants WHERE id = #{id}")
    Restaurant getRestaurant(Long id);

    @Select("SELECT * FROM orders")
    @Results(value = {@Result(property = "timestamp", column = "timestamp", typeHandler = LocalDateTimeTypeHandler.class),
            @Result(property = "customer", column = "customer_id", javaType = Customer.class, one =@One(select = "getCustomer")),
            @Result(property = "restaurant", column = "restaurant_id", javaType = Restaurant.class, one = @One(select = "getRestaurant")),
            @Result(property = "courier", column = "courier_id", javaType = Courier.class, one = @One(select = "getCourier"))})
    Order getOrder(Long id);


    @Select("SELECT * FROM customers WHERE id = #{id}")
    Customer getCustomer(Long id);
    
    @Select("SELECT * FROM couriers WHERE id = #{id}")
    Courier getCourier(Long id);


}
