package com.example.springmvc.mvcLayer.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.dto.ProductDto;

import java.util.HashSet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ShoppingCart.class})
@ExtendWith(SpringExtension.class)
class ShoppingCartTest {
    @Autowired
    private ShoppingCart shoppingCart;

    @Test
    @Disabled("TODO: This test is incomplete")
    void testAddCartItem() {
        // TODO: This test is incomplete.
        //   Reason: F009 Internal error.
        //   java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61208: (#1160).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@2580797f:cartItems, java.util.HashMap@c1a1750:<data>], 2, 1) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61208: (#1160).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@2580797f:cartItems], 2, 1)
        //   Cause: java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61208: (#1160).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@2580797f:cartItems, java.util.HashMap@c1a1750:<data>], 2, 1) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61208: (#1160).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@2580797f:cartItems], 2, 1)
        //       at com.diffblue.assertion.generator.core.g.b(SourceFile:212)
        //       at com.diffblue.assertion.generator.core.i.a(SourceFile:213)
        //       at com.diffblue.assertion.generator.core.n.a(SourceFile:65)
        //       at com.diffblue.assertion.generator.core.l.a(SourceFile:119)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at java.util.ArrayList.forEach(ArrayList.java:1541)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:109)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:287)
        //       at com.diffblue.fuzztest.e.e.addAssertions(SourceFile:48)
        //       at com.diffblue.fuzztest.e.b.b(SourceFile:41)
        //       at com.diffblue.cover.sandbox.execution.WrapExceptionsInBaseException.handleException(SourceFile:55)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.lambda$run$0(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.handleThrowable(SourceFile:32)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.run(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.SafeExecutor.run(SourceFile:26)
        //       at com.diffblue.fuzztest.e.b.a(SourceFile:40)
        //       at com.diffblue.fuzztest.e.c.b(SourceFile:1271)
        //       at com.diffblue.fuzztest.e.c.i(SourceFile:400)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.lambda$callWorkerThread$3(SourceFile:134)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.run(SourceFile:207)
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support) providing details about this error.

        ShoppingCart shoppingCart = new ShoppingCart();

        ProductDto productDto = new ProductDto();
        productDto.setPrice(0);
        shoppingCart.addCartItem(productDto);
    }

    @Test
    void testAddCartItem2() {
        ShoppingCart shoppingCart = new ShoppingCart();
        ProductDto productDto = mock(ProductDto.class);
        when(productDto.getPrice()).thenReturn(1);
        when(productDto.getId()).thenReturn(1);
        shoppingCart.addCartItem(productDto);
        verify(productDto).getId();
        verify(productDto).getPrice();
        assertEquals(1, shoppingCart.getTotalPrice().intValue());
        assertEquals(1, shoppingCart.getCount());
        assertEquals(1, shoppingCart.getCartItems().get(1).getCount().intValue());
    }

    @Test
    void testAddCartItem3() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addCartItem(new ProductDto(1, "Dr", 1, new HashSet<Integer>(), 3));
        ProductDto productDto = mock(ProductDto.class);
        when(productDto.getPrice()).thenReturn(1);
        when(productDto.getId()).thenReturn(1);
        shoppingCart.addCartItem(productDto);
        verify(productDto).getId();
        assertEquals(2, shoppingCart.getTotalPrice().intValue());
        assertEquals(2, shoppingCart.getCartItems().get(1).getCount().intValue());
    }

    @Test
    @Disabled("TODO: This test is incomplete")
    void testDeleteCartItem() {
        // TODO: This test is incomplete.
        //   Reason: F009 Internal error.
        //   java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61883: (#61809).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems, java.util.HashMap@74f7e19c:<data>], 2, 1) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61883: (#61809).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems], 2, 1)
        //   Cause: java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61883: (#61809).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems, java.util.HashMap@74f7e19c:<data>], 2, 1) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61883: (#61809).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems], 2, 1)
        //       at com.diffblue.assertion.generator.core.g.b(SourceFile:212)
        //       at com.diffblue.assertion.generator.core.i.a(SourceFile:213)
        //       at com.diffblue.assertion.generator.core.n.a(SourceFile:65)
        //       at com.diffblue.assertion.generator.core.l.a(SourceFile:119)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at java.util.ArrayList.forEach(ArrayList.java:1541)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:109)
        //       at com.diffblue.fuzztest.e.d.a.addAssertions(SourceFile:71)
        //       at com.diffblue.fuzztest.e.b.b(SourceFile:41)
        //       at com.diffblue.cover.sandbox.execution.WrapExceptionsInBaseException.handleException(SourceFile:55)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.lambda$run$0(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.handleThrowable(SourceFile:32)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.run(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.SafeExecutor.run(SourceFile:26)
        //       at com.diffblue.fuzztest.e.b.a(SourceFile:40)
        //       at com.diffblue.fuzztest.e.c.b(SourceFile:1271)
        //       at com.diffblue.fuzztest.e.c.i(SourceFile:400)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.lambda$callWorkerThread$3(SourceFile:134)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.run(SourceFile:207)
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support) providing details about this error.

        this.shoppingCart.deleteCartItem(0);
    }

    @Test
    @Disabled("TODO: This test is incomplete")
    void testAddSameItem() {
        // TODO: This test is incomplete.
        //   Reason: F009 Internal error.
        //   java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61794: (#61723).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems, java.util.HashMap@407fb5fe:<data>], 2, 1) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61794: (#61723).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems], 2, 1)
        //   Cause: java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61794: (#61723).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems, java.util.HashMap@407fb5fe:<data>], 2, 1) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #61794: (#61723).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems], 2, 1)
        //       at com.diffblue.assertion.generator.core.g.b(SourceFile:212)
        //       at com.diffblue.assertion.generator.core.i.a(SourceFile:213)
        //       at com.diffblue.assertion.generator.core.n.a(SourceFile:65)
        //       at com.diffblue.assertion.generator.core.l.a(SourceFile:119)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at java.util.ArrayList.forEach(ArrayList.java:1541)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:109)
        //       at com.diffblue.fuzztest.e.d.a.addAssertions(SourceFile:71)
        //       at com.diffblue.fuzztest.e.b.b(SourceFile:41)
        //       at com.diffblue.cover.sandbox.execution.WrapExceptionsInBaseException.handleException(SourceFile:55)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.lambda$run$0(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.handleThrowable(SourceFile:32)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.run(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.SafeExecutor.run(SourceFile:26)
        //       at com.diffblue.fuzztest.e.b.a(SourceFile:40)
        //       at com.diffblue.fuzztest.e.c.b(SourceFile:1271)
        //       at com.diffblue.fuzztest.e.c.i(SourceFile:400)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.lambda$callWorkerThread$3(SourceFile:134)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.run(SourceFile:207)
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support) providing details about this error.

        this.shoppingCart.addSameItem(2);
    }

    @Test
    void testDeleteSameItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.deleteSameItem(123);
        assertEquals(0, shoppingCart.getTotalPrice().intValue());
    }

    @Test
    void testDeleteSameItem2() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addCartItem(new ProductDto(1, "Dr", 1, new HashSet<Integer>(), 3));
        shoppingCart.deleteSameItem(123);
        assertEquals(1, shoppingCart.getTotalPrice().intValue());
    }

    @Test
    void testDeleteSameItem3() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addCartItem(new ProductDto(123, "Dr", 1, new HashSet<Integer>(), 3));
        shoppingCart.deleteSameItem(123);
        assertEquals(0, shoppingCart.getTotalPrice().intValue());
        assertEquals(0, shoppingCart.getCount());
    }

    @Test
    @Disabled("TODO: This test is incomplete")
    void testGetCount() {
        // TODO: This test is incomplete.
        //   Reason: F009 Internal error.
        //   java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #112142: (#112131).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems, java.util.HashMap@522e0362:<data>], 2, 1) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #112142: (#112131).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems], 2, 1)
        //   Cause: java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #112142: (#112131).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems, java.util.HashMap@522e0362:<data>], 2, 1) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #112142: (#112131).getCartItems(), [com.example.springmvc.mvcLayer.component.ShoppingCart@841a96f:cartItems], 2, 1)
        //       at com.diffblue.assertion.generator.core.g.b(SourceFile:212)
        //       at com.diffblue.assertion.generator.core.i.a(SourceFile:213)
        //       at com.diffblue.assertion.generator.core.n.a(SourceFile:65)
        //       at com.diffblue.assertion.generator.core.l.a(SourceFile:119)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at java.util.Collections$SingletonList.forEach(Collections.java:4856)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:109)
        //       at com.diffblue.fuzztest.e.d.a.addAssertions(SourceFile:71)
        //       at com.diffblue.fuzztest.e.b.b(SourceFile:41)
        //       at com.diffblue.cover.sandbox.execution.WrapExceptionsInBaseException.handleException(SourceFile:55)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.lambda$run$0(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.handleThrowable(SourceFile:32)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.run(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.SafeExecutor.run(SourceFile:26)
        //       at com.diffblue.fuzztest.e.b.a(SourceFile:40)
        //       at com.diffblue.fuzztest.e.c.b(SourceFile:1271)
        //       at com.diffblue.fuzztest.e.c.i(SourceFile:400)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.lambda$callWorkerThread$3(SourceFile:134)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.run(SourceFile:207)
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support) providing details about this error.

        this.shoppingCart.getCount();
    }

    @Test
    @Disabled("TODO: This test is incomplete")
    void testGetCartItems() {
        // TODO: This test is incomplete.
        //   Reason: F009 Internal error.
        //   java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #112115: (#112112).getCartItems(), [java.util.HashMap@6c10c55d:<data>], 1, 0) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #112115: (#112112).getCartItems(), [], 1, 0)
        //   Cause: java.lang.AssertionError: Misuse of currentTasks stack detected: GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #112115: (#112112).getCartItems(), [java.util.HashMap@6c10c55d:<data>], 1, 0) != GenAssertions$Task(java.util.Map<java.lang.Integer,com.example.springmvc.mvcLayer.domain.cart.CartItem>, #112115: (#112112).getCartItems(), [], 1, 0)
        //       at com.diffblue.assertion.generator.core.g.b(SourceFile:212)
        //       at com.diffblue.assertion.generator.core.i.a(SourceFile:213)
        //       at com.diffblue.assertion.generator.core.n.a(SourceFile:65)
        //       at com.diffblue.assertion.generator.core.l.a(SourceFile:102)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at java.util.Collections$SingletonList.forEach(Collections.java:4856)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:232)
        //       at com.diffblue.assertion.generator.core.f.a(SourceFile:109)
        //       at com.diffblue.fuzztest.e.d.a.addAssertions(SourceFile:71)
        //       at com.diffblue.fuzztest.e.b.b(SourceFile:41)
        //       at com.diffblue.cover.sandbox.execution.WrapExceptionsInBaseException.handleException(SourceFile:55)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.lambda$run$0(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.handleThrowable(SourceFile:32)
        //       at com.diffblue.cover.sandbox.execution.WrapThrowableInBaseException.run(SourceFile:21)
        //       at com.diffblue.cover.sandbox.execution.SafeExecutor.run(SourceFile:26)
        //       at com.diffblue.fuzztest.e.b.a(SourceFile:40)
        //       at com.diffblue.fuzztest.e.c.b(SourceFile:1271)
        //       at com.diffblue.fuzztest.e.c.i(SourceFile:400)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.lambda$callWorkerThread$3(SourceFile:134)
        //       at com.diffblue.cover.sandbox.execution.WorkerThread.run(SourceFile:207)
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support) providing details about this error.

        this.shoppingCart.getCartItems();
    }

    @Test
    void testConstructor() {
        assertEquals(0, (new ShoppingCart()).getTotalPrice().intValue());
    }
}

