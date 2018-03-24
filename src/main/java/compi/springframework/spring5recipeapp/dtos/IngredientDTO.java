package compi.springframework.spring5recipeapp.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureDTO unitOfMeasure;

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", unitOfMeasure=" + unitOfMeasure +
                '}';
    }
}
