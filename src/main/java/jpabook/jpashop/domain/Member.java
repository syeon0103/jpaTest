package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //내장타입 둘 중 하나만 넣어도 되지만 보통 두 개 다 함
    private Address address;

    @OneToMany(mappedBy = "member")  //order 테이블에 있는 member에 의해 매핑
    private List<Order> orders = new ArrayList<>();


}
