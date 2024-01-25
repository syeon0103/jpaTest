package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery" , fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // 스트링으로 써야 순서에서 밀리는 거 없음
    //EnumType.ORDINAL 은 숫자로 들어감 0~ 문제: 다른 상태가 생기면 망함 절대 쓰지 말기
    private DeliveryStatus status; // READY , COMP
}
