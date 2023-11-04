package ru.liga.service.Impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.MenuCreationDTO;
import ru.liga.dto.RestaurantMenuDTO;
import ru.liga.exceptions.InvalidPropertyException;
import ru.liga.exceptions.RestaurantMenuException;
import ru.liga.exceptions.RestaurantNotFoundException;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.repository.RestaurantMenuRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.RestaurantMenuService;


import javax.validation.Valid;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantMenuServiceImpl implements RestaurantMenuService {
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantMenuDTO getById(Long id) {
        log.debug("Поиск меню по id RestaurantMenuRepository");
        RestaurantMenuItem restaurantMenuItem = restaurantMenuRepository.findById(id).orElseThrow(() -> new RestaurantMenuException("Menu not found by id " + id));
        RestaurantMenuDTO restaurantMenuDTO = RestaurantMenuDTO.builder().id(restaurantMenuItem.getId())
                .name(restaurantMenuItem.getName()).price(restaurantMenuItem.getPrice())
                .image(restaurantMenuItem.getImage()).description(restaurantMenuItem.getDescription()).build();
        return restaurantMenuDTO;
    }

    @Override
    public RestaurantMenuItem getByName(String name) {
        log.debug("Поиск меню по имени в RestaurantMenuRepository");
        return restaurantMenuRepository.findByName(name).orElseThrow(() -> new RestaurantMenuException("Menu not found by name" + name));
    }

    
    @Transactional
    public void updatePrice(@Valid Long id, Double price) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuRepository.findById(id).orElseThrow(() ->new RestaurantMenuException("Menu not found"));

        if (price <= 0.0) {

            throw new InvalidPropertyException("Please check price " + price); }

        restaurantMenuItem.setPrice(price);
        restaurantMenuRepository.save(restaurantMenuItem);
    }

    @Transactional
    public void addMenu(MenuCreationDTO menuCreationDTO) {
        RestaurantMenuItem menu = new RestaurantMenuItem();

        Restaurant restaurant = restaurantRepository.findById(menuCreationDTO.getRestaurantId())
                .orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found"));
        menu.setRestaurant(restaurant);
        menu.setName(menuCreationDTO.getName());
        menu.setPrice(menuCreationDTO.getPrice());
        menu.setImage(menuCreationDTO.getImage());
        menu.setDescription(menuCreationDTO.getDescription());

        restaurantMenuRepository.save(menu);
    }

    @Transactional
    public void deleteById(Long id) {
        restaurantMenuRepository.findById(id)
                .orElseThrow(() -> new RestaurantMenuException("Menu not found by id"));

        restaurantMenuRepository.deleteById(id);
    }
}
