package jpabook.jpashop.domain.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("M")
public class Movie extends Item {

    private String artist;
    private String etc;
}
