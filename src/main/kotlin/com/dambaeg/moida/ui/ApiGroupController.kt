package com.dambaeg.moida.ui

import com.dambaeg.moida.application.GroupService
import com.dambaeg.moida.application.view.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(GROUP_BASE_URL)
class ApiGroupController {

    @Autowired
    lateinit var groupService: GroupService

    @PostMapping("")
    fun create(@RequestBody group: GroupCreateView): ResponseEntity<GroupView> {
        val view = groupService.create(group)
        return ResponseEntity.created(URI(view.generateUrl())).body(view)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<GroupView> {
        val view = groupService.findViewById(id)
        return ResponseEntity.ok(view)
    }
}