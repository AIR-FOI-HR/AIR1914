package hr.foi.air.core.modularFunctionInterface;


import java.util.ArrayList;


public interface ILoyalityPointsUpdate {
     void setData(int korisnikID,String code);
     String getData();
     interface onCallBackRecived{
         void Update();
    }
}
