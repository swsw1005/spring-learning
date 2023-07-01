package com.example.transactiontest.bean.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "UserEntity")
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity implements Serializable {

    @Comment("-- PK --")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    protected long id;

    @Column(nullable = false, length = 60)
    private String username;

    @Column
    private String email;

    @Column
    private String password;

}
