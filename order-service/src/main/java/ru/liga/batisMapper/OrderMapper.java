package ru.liga.batisMapper;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.liga.models.Order;


@Mapper
public interface OrderMapper {

    @Select("SELECT id, status FROM orders WHERE id = #{id}")
    Order getById(long id);

}
