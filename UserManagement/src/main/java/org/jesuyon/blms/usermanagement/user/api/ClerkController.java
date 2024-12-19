package org.jesuyon.blms.usermanagement.user.api;

import org.jesuyon.blms.usermanagement.user.dto.ClerkDto;
import org.jesuyon.blms.usermanagement.user.service.ClerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clerks")
public class ClerkController {

    @Autowired
    private ClerkService clerkService;

    @GetMapping
    public ResponseEntity<List<ClerkDto>> getAllClerks() {
        return ResponseEntity.ok(clerkService.getAllClerks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClerkDto> getClerkById(@PathVariable String id) {
        ClerkDto clerk = clerkService.getClerkById(id);
        return clerk != null ? ResponseEntity.ok(clerk) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClerkDto> createClerk(@RequestBody ClerkDto clerkDto) {
        return ResponseEntity.ok(clerkService.createClerk(clerkDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClerkDto> updateClerk(@PathVariable String id, @RequestBody ClerkDto clerkDto) {
        return ResponseEntity.ok(clerkService.updateClerk(id, clerkDto));
    }
}
