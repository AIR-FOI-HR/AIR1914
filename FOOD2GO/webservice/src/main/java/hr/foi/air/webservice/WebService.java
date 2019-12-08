package hr.foi.air.webservice;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WebService {
    @GET("registracija/{ime}/{prezime}/{korisnickoime}/{lozinka}/{oib}/{email}/{adresa}/{brojmobitela}/{aktivacijski}/")
    Call<WebServiceResponse> RegistrirajSe (@Path("ime") String ime, @Path("prezime") String prezime,
                                            @Path("korisnickoime") String korisnickoime, @Path("lozinka") String lozinka,
                                            @Path("oib") String oib, @Path("email") String email,
                                            @Path("adresa") String adresa, @Path("brojmobitela") String brojmobitela,
                                            @Path("aktivacijski") String aktivacijski);
    @GET("aktivacijakoda/{email}/{aktivacijski}/")
    Call<WebServiceResponse> AktivacijskiKod (@Path("email") String email, @Path("aktivacijski") String aktivacijski);
}
