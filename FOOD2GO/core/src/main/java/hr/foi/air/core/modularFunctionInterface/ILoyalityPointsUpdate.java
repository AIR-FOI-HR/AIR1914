package hr.foi.air.core.modularFunctionInterface;

public interface ILoyalityPointsUpdate {
    public void setData(int korisnikID,String code);
    public interface onCallBackRecived{
        public void Update();
    }
}
