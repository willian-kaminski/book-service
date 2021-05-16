package com.kaminski.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "tbl_profiles")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

}
