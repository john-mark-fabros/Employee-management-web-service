package com.johnmark.case_study.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * John Mark A. Fabros
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
}
