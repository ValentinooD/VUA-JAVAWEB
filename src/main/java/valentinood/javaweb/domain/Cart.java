package valentinood.javaweb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Set<CartItem> items = new HashSet<>();

    public double getTotalPrice() {
        return items.stream()
                .filter(i -> i.getQuantity() > 0)
                .mapToDouble(i -> i.getArticle().getPrice().doubleValue() * i.getQuantity())
                .sum();
    }

    public String getDescription() {
        return items.stream()
                .filter(i -> i.getQuantity() > 0)
                .map(i -> i.getArticle().getId() + ":" + i.getArticle().getName().toLowerCase().replace(" ", "_")).collect(Collectors.joining("\n"));
    }
}
