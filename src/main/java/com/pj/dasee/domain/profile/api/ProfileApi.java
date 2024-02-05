package com.pj.dasee.domain.profile.api;

import com.pj.dasee.domain.profile.application.ProfileService;
import com.pj.dasee.domain.profile.domain.Profile;
import com.pj.dasee.domain.profile.dto.ProfileCreateRequest;
import com.pj.dasee.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
@Controller
public class ProfileApi {

    private final ProfileService profileService;

    /*
     * read profile
    */
    @GetMapping("/profiles/{id}")
    public String readProfile(@PathVariable Long id, Model model) {
        Profile profiles = profileService.readProfile(id);
        model.addAttribute("profiles", profiles);

        return "Profile/readProfile";
    }

    /*
     * update profile
    */
    @GetMapping("profiles/{id}/editProfileForm")
    public String editProfileForm(@PathVariable Long id, Model model) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal(); // 현재 로그인한 사용자

        Profile profiles = profileService.editProfileForm(id);
        if (!profiles.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to edit this profile.");
        }

        model.addAttribute("profiles", profiles);
        return "Profile/editProfileForm";
    }

    @PostMapping("/profiles/edit")
    public String editProfile(ProfileCreateRequest profileCreateRequest) {
        Profile editEntity = profileCreateRequest.toEntity();
        profileService.editProfile(editEntity);

        return "redirect:/profiles/" + editEntity.getId();
    }
}
