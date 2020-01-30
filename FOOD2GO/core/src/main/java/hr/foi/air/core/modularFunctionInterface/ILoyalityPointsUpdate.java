package hr.foi.air.core.modularFunctionInterface;

import java.util.ArrayList;

public interface ILoyalityPointsUpdate {
     void setData(int korisnikID,String code);
     interface onCallBackRecived{
         void Update();
    }
}
