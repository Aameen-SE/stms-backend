package com.aameen.stms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    private String role;

    private boolean active;


}
