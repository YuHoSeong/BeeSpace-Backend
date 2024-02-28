package com.creavispace.project.domain.recruit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.PostType;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.common.entity.TechStack;
import com.creavispace.project.domain.common.repository.TechStackRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.recruit.dto.request.RecruitPositionRequestDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitRequestDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitTechStackRequestDto;
import com.creavispace.project.domain.recruit.dto.response.DeadLineRecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitDeleteResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitPositionResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitTechStackResponseDto;
import com.creavispace.project.domain.recruit.entity.Recruit;
import com.creavispace.project.domain.recruit.entity.RecruitPosition;
import com.creavispace.project.domain.recruit.entity.RecruitTechStack;
import com.creavispace.project.domain.recruit.repository.RecruitPositionRepository;
import com.creavispace.project.domain.recruit.repository.RecruitRepository;
import com.creavispace.project.domain.recruit.repository.RecruitTechStackRepository;
import com.creavispace.project.global.exception.CreaviCodeException;
import com.creavispace.project.global.exception.GlobalErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {

    private final MemberRepository memberRepository;
    private final RecruitRepository recruitRepository;
    private final RecruitPositionRepository recruitPositionRepository;
    private final TechStackRepository techStackRepository;
    private final RecruitTechStackRepository recruitTechStackRepository;

    @Override
    public SuccessResponseDto<RecruitResponseDto> createRecruit(RecruitRequestDto dto) {
        // JWT 토큰 정보
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Recruit recruit = Recruit.builder()
            .member(member)
            .category(dto.getCategory())
            .amount(dto.getAmount())
            .proceedWay(dto.getProceedWay())
            .workDay(dto.getWorkDay())
            .end(dto.getEnd())
            .contactWay(dto.getContactWay())
            .contact(dto.getContact())
            .title(dto.getTitle())
            .content(dto.getContent())
            .status(Boolean.TRUE)
            .viewCount(0)
            .build();

        recruitRepository.save(recruit);

        List<RecruitPositionRequestDto> positionDtos = dto.getPositions();

        if(positionDtos != null && !positionDtos.isEmpty()){
            List<RecruitPosition> positions = positionDtos.stream()
                .map(positionDto -> RecruitPosition.builder()
                    .position(positionDto.getPosition())
                    .amount(positionDto.getAmount())
                    .now(positionDto.getNow())
                    .status(Boolean.TRUE)
                    .recruit(recruit)
                    .build())
                .collect(Collectors.toList());
    
            recruitPositionRepository.saveAll(positions);
        }

        List<RecruitTechStackRequestDto> techStackDtos = dto.getTechStacks();

        if(techStackDtos != null && !techStackDtos.isEmpty()){
            List<RecruitTechStack> techStacks = techStackDtos.stream()
                .map(techStackDto -> {
                    TechStack techStack = techStackRepository.findById(techStackDto.getTackStackId()).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.TECHSTACK_NOT_FOUND));
                    
                    return RecruitTechStack.builder()
                        .techStack(techStack)
                        .recruit(recruit)
                        .build();
                })
                .collect(Collectors.toList());

            recruitTechStackRepository.saveAll(techStacks);
        }

        Long recruitId = recruit.getId();
        List<RecruitPosition> recruitPositions = recruitPositionRepository.findByRecruitId(recruitId);
        List<RecruitTechStack> recruitTechStacks = recruitTechStackRepository.findByRecruitId(recruitId);

        List<RecruitPositionResponseDto> positions = recruitPositions.stream()
            .map(recruitPosition -> RecruitPositionResponseDto.builder()
                .id(recruitPosition.getId())
                .position(recruitPosition.getPosition())
                .amount(recruitPosition.getAmount())
                .now(recruitPosition.getNow())
                .build())
            .collect(Collectors.toList());

        List<RecruitTechStackResponseDto> techStacks = recruitTechStacks.stream()
            .map(recruitTechStack -> RecruitTechStackResponseDto.builder()
                .techStackId(recruitTechStack.getTechStack().getId())
                .techStack(recruitTechStack.getTechStack().getTechStack())
                .iconUrl(recruitTechStack.getTechStack().getIconUrl())
                .build())
            .collect(Collectors.toList());

        RecruitResponseDto create = RecruitResponseDto.builder()
            .id(recruit.getId())
            .postType(PostType.RECRUIT.getName())
            .memberId(memberId)
            .viewCount(recruit.getViewCount())
            .category(recruit.getCategory())
            .contactWay(recruit.getContactWay())
            .contact(recruit.getContact())
            .amount(recruit.getAmount())
            .proceedWay(recruit.getProceedWay())
            .workDay(recruit.getWorkDay())
            .end(recruit.getEnd())
            .title(recruit.getTitle())
            .content(recruit.getContent())
            .createdDate(recruit.getCreatedDate())
            .modifiedDate(recruit.getModifiedDate())
            .positions(positions)
            .techStacks(techStacks)
            .build();

        return new SuccessResponseDto<>(true, "모집 게시글 생성이 완료되었습니다.", create);
    }

    @Override
    public SuccessResponseDto<RecruitResponseDto> modifyRecruit(Long recruitId, RecruitRequestDto dto) {
        // JWT 토큰 활용
        Long memberId = 1L;
        List<RecruitPositionRequestDto> positionDtos = dto.getPositions();
        List<RecruitTechStackRequestDto> techStackDtos = dto.getTechStacks();

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Recruit recruit = recruitRepository.findById(recruitId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.RECRUIT_NOT_FOUND));

        if(recruit.getMember().getId() != memberId && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        Recruit modifyRecruit = recruit.toBuilder()
            .category(dto.getCategory())
            .amount(dto.getAmount())
            .workDay(dto.getWorkDay())
            .contact(dto.getContact())
            .contactWay(dto.getContactWay())
            .proceedWay(dto.getProceedWay())
            .end(dto.getEnd())
            .title(dto.getTitle())
            .content(dto.getContent())
            .build();

        recruitRepository.save(modifyRecruit);

        recruitPositionRepository.deleteByRecruitId(recruitId);

        if(positionDtos != null && !positionDtos.isEmpty()){
            List<RecruitPosition> recruitPositions = positionDtos.stream()
                .map(positionDto -> RecruitPosition.builder()
                    .position(positionDto.getPosition())
                    .amount(positionDto.getAmount())
                    .now(positionDto.getNow())
                    .build())
                .collect(Collectors.toList());

            recruitPositionRepository.saveAll(recruitPositions);
        }

        recruitTechStackRepository.deleteByRecruitId(recruitId);

        if(techStackDtos != null && !techStackDtos.isEmpty()){
            List<RecruitTechStack> recruitTechStacks = techStackDtos.stream()
                .map(techStackDto -> {
                    TechStack recruitTechStack = techStackRepository.findById(techStackDto.getTackStackId()).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.RECRUIT_NOT_FOUND));

                    return RecruitTechStack.builder()
                        .techStack(recruitTechStack)
                        .recruit(modifyRecruit)
                        .build();
                })
                .collect(Collectors.toList());

            recruitTechStackRepository.saveAll(recruitTechStacks);
        }

        List<RecruitPosition> recruitPositions = recruitPositionRepository.findByRecruitId(recruitId);
        List<RecruitTechStack> recruitTechStacks = recruitTechStackRepository.findByRecruitId(recruitId);

        List<RecruitPositionResponseDto> positions = recruitPositions.stream()
            .map(recruitPosition -> RecruitPositionResponseDto.builder()
                .id(recruitPosition.getId())
                .position(recruitPosition.getPosition())
                .amount(recruitPosition.getAmount())
                .now(recruitPosition.getNow())
                .build())
            .collect(Collectors.toList());
        
        List<RecruitTechStackResponseDto> techStacks = recruitTechStacks.stream()
            .map(recruitTechStack -> RecruitTechStackResponseDto.builder()
                .techStackId(recruitTechStack.getTechStack().getId())
                .techStack(recruitTechStack.getTechStack().getTechStack())
                .iconUrl(recruitTechStack.getTechStack().getIconUrl())
                .build())
            .collect(Collectors.toList());

        RecruitResponseDto modify = RecruitResponseDto.builder()
            .id(modifyRecruit.getId())
            .postType(PostType.RECRUIT.getName())
            .memberId(modifyRecruit.getMember().getId())
            .viewCount(modifyRecruit.getViewCount())
            .category(modifyRecruit.getCategory())
            .contactWay(modifyRecruit.getContactWay())
            .contact(modifyRecruit.getContact())
            .amount(modifyRecruit.getAmount())
            .proceedWay(modifyRecruit.getProceedWay())
            .workDay(modifyRecruit.getWorkDay())
            .end(modifyRecruit.getEnd())
            .title(modifyRecruit.getTitle())
            .content(modifyRecruit.getContent())
            .createdDate(modifyRecruit.getCreatedDate())
            .modifiedDate(modifyRecruit.getModifiedDate())
            .positions(positions)
            .techStacks(techStacks)
            .build();

        return new SuccessResponseDto<>(true, "모집 게시글의 수정이 완료되었습니다.", modify);
            
    }

    @Override
    public SuccessResponseDto<RecruitDeleteResponseDto> deleteRecruit(Long recruitId) {
        // JWT 토큰
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));
        Recruit recruit = recruitRepository.findById(recruitId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.RECRUIT_NOT_FOUND));

        if(recruit.getMember().getId() != memberId && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        Recruit disableRecruit = recruit.toBuilder()
            .status(false)
            .build();

        recruitRepository.save(disableRecruit);

        RecruitDeleteResponseDto delete = RecruitDeleteResponseDto.builder()
            .recruitId(disableRecruit.getId())
            .postType(PostType.RECRUIT.getName())
            .build();
        
        return new SuccessResponseDto<>(true, "모집 게시글 삭제가 완료되었습니다.", delete);
    }

    @Override
    public SuccessResponseDto<List<RecruitListReadResponseDto>> readRecruitList(Integer size, Integer page, String category) {
        Pageable pageRequest = PageRequest.of(page-1, size);
        Page<Recruit> pageable;

        if(category != null && !category.isEmpty()){
            pageable = recruitRepository.findAllByStatusTrueAndCategory(category, pageRequest);
        }else{
            pageable = recruitRepository.findAllByStatusTrue(pageRequest);
        }

        if(!pageable.hasContent()) new CreaviCodeException(GlobalErrorCode.NOT_RECRUIT_CONTENT);
        List<Recruit> recruits = pageable.getContent();

        List<RecruitListReadResponseDto> reads = recruits.stream()
            .map(recruit -> RecruitListReadResponseDto.builder()
                    .id(recruit.getId())
                    .postType(PostType.RECRUIT.getName())
                    .category(recruit.getCategory())
                    .title(recruit.getTitle())
                    .content(recruit.getContent())
                    .amount(recruit.getAmount())
                    // .now()
                    .techStacks(recruit.getTechStacks().stream()
                    .map(techStack -> RecruitTechStackResponseDto.builder()
                        .techStackId(techStack.getTechStack().getId())
                        .techStack(techStack.getTechStack().getTechStack())
                        .iconUrl(techStack.getTechStack().getIconUrl())
                        .build())
                    .collect(Collectors.toList()))
                    .build())
            .collect(Collectors.toList());

        return new SuccessResponseDto<>(true,"모집 게시글 리스트 조회가 완료되었습니다.", reads);
    }

    @Override
    public SuccessResponseDto<RecruitResponseDto> readRecruit(Long recruitId) {
        Recruit recruit = recruitRepository.findByIdAndStatusTrue(recruitId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.RECRUIT_NOT_FOUND));

        // if(isJwt){
        //     recruit = recruitRepository.findById(recruitId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.RECRUIT_NOT_FOUND));
        // }

        RecruitResponseDto read = RecruitResponseDto.builder()
            .id(recruit.getId())
            .postType(PostType.RECRUIT.getName())
            .memberId(recruit.getMember().getId())
            .viewCount(recruit.getViewCount())
            .category(recruit.getCategory())
            .contactWay(recruit.getContactWay())
            .contact(recruit.getContact())
            .amount(recruit.getAmount())
            .proceedWay(recruit.getProceedWay())
            .workDay(recruit.getWorkDay())
            .end(recruit.getEnd())
            .title(recruit.getTitle())
            .content(recruit.getContent())
            .createdDate(recruit.getCreatedDate())
            .modifiedDate(recruit.getModifiedDate())
            .positions(recruit.getPositions().stream()
                .map(position -> RecruitPositionResponseDto.builder()
                    .id(position.getId())
                    .position(position.getPosition())
                    .amount(position.getAmount())
                    .now(position.getNow())
                    .build())
                .collect(Collectors.toList()))
            .techStacks(recruit.getTechStacks().stream()
                .map(techStack -> RecruitTechStackResponseDto.builder()
                    .techStackId(techStack.getTechStack().getId())
                    .techStack(techStack.getTechStack().getTechStack())
                    .iconUrl(techStack.getTechStack().getIconUrl())
                    .build())
                .collect(Collectors.toList()))
            .build();

        return new SuccessResponseDto<>(true, "모집 게시글 디테일 조회가 완료되었습니다.", read);
    }

    @Override
    public SuccessResponseDto<List<DeadLineRecruitListReadResponseDto>> readDeadlineRecruitList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readDeadlineRecruitList'");
    }
    
}
