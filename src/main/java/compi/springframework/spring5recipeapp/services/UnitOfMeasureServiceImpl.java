package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.converters.response.UnitOfMeasureToDto;
import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToDto unitOfMeasureToDto;

    @Autowired
    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToDto unitOfMeasureToDto) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToDto = unitOfMeasureToDto;
    }

    @Override
    public Set<UnitOfMeasureDTO> listAllUoms() {


        //StreamSupport.stream(unitOfMeasureRepository.findAll(). spliterator(), false)
        //je nacin da kolekciju Iterable iz repository pretvorimo u stream
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
        .spliterator(), false)
                .map(unitOfMeasureToDto::convert)
                .collect(Collectors.toSet());

    }
}
