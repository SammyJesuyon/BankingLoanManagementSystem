package org.jesuyon.blms.usermanagement.user.service;

import org.jesuyon.blms.usermanagement.user.domain.Clerk;
import org.jesuyon.blms.usermanagement.user.dto.ClerkDto;
import org.jesuyon.blms.usermanagement.user.dto.CreateUserDto;
import org.jesuyon.blms.usermanagement.user.repository.ClerkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClerkService {

    @Autowired
    private ClerkRepository clerkRepository;

    public List<ClerkDto> getAllClerks() {
        return clerkRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ClerkDto getClerkById(String id) {
        return clerkRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    public ClerkDto createClerk(CreateUserDto clerkDto) {
        Clerk clerk = mapToEntity(clerkDto);
        return mapToDTO(clerkRepository.save(clerk));
    }

    public ClerkDto updateClerk(String id, ClerkDto clerkDto) {
        Clerk clerk = clerkRepository.findById(id).orElseThrow();
        clerk.setFirstName(clerkDto.getFirstName());
        clerk.setLastName(clerkDto.getLastName());
        clerk.setEmail(clerkDto.getEmail());
        return mapToDTO(clerkRepository.save(clerk));
    }

    private ClerkDto mapToDTO(Clerk clerk) {
        ClerkDto dto = new ClerkDto();
        dto.setId(clerk.getId());
        dto.setFirstName(clerk.getFirstName());
        dto.setLastName(clerk.getLastName());
        dto.setEmail(clerk.getEmail());
        dto.setCreatedAt(clerk.getCreatedAt());
        dto.setUpdatedAt(clerk.getUpdatedAt());
        return dto;
    }

    private Clerk mapToEntity(CreateUserDto dto) {
        Clerk clerk = new Clerk();
        clerk.setFirstName(dto.getFirstName());
        clerk.setLastName(dto.getLastName());
        clerk.setEmail(dto.getEmail());
        clerk.setPassword(dto.getPassword());
        return clerk;
    }
}
