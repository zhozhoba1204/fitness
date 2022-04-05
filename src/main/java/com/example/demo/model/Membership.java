package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private Integer number;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "section", nullable = false)
    private String section;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
