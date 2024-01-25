package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Delivery;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name="orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //x to many 는 기본적으로 레이지를 제공하므로 그냥 두면 됨
    //maytoone 레이지로 바꿔줘야 함!
    //즉시로딩 X , 지연로딩 으로 모두 사용하기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //초기화는 필드에서 바로 하기 아래처럼
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Delivery delivery;

    private LocalDateTime orderDate; //localdatetime 알아서 지원 이거 쓰면 됨

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태

    //==연관관계 메서드 (양방향일 때 )
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

   /* public static void main(String[] args) {
        Member member = new Member();
        Order order = new Order();

      member.getOrders().add(order);
        order.setMember(member);
    }*/


}
