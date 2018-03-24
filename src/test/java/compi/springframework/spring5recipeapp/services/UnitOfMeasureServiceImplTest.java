package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.converters.response.UnitOfMeasureToDto;
import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.model.UnitOfMeasure;
import compi.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    UnitOfMeasureToDto unitOfMeasureToDto;

    @Before
    public void setUp() {
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToDto);
    }

    @Test
    public void listAllUoms() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        UnitOfMeasureDTO uomDto1 = new UnitOfMeasureDTO();
        uomDto1.setId(1L);

        UnitOfMeasureDTO uomDto2 = new UnitOfMeasureDTO();
        uomDto2.setId(2L);

        when(unitOfMeasureToDto.convert(uom1)).thenReturn(uomDto1);
        when(unitOfMeasureToDto.convert(uom2)).thenReturn(uomDto2);

        Set<UnitOfMeasureDTO> dtos = unitOfMeasureService.listAllUoms();

        assertEquals(dtos.size(), 2);
        verify(unitOfMeasureRepository, times(1)).findAll();
        verify(unitOfMeasureToDto, times(2)).convert(any());

    }
}