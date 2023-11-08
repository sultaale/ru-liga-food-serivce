package ru.liga.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.liga.exceptions.RestaurantNotFoundException;
import ru.liga.repository.RestaurantMenuRepository;
import ru.liga.service.Impl.RestaurantMenuServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestaurantMenuServiceImplTest {
    @Mock
    private RestaurantMenuRepository restaurantMenuRepository;
    @InjectMocks
    private RestaurantMenuServiceImpl restaurantMenuService;

    @Test
    public void getById_whenMenuNotFound_throwsException() {
        when(restaurantMenuRepository.findById(10L)).thenThrow(new RestaurantNotFoundException(10L));

        assertThatThrownBy(()-> restaurantMenuService.getById(10L))
                .isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    public void getById_whenIdCorrect_thenReturnRestaurantMenu() {
       when(restaurantMenuRepository.findById(1L)).thenReturn(Optional.empty());

       assertEquals(Optional.empty(), restaurantMenuRepository.findById(1L));
    }

    @Test
    public void getByName_whenNameNotFound_throwsException() {
        when(restaurantMenuRepository.findByName("Оладьи")).thenThrow(new RestaurantNotFoundException("Не найден"));

        assertThatThrownBy(() -> restaurantMenuService.getByName("Оладьи")).isInstanceOf(RestaurantNotFoundException.class);
    }
}
