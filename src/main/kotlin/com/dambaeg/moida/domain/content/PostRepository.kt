package com.dambaeg.moida.domain.content

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, String>