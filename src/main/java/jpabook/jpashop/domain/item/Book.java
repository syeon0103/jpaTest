package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("B") //싱글테이블이니까 구분하는 값
public class Book  extends Item {

    private String author;
    private String isbn;
}
