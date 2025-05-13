package byyoun.board.comment.service.response;

import byyoun.board.comment.entity.Comment;
import byyoun.board.comment.entity.CommentV2;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class CommentResponse {
    private Long commentId;
    private String content;
    private Long parentCommentId;
    private Long articleId;
    private Long writerId;
    private boolean deleted;
    private String path;
    private LocalDateTime createdAt;

    public static CommentResponse from(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.commentId = comment.getCommentId();
        commentResponse.content = comment.getContent();
        commentResponse.parentCommentId = comment.getParentCommentId();
        commentResponse.createdAt = comment.getCreatedAt();
        commentResponse.writerId = comment.getWriterId();
        commentResponse.deleted = comment.getDeleted();
        return commentResponse;
    }

    public static CommentResponse from(CommentV2 comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.commentId = comment.getCommentId();
        commentResponse.content = comment.getContent();
        commentResponse.path = comment.getCommentPath().getPath();
        commentResponse.createdAt = comment.getCreatedAt();
        commentResponse.writerId = comment.getWriterId();
        commentResponse.deleted = comment.getDeleted();
        return commentResponse;
    }
}
