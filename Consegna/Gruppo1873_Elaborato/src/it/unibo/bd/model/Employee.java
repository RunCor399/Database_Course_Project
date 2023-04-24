package it.unibo.bd.model;

public interface Employee {
    String getCode();
    
    String getName();
    
    String getSurname();
    
    Gender getGender();
    
    String getCity();
    
    String getAdress();
    
    String getRole();
    
    void setCode(String code);
    
    void setName(String name);
    
    void setSurname(String surname);
    
    void setGender(Gender gender);
    
    void setCity(String city);
    
    void setAdress(String adress);
    
    void setRole(String role);
    
    enum Gender { M, F }
}
