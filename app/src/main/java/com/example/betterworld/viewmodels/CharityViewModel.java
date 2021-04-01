package com.example.betterworld.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.Charity;
import com.example.betterworld.repositories.CharityRepository;

import javax.inject.Inject;

import static com.example.betterworld.utils.HelperClass.logErrorMessage;

public class CharityViewModel  extends ViewModel {

    private  CharityRepository charityRepository;

    @Inject
    public CharityViewModel() {
        this.charityRepository = new CharityRepository();
    }

    public void  createCharity(){
        charityRepository.createCharity();
    }
}
