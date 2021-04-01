package com.example.betterworld.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.Charity;
import com.example.betterworld.repositories.CharityRepository;

import javax.inject.Inject;

public class CharityViewModel extends ViewModel {

    private CharityRepository _charityRepository;

    @Inject
    public CharityViewModel(CharityRepository charityRepository) {
        this._charityRepository = charityRepository;
    }



    public void  createCharity(Charity charity){
        _charityRepository.createCharityOnFireStore(charity);
    }
}
