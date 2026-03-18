package com.prolink.prolink.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "work")
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfileWork;
    private String workInstitutionName;
    private LocalDate startDateWork;
}
