package com.example.serverinstagram.domain.likes.model;

import com.example.serverinstagram.domain.audit.model.UserDateAudit;
import com.example.serverinstagram.domain.post.model.Post;
import com.example.serverinstagram.domain.user.model.User;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes")
@Builder
public class Like extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
