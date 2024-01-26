package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


//@Controller @ResponseBody //이거 두개 합친 게 밑에
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> getMemberList() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result getMemberList2() {
        List<Member> findMembers = memberService.findMembers();
        List<memberDto> collect = findMembers.stream()
                .map(m -> new memberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class memberDto {
        private String name;
    }


    @PostMapping("/api/v1/members")
    public CreateMember saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMember(id);
    }

    /**
     * 멤버 등록
     * @param request
     * @return
     */
    @PostMapping("/api/v2/members")
        public CreateMember SaveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMember(id);
    }

    @PutMapping("/api/v2/members/{id}")
        public UpdateMembers updateMembers(@PathVariable("id") Long id,
                                           @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMembers(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMembers {
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest {
        private String name;
    }

    @Data
    static class CreateMember {
        private Long id;

        public CreateMember(Long id) {
            this.id = id;
        }
    }
}
