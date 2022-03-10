package com.martorell.mowers.service.database.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="twist_letters")
public class TwistLetters {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="cardinal_order")
    private String cardinalOrder;
}
