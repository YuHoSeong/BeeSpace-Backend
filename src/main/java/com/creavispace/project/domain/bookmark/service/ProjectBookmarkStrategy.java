package com.creavispace.project.domain.bookmark.service;

import com.creavispace.project.common.post.entity.Post;
import com.creavispace.project.common.exception.CreaviCodeException;
import com.creavispace.project.common.exception.GlobalErrorCode;
import com.creavispace.project.domain.bookmark.dto.response.BookmarkResponseDto;
import com.creavispace.project.domain.bookmark.entity.ProjectBookmark;
import com.creavispace.project.domain.bookmark.repository.ProjectBookmarkRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProjectBookmarkStrategy implements BookmarkStrategy {

    private final ProjectRepository projectRepository;
    private final ProjectBookmarkRepository projectBookmarkRepository;

    @Override
    public BookmarkResponseDto bookmarkToggle(Long postId, Member member) {
        BookmarkResponseDto data = null;

        Project project = projectRepository.findById(postId).orElseThrow(()-> new NoSuchElementException("프로젝트 id("+postId+")가 존재하지 않습니다."));

        if(!project.getStatus().equals(Post.Status.PUBLIC)) throw new CreaviCodeException(GlobalErrorCode.NOT_PUBLIC_CONTENT);

        Optional<ProjectBookmark> projectBookmarkOptional = projectBookmarkRepository.findByProjectIdAndMemberId(postId, member.getId());

        if(projectBookmarkOptional.isEmpty()){
            ProjectBookmark saveBookmark = ProjectBookmark.builder()
                    .member(member)
                    .project(project)
                    .build();
            projectBookmarkRepository.save(saveBookmark);
            data = BookmarkResponseDto.builder().bookmarkStatus(true).build();
        }else{
            projectBookmarkRepository.deleteById(projectBookmarkOptional.get().getId());
            data = BookmarkResponseDto.builder().bookmarkStatus(false).build();
        }

        return data;
    }

    @Override
    public BookmarkResponseDto readBookmark(Long postId, String memberId) {
        BookmarkResponseDto data = null;

        projectRepository.findById(postId).orElseThrow(()-> new NoSuchElementException("프로젝트 id("+postId+")가 존재하지 않습니다."));

        boolean isProject = projectBookmarkRepository.existsByProjectIdAndMemberId(postId, memberId);

        return new BookmarkResponseDto(isProject);
    }

//    @Override
//    public List<BookmarkContentsResponseDto> readMyBookmark(String memberId, Pageable pageRequest) {
//        System.out.println("ProjectBookmarkStrategy.readMyBookmark");
//        List<ProjectBookmark> projectBookmarks = projectBookmarkRepository.findByMemberIdAndEnableTrue(memberId, pageRequest);
//        List<Long> projectIds = projectBookmarks.stream()
//                .filter(projectBookmark -> projectBookmark.getProject().isStatus())
//                .map(projectBookmark -> projectBookmark.getProject().getId())
//                .toList();
//        projectBookmarkRepository.findByProjectIdIn(projectIds);
//        projectRepository.findByIdIn(projectIds);
//        List<ProjectLink> projectLinks = projectLinkRepository.findByProjectIdIn(projectIds);
//        Map<Long, List<ProjectLinkResponseDto>> links = projectLinks(projectLinks);
//        return getBookmarkContentsResponseDtos(
//                projectBookmarks, links);
//    }

//    private static List<BookmarkContentsResponseDto> getBookmarkContentsResponseDtos(
//            List<ProjectBookmark> projectBookmarks, Map<Long, List<ProjectLinkResponseDto>> links) {
//        List<BookmarkContentsResponseDto> bookmarks = new ArrayList<>();
//        for (int i = 0; i < projectBookmarks.size(); i++) {
//            ProjectBookmark bookmark = projectBookmarks.get(i);
//            ProjectBookmarkResponseDto build = ProjectBookmarkResponseDto.builder()
//                    .thumbnail(bookmark.getProject().getThumbnail())
//                    .links(links.get(bookmark.getProject().getId()))
//                    .id(bookmark.getProject().getId())
//                    .postType(PostType.PROJECT.name())
//                    .category(bookmark.getProject().getCategory().name())
//                    .title(bookmark.getProject().getTitle())
//                    .content(bookmark.getProject().getContent())
//                    .createdDate(bookmark.getProject().getCreatedDate())
//                    .modifiedDate(bookmark.getProject().getModifiedDate())
//                    .viewCount(bookmark.getProject().getViewCount())
//                    .build();
//            bookmarks.add(build);
//        }
//        return bookmarks;
//    }
//
//    private Map<Long, List<ProjectLinkResponseDto>> projectLinks(List<ProjectLink> projectLinks) {
//        Map<Long, List<ProjectLinkResponseDto>> linkMap = new HashMap<>();
//        for (int i = 0; i < projectLinks.size(); i++) {
//            ProjectLink projectLink = projectLinks.get(i);
//            List<ProjectLinkResponseDto> links = linkMap.getOrDefault(projectLink.getProject().getId(),
//                    new ArrayList<>());
//            ProjectLinkResponseDto projectLinkResponseDto = ProjectLinkResponseDto.builder()
//                    .linkType(projectLink.getLinkType())
//                    .url(projectLink.getUrl())
//                    .build();
//            links.add(projectLinkResponseDto);
//            linkMap.put(projectLink.getProject().getId(), links);
//        }
//        return linkMap;
//    }

}
