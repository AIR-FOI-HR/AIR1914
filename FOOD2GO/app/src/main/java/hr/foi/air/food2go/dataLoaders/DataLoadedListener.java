package hr.foi.air.food2go.dataLoaders;

public interface DataLoadedListener {
    void onDataLoaded(String message, String status,Object data);
}
