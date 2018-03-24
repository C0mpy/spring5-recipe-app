package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureDTO> listAllUoms();
}
