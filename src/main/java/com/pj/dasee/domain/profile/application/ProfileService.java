package com.pj.dasee.domain.profile.application;

import com.pj.dasee.domain.profile.dao.ProfileRepository;
import com.pj.dasee.domain.profile.domain.Profile;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    /*
     * readProfile
    */
    @Transactional
    public Profile readProfile(Long id) {
        return profileRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Profile with id: " + id + " not found."));
    }

    /*
     * editProfileForm
    */
    @Transactional
    public Profile editProfileForm(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    /*
     * editProfile
    */
    @Transactional
    public void editProfile(Profile editEntity) {
        Profile profile = profileRepository.findById(editEntity.getId()).orElseThrow(() ->
                new IllegalArgumentException("Invalid item id: " + editEntity.getId()));

        if (profile != null) {
            profile.updateProfile(editEntity.getName());
            profileRepository.save(profile);
        }
    }
}
