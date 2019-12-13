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

    @GET("prijava/{username}/{password}/")
    Call<WebServiceResponse> PrijaviSe (@Path("username") String username, @Path("password") String password);

    @GET("zaboravljenalozinka/{email}/{username}/")
    Call<WebServiceResponse> ZaboravljenaLozinka (@Path("email") String email, @Path("username") String username);

    @GET("azurirajKorisnika/{ime}/{prezime}/{username}/{adresa}/{lozinka}/{mobitel}/{id}/{email}/")
    Call<WebServiceResponse> AzurirajKorisnika(@Path("ime") String ime, @Path("prezime") String prezime, @Path("username") String username, @Path("adresa") String adresa, @Path("lozinka") String lozinka, @Path("mobitel") String mobitel, @Path("id") int id, @Path("email") String email);
}
