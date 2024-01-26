package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    @RequiredArgsConstructor
    @Component
    @Transactional
    static class  InitService {

        private final EntityManager em;
        public void dbInit1() {

        }
    }

}

