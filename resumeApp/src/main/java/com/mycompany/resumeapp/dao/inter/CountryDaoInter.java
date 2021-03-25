package com.mycompany.resumeapp.dao.inter;

import com.mycompany.bean.Country;
import java.util.List;

public interface CountryDaoInter {
    //get all countries
    public List<Country> getAllCountries();
    //update country
    public boolean updateCountry(Country u);
    //insert country
    public boolean addCountry(Country u);
    //delete country
    public boolean deleteCountry(int id);
}
