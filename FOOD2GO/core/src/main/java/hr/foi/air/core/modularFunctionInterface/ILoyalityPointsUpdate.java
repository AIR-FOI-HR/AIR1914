package hr.foi.air.core.modularFunctionInterface;

import hr.foi.air.core.Racun;

public interface ILoyalityPointsUpdate {
     void setData(int korisnikID,String code);
     Racun getData();
     interface onCallBackRecived{
         void Update();
    }
}
