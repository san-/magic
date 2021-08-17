package br.eti.save.magic.infrastructure.pesistence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Character(

    @Column(nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String,

    @Column(nullable = false, length = 200)
    val name: String,

    @Column(length = 30)
    val role: String,

    @Column(length = 200)
    val school: String,

    @Column(nullable = false, length = 50)
    val house: String?,

    @Column(length = 30)
    val patronus: String,

    ){

}
