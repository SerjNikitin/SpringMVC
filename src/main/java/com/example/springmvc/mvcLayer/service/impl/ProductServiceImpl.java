package com.example.springmvc.mvcLayer.service.impl;

import com.example.springmvc.mvcLayer.domain.Category;
import com.example.springmvc.mvcLayer.domain.Product;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition;
import com.example.springmvc.mvcLayer.repository.ProductRepository;
import com.example.springmvc.mvcLayer.service.CategoryService;
import com.example.springmvc.mvcLayer.service.FileService;
import com.example.springmvc.mvcLayer.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final FileService fileService;

    @Override
    @Transactional
    public Product saveProductAndImage(ProductDto productDto, MultipartFile image) {
        Product product = choosingActionOrCreatingOrUpdating(productDto);
        Product savedProduct = productRepository.save(product);
        if (image != null && !image.isEmpty()) {
            Path pathImage = fileService.saveProductImage(image);
            savedProduct.setImage(pathImage.toString());
            productRepository.save(savedProduct);
        }
        return savedProduct;
    }

    private Product choosingActionOrCreatingOrUpdating(ProductDto productDto) {
        Integer id = productDto.getId();
        if (id != null) {
            Product product = dtoProductConvertToProduct(productDto);
            product.setId(productDto.getId());
            return product;
        }
        return dtoProductConvertToProduct(productDto);
    }

    private Product dtoProductConvertToProduct(ProductDto productDto) {
        return Product.builder().title(productDto.getTitle())
                .price(productDto.getPrice())
                .categories(categoryService.findCategoryById(productDto.getCategoryDto()))
                .countProduct(productDto.getCountProduct())
                .build();
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductDto findProductDtoById(Integer id) {
        Optional<Product> productById = findProductById(id);
        if (productById.isPresent()) {
            return productConvertToDTOProduct(productById.get());
        } else throw new NoSuchElementException("Продукт был удален администратором");
    }

//    @Override
//    @Transactional
//    public void minusOneProductInCount(Integer id, Integer count) {
////        Optional<Product> byId = productRepository.findById(id);
////        byId.ifPresent(product -> productRepository.updateCount(product, count));
//        productRepository.updateCount(id, count);
//    }

//    @Override
//    @Transactional
//    public void plusCountProduct(Integer productId) {
//        Optional<Product> byId = productRepository.findById(productId);
//        Integer countProduct = byId.get().getCountProduct()+1;
//        productRepository.updateCount(productId, countProduct);
////        productRepository.plusCount(productId);
//    }

    @Override
    @Transactional
    public void updateCountInProduct(Integer id, Integer count) {
        productRepository.updateCount(id, count);
    }

    private ProductDto productConvertToDTOProduct(Product entity) {
        return ProductDto.builder().id(entity.getId())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .categoryDto(categoryService.getCategoryIdList(entity.getCategories()))
                .countProduct(entity.getCountProduct())
                .build();
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Page<Product> findProductsByCategoryId(Integer catId, ProductSearchCondition searchCondition) {
        Optional<Category> category = categoryService.findCategory(catId);
//        Optional<Category> byId = categoryRepository.findById(catId);
        Pageable pageable = getPageable(searchCondition);
        return productRepository.findProductsByCategories(pageable, category.get());
    }

    @Override
    public Page<Product> findAllBySearchConditional(ProductSearchCondition searchCondition) {
        Pageable pageRequest = getPageable(searchCondition);
        return productRepository.findAll(pageRequest);
    }

    private Pageable getPageable(ProductSearchCondition searchCondition) {
        return PageRequest.of(searchCondition.getPageNum() - 1,
                searchCondition.getPageSize(),
                Sort.by(searchCondition.getSortDirection(), searchCondition.getSortField()));
    }

    @Override
    public Page<Product> findProductsByTitleAndByMaxAndMinPriceBySearchConditional(
            ProductSearchCondition searchCondition, String title, Integer minPrice, Integer maxPrice) {
        if (Objects.isNull(minPrice)) {
            minPrice = 0;
        }
        if (Objects.isNull(maxPrice)) {
            maxPrice = Integer.MAX_VALUE;
        }
        Pageable pageRequest = getPageable(searchCondition);
        return productRepository.findProductsByTitleContainingIgnoreCaseAndPriceBetween(title, minPrice, maxPrice, pageRequest);
    }

    @Override
    public void pagination(ProductSearchCondition searchCondition, Model model, Page<Product> page) {
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("pageNum", searchCondition.getPageNum());
        model.addAttribute("page", page);
        model.addAttribute("pageSize", searchCondition.getPageSize());
    }
}