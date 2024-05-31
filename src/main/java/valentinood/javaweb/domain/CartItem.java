package valentinood.javaweb.domain;

import lombok.*;
import valentinood.javaweb.dto.ArticleDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {
    private ArticleDTO article;
    private int quantity;
}
