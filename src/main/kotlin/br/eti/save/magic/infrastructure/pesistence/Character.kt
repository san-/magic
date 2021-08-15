package br.eti.save.magic.infrastructure.pesistence

import org.hibernate.annotations.GeneratorType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Character(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String,

    @Column(nullable = false)
    val name: String,

    val role: String,

    val school: String,

    val house: String,

    val patronus: String,

){

}
