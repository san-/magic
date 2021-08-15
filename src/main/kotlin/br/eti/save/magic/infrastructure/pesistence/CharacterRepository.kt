package br.eti.save.magic.infrastructure.pesistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository: JpaRepository<Character, String> {
}
