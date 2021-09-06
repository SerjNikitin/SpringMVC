package com.example.springmvc.service.impl;

import com.example.springmvc.converter.Converter;
import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.ProductSearchCondition;
import com.example.springmvc.domain.dto.ProductDto;
import com.example.springmvc.repository.ProductRepository;
import com.example.springmvc.service.ProductService;
import com.example.springmvc.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

//    @Override
//    public Page<Product> pagination(ProductSearchCondition searchCondition) {
//        Pageable pageRequest = PageRequest.of(searchCondition.getPageNum(),
//                searchCondition.getPageSize(),
//                Sort.by(searchCondition.getSortDirection(),searchCondition.getSortField()));
//        return pageRequest.;
//        return productRepository.findAll(pageRequest);
//    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product saveProductAndImage(ProductDto productDto, MultipartFile image) {
        Product product = convertProductDtoInProduct(productDto);
        Product savedProduct = productRepository.save(product);
        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            savedProduct.setImage(pathImage.toString());
            productRepository.save(savedProduct);
        }
        return savedProduct;
    }

    private Product convertProductDtoInProduct(ProductDto productDto) {
        Integer id = productDto.getId();
        if (id != null) {
            Product product = findProductById(id).get();
            product.setId(productDto.getId());
            return Converter.getProduct(productDto, product);
        }
        Product product = new Product();
        return Converter.getProduct(productDto, product);
    }

    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public ProductDto findProductDtoById(Integer id) {
        Optional<Product> productById = findProductById(id);

        return Converter.DtoProductConvertInProduct(productById.get());
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductsByTitleAndByMaxAndMinPrice(String title, Integer minPrice, Integer maxPrice) {
        if (Objects.isNull(minPrice)) {
            minPrice = 0;
        }
        if (Objects.isNull(maxPrice)) {
            maxPrice = Integer.MAX_VALUE;
        }
        return productRepository.findProductsByTitleContainingIgnoreCaseAndPriceBetween(title, minPrice, maxPrice);
    }

//
//    private boolean getRedirectView(String title, String price, RedirectAttributes attributes, MultipartFile image) {
//        if (title.isEmpty()) {
//            attributes.addFlashAttribute("error", "Заполните поле с названием продукта");
//            return true;
//        }
//        if (price.isEmpty()) {
//            attributes.addFlashAttribute("error", "Заполните поле с ценой продукта");
//            return true;
//        }
//        if (image.isEmpty()) {
//            attributes.addFlashAttribute("error", "Заполните поле с фотографией продукта");
//            return true;
//        }
//        return false;
//    }
}