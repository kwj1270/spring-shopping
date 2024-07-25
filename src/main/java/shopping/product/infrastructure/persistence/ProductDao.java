package shopping.product.infrastructure.persistence;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import shopping.product.infrastructure.api.dto.ProductDetailResponse;
import shopping.product.infrastructure.api.dto.ProductListResponse;

import javax.sql.DataSource;

@Component
public class ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductDao(final DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public ProductDetailResponse findProductDetail(final long productId) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", productId);

        return namedParameterJdbcTemplate.queryForObject(
                """
                        SELECT 
                            p.name as product_name, p.amount, p.image_url, sc.name as category_name, s.name as shop_name, se.name as seller_name   
                        FROM products p
                        LEFT JOIN shops s ON p.shop_id = s.id 
                        LEFT JOIN sub_categories sc ON p.sub_category_id = sc.id 
                        LEFT JOIN sellers se ON s.seller_id = se.id
                        WHERE p.id = :id 
                        """,
                namedParameters, new ProductDetailMapper()
        );
    }

    public ProductListResponse findProductListByCategoryId(final long categoryId, final long startId, final long limit) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("categoryId", categoryId)
                .addValue("startId", startId)
                .addValue("limit", limit);

        return namedParameterJdbcTemplate.queryForObject(
                """
                        SELECT 
                            p.name as product_name, p.amount, p.image_url, sc.name as category_name, s.name as shop_name, se.name as seller_name   
                        FROM products p
                        LEFT JOIN shops s ON p.shop_id = s.id 
                        LEFT JOIN sub_categories sc ON p.sub_category_id = sc.id 
                        LEFT JOIN sellers se ON s.seller_id = se.id
                        LEFT JOIN wish_lists w ON p.id = w.product_id
                        WHERE p.id >= :startId 
                        AND p.sub_category_id = :categoryId
                        LIMIT :limit
                        """,
                namedParameters, new ProductListMapper()
        );
    }
}

