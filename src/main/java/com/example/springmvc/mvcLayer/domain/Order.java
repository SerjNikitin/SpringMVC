package com.example.springmvc.mvcLayer.domain;

import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.domain.security.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@ToString(exclude = {"userId", "address", "cart"})
@EqualsAndHashCode(exclude = {"id", "userId", "address", "cart"})
public class Order implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<ProductDto> cart;

    @Column(name = "date")
    private LocalDate date;
}
