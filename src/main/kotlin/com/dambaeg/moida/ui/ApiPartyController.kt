package com.dambaeg.moida.ui

import com.dambaeg.moida.application.PartyService
import com.dambaeg.moida.application.view.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(PARTY_BASE_URL)
class ApiPartyController {

    @Autowired
    lateinit var partyService: PartyService

    @PostMapping("")
    fun create(@RequestBody view: PartyCreateView): ResponseEntity<PartyView> {
        val view = partyService.create(view)
        return ResponseEntity.created(URI(view.generateUrl())).body(view)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<PartyView> {
        val view = partyService.findViewById(id)
        return ResponseEntity.ok(view)
    }
}