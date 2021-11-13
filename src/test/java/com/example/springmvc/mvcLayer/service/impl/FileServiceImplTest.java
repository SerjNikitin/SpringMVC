package com.example.springmvc.mvcLayer.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FileServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FileServiceImplTest {
    @Autowired
    private FileServiceImpl fileServiceImpl;

    @Test
    void testSaveProductImage() {
        assertThrows(IllegalArgumentException.class, () -> this.fileServiceImpl.saveProductImage(null));
    }
}

