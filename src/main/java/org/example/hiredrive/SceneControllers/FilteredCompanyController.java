package org.example.hiredrive.SceneControllers;

import org.example.hiredrive.users.Company;

import java.util.ArrayList;

public class FilteredCompanyController extends SuperSceneController{

    private ArrayList<Company> filteredCompany;



    @Override
    public void setData(Object data){

        filteredCompany = (ArrayList<Company>) data;

    }

    public void initialise() {
        
    }


}
