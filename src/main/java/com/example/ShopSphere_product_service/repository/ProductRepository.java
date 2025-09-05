package com.example.ShopSphere_product_service.repository;

import com.example.ShopSphere_product_service.dtos.ProductResponseDTO;
import com.example.ShopSphere_product_service.dtos.SearchProductDTO;
import com.example.ShopSphere_product_service.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    default List<ProductResponseDTO> searchProduct(SearchProductDTO searchDTO) {
        return List.of();
    }
//     PS Query
//    default Page<ProductResponseDTO> searchProduct(EntityManager em,
//                                                   SearchProductDTO searchDTO,
//                                                   Pageable paging) {
//        var queryBuilder = new PMISQueryBuilder.QueryBuilder()
//                .select()
//                .distinct()
//                .map(ProductResponseDTO.class,
//                        "projectinfo.codePps",
//                        "projectinfo.code",
//                        "project.id",
//                        "projectinfo.name",
//                        "projectinfo.nameBangla",
//                        "type.id",
//                        "type.name",
//                        "projectinfo.dateOfCommencement",
//                        "projectinfo.dateOfCompletion",
//                        "agency.id",
//                        "agency.name"
//                )
//                .from(ProjectInfo.class, "projectinfo")
//                .innerJoin(Project.class, "project")
//                .onWithAliasThree("projectinfo.project", "project", "projectinfo.projectRevisionNo",
//                        "project.currentProjectRevisionNo")
//                .innerJoin(BaseData.class, "type")
//                .onWithAlias("type", "project.projectTypeBaseData")
//                .innerJoin(AdministrativeHierarchy.class, "agency")
//                .onWithAlias("agency", "projectinfo.agencyAdministrativeHierarchy");
//
//        if (StringUtils.isNotBlank(searchDTO.getPps_code())) {
//            queryBuilder.and();
//            queryBuilder.equalWithAlias("projectinfo.codePps",
//                    searchDTO.getPps_code());
//        }
//        if (StringUtils.isNotBlank(searchDTO.getName())) {
//            queryBuilder.and();
//            queryBuilder.equalWithAlias("projectinfo.name",
//                    searchDTO.getName());
//        }
//        if (StringUtils.isNotBlank(searchDTO.getProject_type_id())) {
//            queryBuilder.and();
//            queryBuilder.equalWithAlias("type.id",
//                    searchDTO.getProject_type_id());
//        }
//        if (StringUtils.isNotBlank(searchDTO.getAgency_id())) {
//            queryBuilder.and();
//            queryBuilder.equalWithAlias("agency.id", searchDTO.getAgency_id());
//        }
//        queryBuilder.and();
//        queryBuilder.equalWithAlias("project.source", ProjectSource.PPS);
//
//
//        var q = queryBuilder.build()
//                .toString();
//        Map<String,Object> params = queryBuilder.build().getParameters();
//        TypedQuery<ProductResponseDTO> query =
//                em.createQuery(q, ProductResponseDTO.class);
//
//        String countQueryString = getCountQuery(q);
//        TypedQuery<Long> countQuery = em.createQuery(countQueryString, Long.class);
//        params.forEach((key, value) -> {
//            query.setParameter(key, value);
//            countQuery.setParameter(key, value);
//        });
//
//        long totalCount = countQuery.getSingleResult();
//
//        List<ProductResponseDTO> result =
//                query.setFirstResult((int) paging.getOffset())
//                        .setMaxResults(paging.getPageSize())
//                        .getResultList();
//        return new PageImpl<>(result, paging, totalCount);
//    }
//    String getCountQuery(Object q);

    default List<Product> searchProducts(EntityManager em, SearchProductDTO dto, Pageable pageable) {

        StringBuilder jpql = new StringBuilder("SELECT p FROM Product p WHERE 1=1");

        if (StringUtils.isNotBlank(dto.getName())) {
            jpql.append(" AND LOWER(p.name) LIKE LOWER('%")
                    .append(dto.getName().trim().toLowerCase())
                    .append("%')");
        }

        if (StringUtils.isNotBlank(dto.getDescription())) {
            jpql.append(" AND LOWER(p.description) LIKE LOWER('%")
                    .append(dto.getDescription().trim().toLowerCase())
                    .append("%')");
        }

        if (dto.getMin_price() != null) {
            jpql.append(" AND p.price >= ").append(dto.getMin_price());
        }

        if (dto.getMax_price() != null) {
            jpql.append(" AND p.price <= ").append(dto.getMax_price());
        }

//        if (StringUtils.isNotBlank(dto.getCategory())) {
//            jpql.append(" AND LOWER(p.category) = '")
//                    .append(dto.getCategory().trim().toLowerCase())
//                    .append("'");
//        }

        if (dto.getIn_stock() != null) {
            if (dto.getIn_stock()) {
                jpql.append(" AND p.stockQuantity > 0");
            } else {
                jpql.append(" AND p.stockQuantity = 0");
            }
        }




        // Step 2: Create TypedQuery
        TypedQuery<Product> query = em.createQuery(jpql.toString(), Product.class);

        // Step 3: Pagination
        TypedQuery<Long> countQuery = em.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE 1=1" + jpql.substring(jpql.indexOf(" AND")), Long.class);

        long totalCount = countQuery.getSingleResult();

//        List<Product> result = query.setFirstResult((int) pageable.getOffset())
//                .setMaxResults(pageable.getPageSize())
//                .getResultList();
        List<Product> result = query.getResultList();
        return result;
    }
}